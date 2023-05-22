package Clases;

import java.util.Set;
import java.util.TreeSet;

public class Atraccion implements Comparable<Atraccion> {

	private String nombre;
	private double costo;
	private double tiempo;
	private int cupo;
	private TipoDeAtraccion tipoDeAtraccion;

	public Atraccion(String nombre, double costo, double tiempo, int cupo, TipoDeAtraccion tipoDeAtraccion) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipoDeAtraccion = tipoDeAtraccion;
	}

	@Override
	public String toString() {
		return "Atraccion [nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + ", cupo=" + cupo
				+ ", tipoDeAtraccion=" + tipoDeAtraccion + "]";
	}

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

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public String getNombre() {
		return nombre;
	}

	public static Set<Atraccion> atraccionesDisponibles(Usuario usuario, Set<Atraccion> treeSet) {

		Set<Atraccion> res = new TreeSet<>();

		for (Atraccion atraccion : treeSet) {
			if (atraccion.getCosto() < usuario.getPresupuesto() && atraccion.getTiempo() < usuario.getTiempoDisponible()
					&& atraccion.getCupo() > 0 && !usuario.atraccionYaElegida(atraccion))
				res.add(atraccion);
		}
		return res;
	}

	public void decrementarCupo() {
		this.cupo = this.cupo - 1;
	}

	@Override
	// TODO: Cambiar esto a algo que tenga más sentido, de momento se comparan las
	// atracciones de acuerdo a su precio
	public int compareTo(Atraccion o) {
		return (int) (this.costo - o.costo);
	}
}