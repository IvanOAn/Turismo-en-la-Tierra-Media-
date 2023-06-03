package Clases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Atraccion implements Recomendacion {

	private String nombre;
	private double costo;
	private double tiempo;
	private int cupo;
	private TipoDeAtraccion tipoDeAtraccion;

	// -- Constructor --
	public Atraccion(String nombre, double costo, double tiempo, int cupo, TipoDeAtraccion tipoDeAtraccion) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipoDeAtraccion = tipoDeAtraccion;
	}

	// -- Getters --
	public double getCosto() {
		return costo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public int getCupo() {
		return cupo;
	}

	public String getNombre() {
		return nombre;
	}
	
	// -- Overrides --

	@Override
	public String toString() {
		return "\nAtraccion: " + this.nombre + "\n" + "-Precio: $" + costo + "\n" + "-Tiempo Requerido: " + tiempo + "\n"
				+ "-Tipo de atraccion: " + tipoDeAtraccion;
	}

	@Override
	public double getPrecio() {
		return this.costo;
	}

	@Override
	public TipoDeAtraccion getTipoDeRecomendacion() {
		return this.tipoDeAtraccion;
	}

	@Override
	public boolean recomendacionValida(Usuario usuario) {
		HashSet<String> itinerario = usuario.getItinerario();
		if (itinerario.contains(this.nombre))
			return false;
		return true;
	}

	@Override
	public void agregarRecomendacionAItinerario(Usuario usuario) {
		usuario.agregarRecomendacion(this.nombre);
	}

/*	@Override
	public void actualizarRecomendaciones(List<Atraccion> listaAtracciones,List<Promocion> listaPromociones){
		Iterator<Atraccion> iterador=listaAtracciones.iterator();
		
		while(iterador.hasNext()) {
			Atraccion atraccion=iterador.next();
			if(atraccion.getNombre().equals(this.nombre)) {
				if(atraccion.getCupo()==0)
					iterador.remove();			}
		}
		
		
	}*/

	public void decrementarCupo() {
		this.cupo -= 1;
	}

	@Override
	public int prioridad() {
		return 1;
	}

/*	@Override
	public int hashCode() {
		return Objects.hash(cupo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return cupo == other.cupo;
	}*/
}