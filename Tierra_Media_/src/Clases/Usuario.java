package Clases;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	//private Set<Atraccion> itinerario;	
	private HashMap<String, Atraccion> itinerario;
	
	public Usuario(String nombre, double presupuesto, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		//itinerario =new TreeSet<>(new ComparadorAtracciones(tipoDeAtraccionPreferida));
		itinerario =new HashMap<>();
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public TipoDeAtraccion getTipoDeAtraccionPreferida() {
		return tipoDeAtraccionPreferida;
	}
	
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
	
	public void agregarPromocion(Promocion promocion) {
		this.itinerario.putAll(promocion.getAtraccionesIncluidas());
		this.setPresupuesto(this.getPresupuesto()-promocion.getPrecio());
		this.setTiempoDisponible(getTiempoDisponible()-promocion.getTiempoRequerido());
		promocion.setCupo(promocion.getCupo()-1);
		promocion.actualizarAtracciones();
	}
	
	public boolean promocionValida(Promocion promocion) {
		return promocion.atraccionEstaEnPromocion(itinerario);
	}
}
