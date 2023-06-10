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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}