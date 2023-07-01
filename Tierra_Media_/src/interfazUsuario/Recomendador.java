package interfazUsuario;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import archivo.Archivo;
import recomendacion.Atraccion;
import recomendacion.Recomendacion;
import usuario.Usuario;

public class Recomendador {
	private Queue<Usuario> colaDeUsuarios;
	private List<Recomendacion> listaRecomendaciones;
	private Map<String, Atraccion> mapaAtracciones;

	public Recomendador() {
		this.colaDeUsuarios = new LinkedList<Usuario>();
		this.listaRecomendaciones = new LinkedList<Recomendacion>();
		this.mapaAtracciones = new HashMap<String, Atraccion>();
	}

	private void cargarUsuarios() {
		Archivo archivoUsuarios = new Archivo("Usuarios");
		this.colaDeUsuarios = archivoUsuarios.cargarArchivoUsuarios();
	}

	private void cargarAtracciones(Map<String, Atraccion> mapaAtracciones) {
		Archivo archivoAtracciones = new Archivo("Atracciones");
		archivoAtracciones.cargarArchivoAtracciones(mapaAtracciones, listaRecomendaciones);
	}

	private void cargarPromociones(Map<String, Atraccion> mapaAtracciones) {
		Archivo archivoPromociones = new Archivo("Promociones");
		archivoPromociones.cargarArchivoPromociones(mapaAtracciones, listaRecomendaciones);
	}

	public void realizarSugerencia() {

		this.cargarUsuarios();
		this.cargarAtracciones(mapaAtracciones);
		this.cargarPromociones(mapaAtracciones);

		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("%52s", "Bienvenido/a a Springfield");
		System.out.println("\n------------------------------------------------------------------------------------");

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

				if (ofrecerRecomendacion(usuario, aux, input)) {
					System.out.println("¡Aceptada!");
					System.out.println(
							"------------------------------------------------------------------------------------");
				}
			}
			imprimirResumenUsuario(usuario);

			Archivo archivoSalida = new Archivo(usuario.getNombre());
			archivoSalida.generarArchivoResumenUsuario(usuario, mapaAtracciones);
		}
		System.out.println("\nSe han realizado todas las recomendaciones a los usuarios, hasta mañana!");
		input.close();
	}

	boolean ofrecerRecomendacion(Usuario usuario, Recomendacion recomendacion, Scanner input) {

		if (usuario.getPresupuesto() < recomendacion.getPrecio()
				|| usuario.getTiempoDisponible() < recomendacion.getDuracion()
				|| recomendacion.recomendacionIncluyeAtraccionComprada(usuario) || recomendacion.getCupo() == 0)
			return false;

		System.out.println(recomendacion);

		if (this.validarRecomendacion(input)) {
			usuario.comprarRecomendacion(recomendacion);
			return true;
		}

		System.out.println("------------------------------------------------------------------------------------");
		return false;
	}

	boolean validarRecomendacion(Scanner input) {
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
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Resumen del Usuario: " + usuario.getNombre());

		System.out.println("\nSituacion Inicial:");
		System.out.printf(" -%-15s\t$%.2f\n", "Presupuesto:", usuario.getPresupuestoInicial());
		System.out.printf(" -%-15s\t%.2f horas\n", "Tiempo Disponible:", usuario.getTiempoInicial());

		System.out.println("\nAtracciones compradas:");

		boolean usuarioNoComproNada = true;
		for (String nombreAtraccion : this.mapaAtracciones.keySet()) {
			if (usuario.tieneAtraccion(mapaAtracciones.get(nombreAtraccion))) {
				System.out.printf(" -%-32s\t", mapaAtracciones.get(nombreAtraccion).getNombre());
				System.out.printf("%.2f horas\n", mapaAtracciones.get(nombreAtraccion).getDuracion());
				usuarioNoComproNada = false;
			}
		}
		if (usuarioNoComproNada) {
			System.out.printf(" -%-32s\n", "No se aceptaron recomendaciones");
		}

		System.out.print("\nSituacion Final:");
		System.out.printf("\n -%-15s\t$%.2f\n", "Costo total:",
				usuario.getPresupuestoInicial() - usuario.getPresupuesto());
		System.out.printf(" -%-15s\t%.2f horas\n", "Duración total:",
				(usuario.getTiempoInicial() - usuario.getTiempoDisponible()));
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------");

	}
}