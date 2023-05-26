package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
			List<String> atraccionesIncluidas) {
		this.nombre= nombre;
		this.cupo = cupo;
		this.tiempoRequerido = tiempoRequerido;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.atraccionesIncluidas = atraccionesIncluidas;
	}
	

	///-- Gettters --
	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public double getTiempoRequerido() {
		return tiempoRequerido;
	}

	public int getCupo() {
		return cupo;
	}

	public List<String> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public double getPrecio() {
		return precio;
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

	public boolean atraccionEstaEnPromocion(HashSet<String> itinerario) {
		for (String palabra : atraccionesIncluidas) {
			if (itinerario.contains(palabra))
				return false;
		}
		return true;
	}
/*
	public void actualizarAtracciones() {
		for (String nombre : this.atraccionesIncluidas.keySet()) {
			atraccionesIncluidas.get(nombre).decrementarCupo();
		}
	}*/
	
	public void actualizarCupoAtraccionesEnPromocion(HashMap<String, Atraccion> mapaAtracciones) {
		for (String Nombreatraccion : this.atraccionesIncluidas) {
			mapaAtracciones.get(Nombreatraccion).decrementarCupo();
		}
	}
	
	//-- Overrides --
	
	@Override
	public String toString() {
		return "Promocion: "+ nombre + "\n" +
				"-Tiempo requerido: " + tiempoRequerido + "\n" + 
				"-Atracciones incluidas: " + atraccionesIncluidas;		
	}
}
