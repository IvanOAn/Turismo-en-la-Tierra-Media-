package Clases;

import java.util.ArrayList;
import java.util.List;

public class PromocionesAbsolutas extends Promocion {

	public PromocionesAbsolutas(String nombre,int cupo, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<String> atraccionesIncluidas, double precio) {
		super(nombre,cupo, tiempoRequerido, tipoDeAtraccion, atraccionesIncluidas);
		this.precio=precio;
	}
  
	@Override
	public int compareTo(Promocion o) {
		//TODO: definir como se ordenan las promociones
		return 0;
	}	
	
}