package Clases;

import java.util.List;

public class PromocionesAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionesAxB(String nombre, List<Atraccion> atraccionesIncluidas, Atraccion atraccionGratis) {
		super(nombre, atraccionesIncluidas);
		this.atraccionGratis = atraccionGratis;
		this.tiempoRequerido += atraccionGratis.getTiempo();
	}

	@Override
	public String toString() {
		return super.toString() + "\n-AtraccionGratis: " + this.atraccionGratis + "\n" + "-Precio: $" + this.precio;
	}
}
