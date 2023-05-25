package Clases;

import java.util.HashMap;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	//private Set<Atraccion> itinerario;	
	private HashMap<String, Atraccion> itinerario;
	
	//-- Constructor --
	public Usuario(String nombre, double presupuesto, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		//itinerario =new TreeSet<>(new ComparadorAtracciones(tipoDeAtraccionPreferida));
		itinerario =new HashMap<>();
	}
	
	//-- Getters --
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
	
	//-- Setters --
	protected void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	protected void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}	
	
	//-- Métodos --
	public boolean estado() {
		return this.getPresupuesto()>0 && this.getTiempoDisponible()>0;
	}
	
	public void agregarAtraccion(Atraccion atraccion) {
		//this.itinerario.add(atraccion);
		this.itinerario.put(atraccion.getNombre(), atraccion);
		this.setPresupuesto(this.getPresupuesto()-atraccion.getCosto());
		this.setTiempoDisponible(getTiempoDisponible()-atraccion.getTiempo());
		atraccion.decrementarCupo();
	}
	
	public boolean atraccionYaElegida(Atraccion atraccion) {
		return this.itinerario.containsKey(atraccion.getNombre());
	}
	
	/*
	public void agregarPromocion(Promocion promocion) {
		this.itinerario.putAll(promocion.getAtraccionesIncluidas());
		this.setPresupuesto(this.getPresupuesto()-promocion.getPrecio());
		this.setTiempoDisponible(getTiempoDisponible()-promocion.getTiempoRequerido());
		promocion.setCupo(promocion.getCupo()-1);
		promocion.actualizarAtracciones();
	}
	
	public boolean promocionValida(Promocion promocion) {
		return promocion.atraccionEstaEnPromocion(itinerario);
	}*/

	//-- Overrides --
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", tipoDeAtraccionPreferida=" + tipoDeAtraccionPreferida + ", itinerario=" + itinerario + "]";
	}
	
}
