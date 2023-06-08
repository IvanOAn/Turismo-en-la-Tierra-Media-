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
	List<Atraccion> listaDeAtracciones;
	List<Promocion> listaDePromociones;
	HashMap<String, Atraccion> mapaAtracciones;

	public Recomendador() {
		this.colaDeUsuarios = new LinkedList<Usuario>();
		this.listaDeAtracciones = new LinkedList<Atraccion>();
		this.listaDePromociones = new LinkedList<Promocion>();
		this.mapaAtracciones = new HashMap<>();
	}

	private void cargarUsuarios() {
		Archivo archivoUsuarios = new Archivo("Usuarios");
		this.colaDeUsuarios = archivoUsuarios.cargarArchivoUsuarios();
	}

	private void cargarAtracciones() {
		Archivo archivoAtracciones = new Archivo("Atracciones");
		this.listaDeAtracciones = archivoAtracciones.cargarArchivoAtracciones(mapaAtracciones);
	}

	private void cargarPromociones() {
		Archivo archivoPromociones = new Archivo("Promociones");
		this.listaDePromociones = archivoPromociones.cargarArchivoPromociones(mapaAtracciones);
	}

	public void realizarSugerencia() {
		this.cargarUsuarios();
		this.cargarAtracciones();
		this.cargarPromociones();

		System.out.println("------------------------------------------");
		System.out.printf("%30s", "Bienvenido/a a ...");
		System.out.println("\n------------------------------------------");

		List<Recomendacion> listaRecomendaciones = new LinkedList<Recomendacion>();
		//LinkedList<Recomendacion> listaRecomendacionesAceptadas = new LinkedList<Recomendacion>();

		listaRecomendaciones.addAll(listaDeAtracciones);
		listaRecomendaciones.addAll(listaDePromociones);

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
					//listaRecomendacionesAceptadas.add(aux);
				}
			}
			imprimirResumenUsuario(usuario);

			Archivo archivoSalida = new Archivo(usuario.getNombre());
			archivoSalida.generarArchivoResumenUsuario(usuario);
			//listaRecomendacionesAceptadas.clear();
		}
		input.close();
	}

	private boolean ofrecerRecomendacion(Usuario usuario, Recomendacion recomendacion, Scanner input) {

		if (usuario.getPresupuesto() < recomendacion.getPrecio()
				|| usuario.getTiempoDisponible() < recomendacion.getDuracion()
				|| !usuario.recomendacionValida(recomendacion) || recomendacion.getCupo() == 0)
			return false;

		System.out.println(recomendacion);

		if (this.validarRecomendacion(input)) {
			usuario.comprarRecomendacion(recomendacion, this.listaDeAtracciones, this.listaDePromociones,
					mapaAtracciones);
			return true;
		}
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

		System.out.println("\nSituacion Original:\n");
		System.out.printf("-Presupuesto Orignal: $%.2f\n", usuario.getPresupuestoInicial());
		System.out.println("-Tiempo Disponible Orignal: " + usuario.getTiempoInicial());

		System.out.println("\nRecomendaciones aceptadas:");
		
		HashMap<String, Atraccion> itinerario=usuario.getItinerario();
		for (String nombre : itinerario.keySet()) {
			System.out.println(itinerario.get(nombre));
		}

		System.out.println("\nSituacion Final:\n");
		System.out.printf("-Presupuesto Final: $%.2f\n", usuario.getPresupuesto());
		System.out.println("-Tiempo Disponible Final: " + usuario.getTiempoDisponible());

		System.out.printf("\nCosto total de la salida: $%.2f\n",
				usuario.getPresupuestoInicial() - usuario.getPresupuesto());
		System.out.printf("Tiempo total de la salida: %.2f\n",
				usuario.getTiempoInicial() - usuario.getTiempoDisponible());
		System.out.println("------------------------------------------");
	}
}