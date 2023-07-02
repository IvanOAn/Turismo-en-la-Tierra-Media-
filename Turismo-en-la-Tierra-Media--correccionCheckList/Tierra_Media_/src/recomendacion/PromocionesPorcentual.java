package recomendacion;

import java.util.List;
import java.util.Objects;

public class PromocionesPorcentual extends Promocion {
	private double porcentajeDescuento;

	public PromocionesPorcentual(String nombre, List<Atraccion> atraccionesIncluidas, double porcentajeDescuento) {
		super(nombre, atraccionesIncluidas);
		this.porcentajeDescuento = porcentajeDescuento;
		this.precio = precio - precio * porcentajeDescuento / 100;
	}

	@Override
	public String toString() {
		return super.toString() + "\n-Porcentaje de descuento: " + porcentajeDescuento + " %\n"
				+ "-Precio sin Descuento: $" + (precio + precio * porcentajeDescuento / (100 - porcentajeDescuento))
				+ "\n" + "-Precio con descuento: $" + precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(porcentajeDescuento);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PromocionesPorcentual other = (PromocionesPorcentual) obj;
		return Double.doubleToLongBits(porcentajeDescuento) == Double.doubleToLongBits(other.porcentajeDescuento);
	}

}