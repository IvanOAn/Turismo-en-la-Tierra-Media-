package Clases;

import java.util.List;

public class PromocionesAbsolutas extends Promocion {

	public PromocionesAbsolutas(String nombre, List<Atraccion> atraccionesIncluidas, double precio) {
		super(nombre, atraccionesIncluidas);
		this.precio = precio;
	}

	@Override
	public String toString() {
		return super.toString() + "\n-Precio: $" + this.precio;
	}

}