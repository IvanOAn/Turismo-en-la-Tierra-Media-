package usuario;

import java.util.HashMap;

import recomendacion.Atraccion;
import recomendacion.Recomendacion;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoDeAtraccionPreferida;
	private HashMap<String, Atraccion> itinerario;
	private final double presupuestoInicial;
	private final double tiempoInicial;

	// -- Constructor --
	public Usuario(String nombre, double presupuesto, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		this.itinerario = new HashMap<String, Atraccion>();
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

	// -- Métodos --
	public boolean estado() {
		return this.getPresupuesto() > 0 && this.getTiempoDisponible() > 0;
	}

	public boolean tieneAtraccion(Atraccion atraccion) {
		return this.itinerario.containsKey(atraccion.getNombre());
	}

	public void comprarRecomendacion(Recomendacion recomendacion) {
		recomendacion.agregarRecomendacionAItinierario(this);
		this.presupuesto -= recomendacion.getPrecio();
		this.tiempoDisponible -= recomendacion.getDuracion();
		recomendacion.decrementarCupo();
	}

	public void agregaAtraccion(Atraccion atraccion) {
		this.itinerario.put(atraccion.getNombre(), atraccion);
	}
}