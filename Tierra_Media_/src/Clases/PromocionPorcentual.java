package Clases;

import java.util.List;

public class PromocionPorcentual extends Promocion {
	private double porcentajeDescuento;

	public PromocionPorcentual(String nombre, int cupo, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<String> atraccionesIncluidas, double precio, double porcentajeDescuento) {
		super(nombre, cupo, tiempoRequerido, tipoDeAtraccion, atraccionesIncluidas);
		this.porcentajeDescuento = porcentajeDescuento;
		this.precio = precio - precio * porcentajeDescuento / 100;
	}

	@Override
	public String toString() {
		return super.toString() + "-Porcentaje de descuento: " + porcentajeDescuento + "\n" + "-Precio sin Descuento: "
				+ (precio + precio * porcentajeDescuento / (100 - porcentajeDescuento)) + "\n"
				+ "-Precio con descuento: " + precio + "\n";
	}
}
