package Clases;

public abstract class Recomendacion implements Recomendable {
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

	@Override
	public abstract int getCupo();

	@Override
	public abstract void decrementarCupo();

	@Override
	public abstract boolean esPromocion();
}
