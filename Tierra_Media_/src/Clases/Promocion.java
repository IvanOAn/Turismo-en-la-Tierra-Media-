package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class Promocion implements Comparable<Promocion> {
	protected String nombre;
	protected int cupo;
	protected double tiempoRequerido;
	protected TipoDeAtraccion tipoDeAtraccion;
	//protected HashMap<String, Atraccion> atraccionesIncluidas;
	protected List<String> atraccionesIncluidas;
	protected double precio;

	//-- Constructor --
/*	public Promocion(ArrayList<Atraccion> atraccionesIncluidas) {
		this.atraccionesIncluidas = new HashMap<>();
		this.tipoDeAtraccion = atraccionesIncluidas.get(0).getTipoDeAtraccion();
		for (Atraccion atraccion : atraccionesIncluidas) {
			this.atraccionesIncluidas.put(atraccion.getNombre(), atraccion);
			this.tiempoRequerido += atraccion.getTiempo();
			this.precio += atraccion.getCosto();
			if (cupo == 0)
				cupo = atraccion.getCupo();
			else
				cupo = Math.min(cupo, atraccion.getCupo());
		}
	}*/
	
	//Esto seria si calculamos precio y cupo en la clase archivo
	public Promocion(String nombre,int cupo, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<String> atraccionesIncluidas, double precio) {
		this.nombre= nombre;
		this.cupo = cupo;
		this.tiempoRequerido = tiempoRequerido;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.atraccionesIncluidas = atraccionesIncluidas;
		this.precio = precio;
	}
	
	

	///-- Gettters --
	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoRequerido() {
		return tiempoRequerido;
	}

	public double getPrecio() {
		return precio;
	}

	public int getCupo() {
		return cupo;
	}

	//-- Setters --
	protected void setCupo(int cupo) {
		this.cupo = cupo;
	}

/*	//-- Métodos --
	public HashMap<String, Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}
*/
/*	public static Set<Promocion> promocionesDisponibles(Usuario usuario, Set<Promocion> treeSet) {
		Set<Promocion> res = new TreeSet<>();

		for (Promocion promocion : treeSet) {
			if (promocion.getPrecio() <= usuario.getPresupuesto()
					&& promocion.getTiempoRequerido() <= usuario.getTiempoDisponible() && promocion.getCupo() > 0
					&& !usuario.promocionValida(promocion))
				res.add(promocion);
		}
		return res;
	}*/
/*
	public boolean atraccionEstaEnPromocion(HashMap<String, Atraccion> itinerario) {
		for (String palabra : atraccionesIncluidas.keySet()) {
			if (itinerario.containsKey(palabra))
				return true;
		}
		return false;
	}

	public void actualizarAtracciones() {
		for (String nombre : this.atraccionesIncluidas.keySet()) {
			atraccionesIncluidas.get(nombre).decrementarCupo();
		}
	}*/
	
	//-- Overrides --
	
	@Override
	public String toString() {
		return "Promocion [cupo=" + cupo + ", tiempoRequerido=" + tiempoRequerido + ", tipoDeAtraccion="
				+ tipoDeAtraccion + ", atraccionesIncluidas=" + atraccionesIncluidas + ", precio=" + precio + "]";
	}
}
