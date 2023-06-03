package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Promocion implements Recomendacion {

	protected String nombre;
	//protected int cupo;
	protected double tiempoRequerido;
	protected TipoDeAtraccion tipoDeAtraccion;
	protected List<Atraccion> atraccionesIncluidas;
	protected double precio;

	// -- Constructor --

	// Esto seria si calculamos precio y cupo en la clase archivo
	public Promocion(String nombre,double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<Atraccion> atraccionesIncluidas) {
		this.nombre = nombre;
		//this.cupo = cupo;
		this.tiempoRequerido = tiempoRequerido;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.atraccionesIncluidas = atraccionesIncluidas;
	}

	/// -- Gettters --
	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoRequerido() {
		return tiempoRequerido;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	// -- Overrides --

	@Override
	public String toString() {
		return "\nPromocion: " + nombre + "\n" + "-Tiempo requerido: " + tiempoRequerido + "\n"
				+ "-Atracciones incluidas: " + this.toNombre();
	}

	@Override
	public double getPrecio() {
		return this.precio;
	}

	@Override
	public double getTiempo() {
		return this.tiempoRequerido;
	}

	@Override
	public int getCupo() {
		int cupo = 0;
		for (Atraccion atraccion : this.atraccionesIncluidas) {
			if (cupo == 0)
				cupo = atraccion.getCupo();
			else
				cupo = Math.min(cupo, atraccion.getCupo());
		}
		return cupo;
	}

	@Override
	public TipoDeAtraccion getTipoDeRecomendacion() {
		return this.tipoDeAtraccion;
	}

	/*@Override
	public void actualizarRecomendaciones(HashMap<String, Recomendacion> mapaRecomendaciones) {
		this.decrementarCupo();
		Recomendacion recomendacion = null;

		for (String nombre : this.getAtraccionesIncluidas()) {
			recomendacion = mapaRecomendaciones.get(nombre);
			if (recomendacion != null)
				recomendacion.decrementarCupo();
		}
	}*/

	@Override
	public boolean recomendacionValida(Usuario usuario) {
		HashSet<String> itinerario = usuario.getItinerario();
		for (Atraccion atraccion : atraccionesIncluidas) {
			if (itinerario.contains(atraccion.getNombre()))
				return false;
		}
		return true;
	}

	@Override
	public void agregarRecomendacionAItinerario(Usuario usuario) {
		Iterator<Atraccion> iterador = this.atraccionesIncluidas.iterator();
		while (iterador.hasNext())
			usuario.agregarRecomendacion(iterador.next().getNombre());
	}

	public void decrementarCupo() {
		for(Atraccion atraccion : this.atraccionesIncluidas) {
			atraccion.decrementarCupo();
		}
	}

	/*public void recalcularCupo(List<Atraccion> listaAtracciones) {
		int cupo = 0;

		for (String nombreAtraccion : this.atraccionesIncluidas) {
			for (Atraccion atraccion : listaAtracciones) {
				if (nombreAtraccion.equals(atraccion.getNombre())) {
					if (cupo == 0)
						cupo = atraccion.getCupo();
					else
						cupo = Math.min(cupo, atraccion.getCupo());
				}
			}
		}
		this.cupo = cupo;
	}*/

	@Override
	public int prioridad() {
		return -1;
	}
	
	public List<String> toNombre() {
		List<String> listaNombres= new ArrayList<String>();
		for(Atraccion atraccion:this.atraccionesIncluidas)
			listaNombres.add(atraccion.getNombre());
		return listaNombres;
	}

	/*@Override
	public void actualizarRecomendaciones(List<Atraccion> listaAtracciones, List<Promocion> listaPromociones) {
		// TODO Auto-generated method stub
		
	}*/
}
