package Clases;

import java.util.HashSet;
import java.util.Objects;

public class Atraccion extends Recomendacion{

	private int cupo;

	// -- Constructor --
	public Atraccion(String nombre, double precio, double duracion, int cupo, TipoDeAtraccion tipoDeAtraccion) {
		super(nombre,precio,duracion,tipoDeAtraccion);
		this.cupo = cupo;
	}

	// -- Getters --
	public int getCupo() {
		return cupo;
	}
	
	// -- Overrides --
	@Override
	public String toString() {
		return "\nAtraccion: " + this.nombre + "\n" + "-Precio: $" + precio + "\n" + "-Duración: " + duracion + "\n"
				+ "-Tipo de atraccion: " + tipoDeAtraccion;
	}
	
	@Override
	public void decrementarCupo() {
		this.cupo -= 1;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return cupo == other.cupo;
	}
}