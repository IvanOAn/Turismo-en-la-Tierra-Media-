package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Promocion implements Recomendacion {

	protected String nombre;
	protected int cupo;
	protected double tiempoRequerido;
	protected TipoDeAtraccion tipoDeAtraccion;
	protected List<String> atraccionesIncluidas;
	protected double precio;

	// -- Constructor --

	// Esto seria si calculamos precio y cupo en la clase archivo
	public Promocion(String nombre, int cupo, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<String> atraccionesIncluidas) {
		this.nombre = nombre;
		this.cupo = cupo;
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

	public List<String> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	// -- Overrides --

	@Override
	public String toString() {
		return "\nPromocion: " + nombre + "\n" + "-Tiempo requerido: " + tiempoRequerido + "\n"
				+ "-Atracciones incluidas: " + atraccionesIncluidas;
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
		return this.cupo;
	}

	@Override
	public TipoDeAtraccion getTipoDeRecomendacion() {
		return this.tipoDeAtraccion;
	}

	@Override
	public void actualizarRecomendaciones(List<Atraccion> listaAtracciones, List<Promocion> listaPromociones,
			HashMap<String, Atraccion> mapaAtracciones) {

		for (Promocion promocion : listaPromociones)
			if (promocion.getNombre().equals(this.nombre)) {
				promocion.decrementarCupo();
				for (String nombre : promocion.getAtraccionesIncluidas())
					for (Atraccion atraccion : listaAtracciones)
						if (atraccion.getNombre().equals(nombre))
							atraccion.decrementarCupo();

			}
	}

	@Override
	public boolean recomendacionValida(Usuario usuario) {
		HashSet<String> itinerario = usuario.getItinerario();
		for (String palabra : atraccionesIncluidas) {
			if (itinerario.contains(palabra))
				return false;
		}
		return true;
	}

	@Override
	public void agregarRecomendacionAItinerario(Usuario usuario) {
		Iterator<String> iterador = this.atraccionesIncluidas.iterator();
		while (iterador.hasNext())
			usuario.agregarRecomendacion(iterador.next());
	}

	public void decrementarCupo() {
		this.cupo -= 1;
	}

	public void recalcularCupo(List<Atraccion> listaAtracciones) {
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
	}

	@Override
	public int prioridad() {
		return -1;
	}

	@Override
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
		Promocion other = (Promocion) obj;
		return cupo == other.cupo;
	}
}
