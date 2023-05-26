package Clases;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Recomendador {
	Queue<Usuario> colaDeUsuarios;
	LinkedList<Atraccion> listaDeAtracciones;
	LinkedList<Promocion> listaDePromociones;
	HashMap<String, Atraccion> mapaAtracciones;

	public Recomendador() {
		this.colaDeUsuarios = new LinkedList<Usuario>();
		this.listaDeAtracciones = new LinkedList<Atraccion>();
		this.listaDePromociones = new LinkedList<Promocion>();
	}

	private void cargarUsuarios() {
		Archivo archivoUsuarios = new Archivo("Usuarios");
		this.colaDeUsuarios = archivoUsuarios.cargarArchivoUsuarios();
	}

	private void cargarAtracciones() {
		Archivo archivoAtracciones = new Archivo("Atracciones");
		this.mapaAtracciones = new HashMap<String, Atraccion>();
		this.listaDeAtracciones=archivoAtracciones.cargarArchivoAtracciones(this.mapaAtracciones);
	}

	private void cargarPromociones() {
		Archivo archivoPromociones = new Archivo("Promociones");
		this.listaDePromociones = archivoPromociones.cargarArchivoPromociones(this.mapaAtracciones);
	}

	public void realizarSugerencia() {
		this.cargarUsuarios();
		this.cargarAtracciones();
		this.cargarPromociones();

		System.out.println("------------------------------------------");
		System.out.printf("%30s", "Bienvenido/a a ...");
		
		
		Scanner input = new Scanner(System.in);
		while (!this.colaDeUsuarios.isEmpty()) {
			System.out.println("\n------------------------------------------\n");
			Usuario usuario = this.colaDeUsuarios.remove();
			System.out.printf("\nNombre del visitante: %s\n", usuario.getNombre());
			System.out.println("Presupuesto: " + usuario.getPresupuesto() + " y tiempoo: " + 
					usuario.getTiempoDisponible() + "\n\n");

			LinkedList<Promocion> listaRecomPromociones = this.generarRecomendacionPromociones(usuario.getTipoDeAtraccionPreferida());
			for(int i = 0; i < listaRecomPromociones.size(); i++) { // preguntas por todas las promociones
				Promocion promoAct = listaRecomPromociones.get(i);
				if(usuario.puedeComprarPromocion(promoAct)) {
					this.ofrecerPromocion(usuario, promoAct, input);
				}
			}
			
			LinkedList<Atraccion> listaRecomAtracciones = this.generarRecomendacionAtracciones(usuario);
			for(int i = 0; i < listaRecomAtracciones.size(); i++) {
				Atraccion atraccionAct = listaRecomAtracciones.get(i);
				if(usuario.puedeComprarAtraccion(atraccionAct)) {
					this.ofrecerAtraccion(usuario, atraccionAct, input);
				}
			}
			// guardarDatosDelUsuarioEnArchivo();
			//System.out.println(usuario.getItinerario()); // o algo asi
		}
		input.close();
	}

	private void ofrecerPromocion(Usuario usuario, Promocion promocion, Scanner input) {
		if (usuario.getPresupuesto() < promocion.getPrecio()
				|| usuario.getTiempoDisponible() < promocion.getTiempoRequerido()
				|| !usuario.promocionValida(promocion))
			return;

		System.out.println(promocion);

		if (this.validarRecomendacion(input)) {
			usuario.comprarPromocion(promocion);
			promocion.actualizarCupoAtraccionesEnPromocion(this.mapaAtracciones);
			System.out.println("Promocion comprada con exito\n");
		} else
			System.out.println("Promocion rechazada\n");
	}

	private boolean validarRecomendacion(Scanner input) {
		String respuesta;
		do {
			System.out.println("\nQuiere aceptar la recomendacion? Digite S (Si) o N (No)");
			System.out.print("Respuesta: ");
			respuesta = input.next();
			respuesta.toUpperCase();
		} while (!(respuesta.equals("S") || respuesta.equals("N")));

		return respuesta.equals("S");
	}

	private void ofrecerAtraccion(Usuario usuario, Atraccion atraccion, Scanner input) {
		if (usuario.getPresupuesto() < atraccion.getCosto() || usuario.getTiempoDisponible() < atraccion.getTiempo()
				|| !usuario.atraccionValida(atraccion))
			return;

		System.out.println(atraccion);

		if (this.validarRecomendacion(input)) {
			usuario.comprarAtraccion(atraccion);
		} else
			return;
	}
	
	// -- Mio --
	public LinkedList<Promocion> generarRecomendacionPromociones(TipoDeAtraccion tipo) {
		LinkedList<Promocion> listaRecomendacionDePromociones = this.listaDePromociones;
		
		ComparadorPromociones comparador = new ComparadorPromociones(tipo);
		listaRecomendacionDePromociones.sort(comparador);
		
		return listaRecomendacionDePromociones;
	}
	
	public LinkedList<Atraccion> generarRecomendacionAtracciones(Usuario usuario){
		LinkedList<Atraccion> listaRecomendacionesDeAtracciones = new LinkedList<Atraccion>();
		
		for(Atraccion atraccion : this.listaDeAtracciones) { // recorro la lista y pregunto si ya fue elegida
			if(!usuario.atraccionYaElegida(atraccion) || atraccion.getCupo() > 0) {
				listaRecomendacionesDeAtracciones.add(atraccion); // si no fue elegida y tiene lugar la agrego
			}
		}
		
		ComparadorAtracciones comparador = new ComparadorAtracciones(usuario.getTipoDeAtraccionPreferida());
		listaRecomendacionesDeAtracciones.sort(comparador);		
		
		return listaRecomendacionesDeAtracciones;
	}
}
