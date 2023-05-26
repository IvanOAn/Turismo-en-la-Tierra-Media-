package Clases;

import java.util.Set;
import java.util.TreeSet;

public class Atraccion implements Comparable<Atraccion> {

	private String nombre;
	private double costo;
	private double tiempo;
	private int cupo;
	private TipoDeAtraccion tipoDeAtraccion;

	//-- Constructor --
	public Atraccion(String nombre, double costo, double tiempo, int cupo, TipoDeAtraccion tipoDeAtraccion) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipoDeAtraccion = tipoDeAtraccion;
	}
	
	//-- Getters --
	public double getCosto() {
		return costo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public int getCupo() {
		return cupo;
	}

	public String getNombre() {
		return nombre;
	}

	//-- Métodos --
	public static Set<Atraccion> atraccionesDisponibles(Usuario usuario, Set<Atraccion> treeSet) {

		Set<Atraccion> res = new TreeSet<>();

		for (Atraccion atraccion : treeSet) {
			if (atraccion.getCosto() <= usuario.getPresupuesto()
					&& atraccion.getTiempo() <= usuario.getTiempoDisponible() && atraccion.getCupo() > 0
					&& !usuario.atraccionValida(atraccion))
				res.add(atraccion);
		}
		return res;
	}
	
	//Esto podría ser una excepción en vez de un if
	public void decrementarCupo() {
		if (this.cupo > 0)
			this.cupo = this.cupo - 1;
	}

	//-- Overrides --
	
	//El orden natural de las atracciones será de mayor a menor costo
	@Override
	public int compareTo(Atraccion o) {
		return (int) (o.costo - this.costo);
	}
	
	@Override
	public String toString() {
		return "Atraccion\n" +
				"Nombre: [" + nombre + "]\n" +
				"-Precio: $" + costo + "\n" +
				"-Duración: " + tiempo + " horas";
	}
}