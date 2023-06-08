package Clases;

import java.util.Objects;

public abstract class Recomendacion{
	protected String nombre;
	protected double precio;
	protected double duracion;
	protected TipoDeAtraccion tipoDeAtraccion;
	
	public Recomendacion() {}
	
	public Recomendacion(String nombre, double precio, double duracion, TipoDeAtraccion tipoDeAtraccion) {
		this.nombre = nombre;
		this.precio = precio;
		this.duracion = duracion;
		this.tipoDeAtraccion = tipoDeAtraccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public double getDuracion() {
		return duracion;
	}
	
	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public abstract int getCupo();
	
	public abstract void decrementarCupo();
	
	public abstract boolean esPromocion();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recomendacion other = (Recomendacion) obj;
		return Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& tipoDeAtraccion == other.tipoDeAtraccion;
	}
}
