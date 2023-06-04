package Clases;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private double porcentajeDescuento;

	public PromocionPorcentual(String nombre, List<Atraccion> atraccionesIncluidas, double porcentajeDescuento) {
		super(nombre, atraccionesIncluidas);
		this.porcentajeDescuento = porcentajeDescuento;
		this.precio = precio - precio * porcentajeDescuento / 100;
	}

	@Override
	public String toString() {
		return super.toString() + "\n-Porcentaje de descuento: " + porcentajeDescuento + "\n"
				+ "-Precio sin Descuento: $" + (precio + precio * porcentajeDescuento / (100 - porcentajeDescuento))
				+ "\n" + "-Precio con descuento: $" + precio;
	}
}
