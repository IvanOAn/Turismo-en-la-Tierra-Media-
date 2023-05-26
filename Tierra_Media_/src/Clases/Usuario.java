package Clases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	// private Set<Atraccion> itinerario;
	private HashSet<String> itinerario;

	// -- Constructor --
	public Usuario(String nombre, double presupuesto, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		// itinerario =new TreeSet<>(new
		// ComparadorAtracciones(tipoDeAtraccionPreferida));
		itinerario = new HashSet<String>();
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
	
	public HashSet<String> getItinerario() {
		return itinerario;
	}

	// -- Setters --
	protected void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	protected void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	// -- Métodos --
	public boolean estado() {
		return this.getPresupuesto() > 0 && this.getTiempoDisponible() > 0;
	}

	public void comprarAtraccion(Atraccion atraccion) {
		this.itinerario.add(atraccion.getNombre());
		this.presupuesto = this.presupuesto - atraccion.getCosto();
		this.tiempoDisponible = this.tiempoDisponible - atraccion.getTiempo();
		atraccion.decrementarCupo();
	}

	public boolean atraccionValida(Atraccion atraccion) {
		return !this.itinerario.contains(atraccion.getNombre());
	}

	public void comprarPromocion(Promocion promocion) {
		this.itinerario.addAll(promocion.getAtraccionesIncluidas());
		this.presupuesto = this.presupuesto - promocion.getPrecio();
		this.tiempoDisponible = this.tiempoDisponible - promocion.getTiempoRequerido();
		promocion.setCupo(promocion.getCupo() - 1);
	}

	public boolean promocionValida(Promocion promocion) {
		return promocion.atraccionEstaEnPromocion(itinerario);
	}

	public boolean atraccionYaElegida(Atraccion atraccion) {
		return this.itinerario.contains(atraccion.getNombre()); // pregunta si esta atraccion ya la eligió
	}
	
	public boolean puedeComprarPromocion(Promocion promocion) {
		return this.presupuesto >= promocion.getPrecio() && 
				this.tiempoDisponible >= promocion.getTiempoRequerido() && promocion.getCupo() > 0;
	}
	
	public boolean puedeComprarAtraccion(Atraccion atraccion) {
		return this.presupuesto >= atraccion.getCosto() &&
				this.tiempoDisponible >= atraccion.getTiempo() && atraccion.getCupo() > 0;
	}

	// -- Overrides --

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", tipoDeAtraccionPreferida=" + tipoDeAtraccionPreferida + ", itinerario=" + itinerario + "]";
	}

}
