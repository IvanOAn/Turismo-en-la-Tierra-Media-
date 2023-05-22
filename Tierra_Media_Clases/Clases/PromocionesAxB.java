package Clases;

import java.util.ArrayList;
import java.util.HashMap;

public class PromocionesAxB extends Promocion {
	private Atraccion atraccionGratis;
	
	public PromocionesAxB(ArrayList<Atraccion> atraccionesIncluidas){
		super(atraccionesIncluidas);
		this.atraccionGratis=atraccionesIncluidas.get(atraccionesIncluidas.size()-1);
		this.precio=this.precio-atraccionesIncluidas.get(atraccionesIncluidas.size()-1).getCosto();
	}

	@Override
	public String toString() {
		return super.toString()+"PromocionesAxB [atraccionGratis=" + atraccionGratis + "]";
	}
	
}
