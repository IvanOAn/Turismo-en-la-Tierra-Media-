package Clases;

import java.util.ArrayList;
import java.util.HashMap;

public class PromocionesAbsolutas extends Promocion {

	public PromocionesAbsolutas(ArrayList<Atraccion> atraccionesIncluidas,double precio) {
		super(atraccionesIncluidas);
		this.precio = precio;
	}	
	
}
