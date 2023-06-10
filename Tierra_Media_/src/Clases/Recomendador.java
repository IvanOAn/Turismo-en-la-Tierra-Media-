package Clases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Recomendador {
	Queue<Usuario> colaDeUsuarios;
	List<Recomendacion> listaRecomendaciones;

	public Recomendador() {
		this.colaDeUsuarios = new LinkedList<Usuario>();
		this.listaRecomendaciones = new LinkedList<Recomendacion>();
	}

	private void cargarUsuarios() {
		Archivo archivoUsuarios = new Archivo("Usuarios");
		this.colaDeUsuarios = archivoUsuarios.cargarArchivoUsuarios();
	}

	private void cargarAtracciones(HashMap<String, Atraccion> mapaAtracciones) {
		Archivo archivoAtracciones = new Archivo("Atracciones");
		archivoAtracciones.cargarArchivoAtracciones(mapaAtracciones, listaRecomendaciones);
	}

	private void cargarPromociones(HashMap<String, Atraccion> mapaAtracciones) {
		Archivo archivoPromociones = new Archivo("Promociones");
		archivoPromociones.cargarArchivoPromociones(mapaAtracciones, listaRecomendaciones);
	}

	public void realizarSugerencia() {

		HashMap<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();

		this.cargarUsuarios();
		this.cargarAtracciones(mapaAtracciones);
		this.cargarPromociones(mapaAtracciones);

		System.out.println("------------------------------------------");
		System.out.printf("%30s", "Bienvenido/a a ...");
		System.out.println("\n------------------------------------------");

		Scanner input = new Scanner(System.in);
		while (!this.colaDeUsuarios.isEmpty()) {

			Usuario usuario = this.colaDeUsuarios.remove();
			System.out.printf("\nNombre del visitante: %s\n", usuario.getNombre());

			Collections.sort(listaRecomendaciones,
					new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

			Iterator<Recomendacion> iterador = listaRecomendaciones.iterator();
			Recomendacion aux;

			while (usuario.estado() && iterador.hasNext()) {

				aux = iterador.next();

				if (ofrecerRecomendacion(usuario, aux, input)) { // devuelve true cuando acepto la recomendacion
					System.out.println("¡Aceptada!");
					System.out.println("------------------------------------------");
				}
			}
			imprimirResumenUsuario(usuario);

			Archivo archivoSalida = new Archivo(usuario.getNombre());
			archivoSalida.generarArchivoResumenUsuario(usuario);
		}
		System.out.println("\nSe han realizado todas las recomendaciones a los usuarios, hasta mañana!");
		input.close();
	}

	private boolean ofrecerRecomendacion(Usuario usuario, Recomendacion recomendacion, Scanner input) {

		if (usuario.getPresupuesto() < recomendacion.getPrecio()
				|| usuario.getTiempoDisponible() < recomendacion.getDuracion()
				|| recomendacion.recomendacionIncluyeAtraccionComprada(usuario) || recomendacion.getCupo() == 0)
			return false;

		System.out.println(recomendacion);

		if (this.validarRecomendacion(input)) {
			usuario.comprarRecomendacion(recomendacion);
			return true;
		}

		System.out.println("------------------------------------------");
		return false;
	}

	private boolean validarRecomendacion(Scanner input) {
		String respuesta;

		do {
			System.out.println("\nQuiere aceptar la recomendacion? Digite S (Si) o N (No)");
			System.out.print("Respuesta: ");
			respuesta = input.next();
			respuesta = respuesta.toUpperCase();
		} while (!(respuesta.equals("S") || respuesta.equals("N")));

		return respuesta.equals("S");
	}

	private void imprimirResumenUsuario(Usuario usuario) {
		System.out.println("------------------------------------------");
		System.out.println("Resumen del Usuario: " + usuario.getNombre());

		System.out.println("\n\tSituacion Inicial:\n");
		System.out.printf("\t\t-Presupuesto: $%.2f\n", usuario.getPresupuestoInicial());
		System.out.println("\t\t-Tiempo Disponible: " + usuario.getTiempoInicial() + " horas");

		System.out.println("\n\tAtracciones compradas:\n");

		HashMap<String, Atraccion> itinerario = usuario.getItinerario();
		for (String nombre : itinerario.keySet()) {
			Atraccion atraccion = itinerario.get(nombre);
			System.out.println("\t\t-Atraccion: " + atraccion.getNombre());
			System.out.println("\t\t-Duración : " + atraccion.getDuracion() + " horas\n");
		}

		System.out.println("\tSituacion Final:");

		System.out.printf("\n\t\tCosto total de la salida: $%.2f\n",
				usuario.getPresupuestoInicial() - usuario.getPresupuesto());
		System.out.println("\t\tDuración total de la salida: "
				+ (usuario.getTiempoInicial() - usuario.getTiempoDisponible()) + " horas");
		System.out.println("------------------------------------------");
		System.out.println("------------------------------------------");

	}
}