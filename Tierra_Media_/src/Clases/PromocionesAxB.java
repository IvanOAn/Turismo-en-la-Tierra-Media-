package Clases;

import java.util.List;
import java.util.Objects;

public class PromocionesAxB extends Promocion {
	private Atraccion atraccionGratis;

	public PromocionesAxB(String nombre, List<Atraccion> atraccionesIncluidas, Atraccion atraccionGratis) {
		super(nombre, atraccionesIncluidas);
		this.atraccionGratis = atraccionGratis;
		this.duracion += atraccionGratis.getDuracion();
		atraccionesIncluidas.add(atraccionGratis);
	}

	@Override
	public String toString() {
		return super.toString() + "\n-AtraccionGratis: " + this.atraccionGratis.getNombre() + "\n" + "-Precio: $" + this.precio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionesAxB other = (PromocionesAxB) obj;
		return Objects.equals(atraccionGratis, other.atraccionGratis);
	}
	
	
}
