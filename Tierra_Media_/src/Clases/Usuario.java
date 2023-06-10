package Clases;

import java.util.HashMap;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
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
		this.itinerario = new Itinerario();
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

	public double getPresupuestoInicial() {
		return presupuestoInicial;
	}

	public double getTiempoInicial() {
		return tiempoInicial;
	}

	public HashMap<String, Atraccion> getItinerario() {
		return this.itinerario.getItinerario();
	}

	// -- Métodos --
	public boolean estado() {
		return this.getPresupuesto() > 0 && this.getTiempoDisponible() > 0;
	}

	public void comprarRecomendacion(Recomendacion recomendacion) {
		recomendacion.agregarRecomendacionAItinierario(this.itinerario);
		this.presupuesto -= recomendacion.getPrecio();
		this.tiempoDisponible -= recomendacion.getDuracion();
		recomendacion.decrementarCupo();
	}

}