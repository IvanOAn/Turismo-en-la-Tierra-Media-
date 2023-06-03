package Clases;

import java.util.ArrayList;
import java.util.List;

public class PromocionesAxB extends Promocion {
	private String atraccionGratis;

	public PromocionesAxB(String nombre,double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<Atraccion> atraccionesIncluidas, double precio, String atraccionGratis) {
		super(nombre,tiempoRequerido, tipoDeAtraccion, atraccionesIncluidas);
		this.atraccionGratis = atraccionGratis;
		this.precio = precio;
	}

	@Override
	public String toString() {
		return super.toString() + "\n-AtraccionGratis: " + this.atraccionGratis + "\n" + 
					"-Precio: $" + this.precio;
	}
}
