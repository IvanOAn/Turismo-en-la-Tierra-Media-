package Clases;

import java.util.ArrayList;
import java.util.List;

public class PromocionesAbsolutas extends Promocion {

	public PromocionesAbsolutas(String nombre, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<Atraccion> atraccionesIncluidas, double precio) {
		super(nombre,tiempoRequerido, tipoDeAtraccion, atraccionesIncluidas);
		this.precio = precio;
	}

	@Override
	public String toString() {
		return super.toString() + "\n-Precio: $" + this.precio;
	}
	
	
}