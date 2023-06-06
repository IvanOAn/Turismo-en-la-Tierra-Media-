package Clases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	//private HashSet<String> itinerario;
	private Itinerario itinerario;
	private final double presupuestoInicial;
	private final double tiempoInicial;

	// -- Constructor --
	public Usuario(String nombre, double presupuesto, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		//itinerario = new HashSet<String>();
		this.itinerario= new Itinerario();
		this.presupuestoInicial = presupuesto;
		this.tiempoInicial = tiempoDisponible;
	}

	// -- Getters --
	public String getNombre() {
		return nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public TipoDeAtraccion getTipoDeAtraccionPreferida() {
		return tipoDeAtraccionPreferida;
	}

	/*public HashSet<String> getItinerario() {
		return itinerario;
	}*/
	
	public double getPresupuestoInicial() {
		return presupuestoInicial;
	}

	public double getTiempoInicial() {
		return tiempoInicial;
	}

	// -- Setters --
	/*
	protected void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	protected void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}
*/
	// -- Métodos --
	public boolean estado() {
		return this.getPresupuesto() > 0 && this.getTiempoDisponible() > 0;
	}
	
	public boolean recomendacionValida(Recomendacion recomendacion) {
		return this.itinerario.recomendacionValida(recomendacion);
	}

	public void comprarRecomendacion(Recomendacion recomendacion, List<Atraccion> listaAtracciones,
			List<Promocion> listaDePromociones, HashMap<String, Atraccion> mapaAtracciones) {
		//recomendacion.agregarRecomendacionAItinerario(this);
		this.itinerario.agregarRecomendacion(recomendacion);
		this.presupuesto -= recomendacion.getPrecio();
		this.tiempoDisponible -= recomendacion.getDuracion();
		recomendacion.decrementarCupo();
	}

	public HashMap<String, Atraccion> getItinerario() {
		return this.itinerario.getItinerario();
	}

	// -- Overrides --

	/*@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", tipoDeAtraccionPreferida=" + tipoDeAtraccionPreferida + ", itinerario=" + itinerario + "]";
	}*/
}