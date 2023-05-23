package Clases;

import java.util.ArrayList;

public class PromocionesAbsolutas extends Promocion {

	public PromocionesAbsolutas(ArrayList<Atraccion> atraccionesIncluidas,double precio) {
		super(atraccionesIncluidas);
		this.precio = precio;
	}

	@Override
	public int compareTo(Promocion o) {
		//TODO: definir como se ordenan las promociones
		return 0;
	}	
	
}