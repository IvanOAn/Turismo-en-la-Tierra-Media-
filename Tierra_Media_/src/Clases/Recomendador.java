package Clases;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

public class Recomendador {
	Queue<Usuario> colaDeUsuarios;
	List<Atraccion> listaDeAtracciones;
	List<Promocion> listaDePromociones;

	public Recomendador() {
		this.colaDeUsuarios =new LinkedList<Usuario>();
		this.listaDeAtracciones= new LinkedList<Atraccion>();
		this.listaDePromociones= new LinkedList<Promocion>();
	}
	
	private void cargarUsuarios() {
		Archivo archivoUsuarios= new Archivo("Usuarios");
		this.colaDeUsuarios=archivoUsuarios.cargarArchivoUsuarios();
	}
	
	private void cargarAtracciones() {
		Archivo archivoAtracciones= new Archivo("Atracciones");
		this.listaDeAtracciones=archivoAtracciones.cargarArchivoAtracciones();
	}
	
	private void cargarPromociones() {
		Archivo archivoPromociones= new Archivo("Promociones");
		this.listaDePromociones=archivoPromociones.cargarArchivoPromociones(listaDeAtracciones);
	}
	
	public void realizarSugerencia() {
		this.cargarUsuarios();
		this.cargarAtracciones();
		this.cargarPromociones();
		
		System.out.println("------------------------------------------");
		System.out.printf("%30s","Bienvenido/a a ...");
		System.out.println("\n------------------------------------------\n");
		
		List<Recomendacion> listaRecomendaciones=new LinkedList<Recomendacion>();
		
		while(!this.colaDeUsuarios.isEmpty()) {
			
			Usuario usuario= this.colaDeUsuarios.remove();
			System.out.printf("\n\nNombre del visitante: %s\n\n",usuario.getNombre());
			
			listaRecomendaciones.addAll(listaDeAtracciones);
			listaRecomendaciones.addAll(listaDePromociones);
			
			Collections.sort(listaRecomendaciones, new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));
			
			Iterator<Recomendacion> iterador = listaRecomendaciones.iterator();
			Recomendacion aux;
			
			while(usuario.estado() && iterador.hasNext()) {
				
				aux =iterador.next();
				
				ofrecerRecomendacion(usuario, aux);
				
				/*while (iteradorPromociones.hasNext() && (!salir || band)) {
					promocion=iteradorPromociones.next();
					if (promocion.getTipoDeAtraccion()==usuario.getTipoDeAtraccionPreferida() || band) {
						this.ofrecerPromocion(usuario,promocion);
					} else {
						if(iteradorPromociones.hasPrevious())
							iteradorPromociones.previous();
						salir = true;
					}
				}
				
				salir = false;
				while (iteradorAtracciones.hasNext() && (!salir || band)) {
					atraccion = iteradorAtracciones.next();
					if (atraccion.getTipoDeAtraccion() == usuario.getTipoDeAtraccionPreferida() || band) {
						this.ofrecerAtraccion(usuario, atraccion);
					} else {
						if(iteradorAtracciones.hasPrevious())
							iteradorAtracciones.previous();
						salir = true;
					}
				}*/
				
			}
		}
	}
	
	private void ofrecerRecomendacion(Usuario usuario,Recomendacion recomendacion) {
		if(usuario.getPresupuesto()<recomendacion.getPrecio() || 
		   usuario.getTiempoDisponible()< recomendacion.getTiempo() ||
		   !recomendacion.recomendacionValida(usuario))
			return;
		
		System.out.println(recomendacion);
		
		if(this.validarRecomendacion()) {
			usuario.comprarRecomendacion(recomendacion,this.listaDeAtracciones,this.listaDePromociones);
		}else
			return;
	}
	
	private boolean validarRecomendacion() {
		String respuesta;
		Scanner input=new Scanner(System.in);
		do {
		System.out.println("\nQuiere aceptar la recomendacion? Digite S (Si) o N (No)");
		System.out.print("Respuesta: ");
		respuesta=input.next();
		respuesta=respuesta.toUpperCase();
		}while(!(respuesta.equals("S") || respuesta.equals("N")));
		
		//input.close();

		return respuesta.equals("S");
	}
	
/*	private void ofrecerPromocion(Usuario usuario,Promocion promocion) {
		if(usuario.getPresupuesto()<promocion.getPrecio() || 
		   usuario.getTiempoDisponible()< promocion.getTiempoRequerido() ||
		   !usuario.promocionValida(promocion))
			return;
		
		System.out.println(promocion);
		
		if(this.validarRecomendacion()) {
//			usuario.comprarPromocion(promocion);
			this.actualizarCupoAtraccionesEnPromocion(promocion);
		}else
			return;
	}
	
	private void ofrecerAtraccion(Usuario usuario,Atraccion atraccion) {
		if(usuario.getPresupuesto()<atraccion.getCosto() || 
		   usuario.getTiempoDisponible()< atraccion.getTiempo() ||
		   !usuario.atraccionValida(atraccion))
			return;
		
		System.out.println(atraccion);
		
		if(this.validarRecomendacion()) {
			usuario.comprarAtraccion(atraccion);
		}else
			return;
	}

	private void actualizarCupoAtraccionesEnPromocion(Promocion promocion) {//Esto se deberia hacer en la clase promocion
		for(Atraccion atraccion: this.listaDeAtracciones){
			for(String nombre: promocion.getAtraccionesIncluidas())
				if(atraccion.getNombre().equals(nombre))
					atraccion.decrementarCupo();
		}
	}*/
}
