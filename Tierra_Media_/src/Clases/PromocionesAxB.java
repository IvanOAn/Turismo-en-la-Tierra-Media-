package Clases;

import java.util.List;

public class PromocionesAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionesAxB(String nombre, List<Atraccion> atraccionesIncluidas, Atraccion atraccionGratis) {
		super(nombre, atraccionesIncluidas);
		this.atraccionGratis = atraccionGratis;
		this.duracion += atraccionGratis.getDuracion();
		//atraccionesIncluidas.add(atraccionGratis);
	}

	@Override
	public String toString() {
		return super.toString() + "\n-AtraccionGratis: " + this.atraccionGratis + "\n" + "-Precio: $" + this.precio;
	}
}
