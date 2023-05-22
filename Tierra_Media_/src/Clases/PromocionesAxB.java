package Clases;

import java.util.ArrayList;

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

	@Override
	public int compareTo(Promocion o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
