package Clases;

import java.util.ArrayList;
import java.util.List;

public class PromocionesAxB extends Promocion {
	private String atraccionGratis;
	
	public PromocionesAxB(String nombre,int cupo, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<String> atraccionesIncluidas, double precio, String atraccionGratis) {
		super(nombre,cupo, tiempoRequerido, tipoDeAtraccion, atraccionesIncluidas);
		this.atraccionGratis = atraccionGratis;
		this.precio=precio;
	}
	
	@Override
	public String toString() {
		return super.toString()+"-AtraccionGratis=" + atraccionGratis + "\n";
	}

	@Override
	public int compareTo(Promocion o) {
		//TODO: definir como se ordenan las promociones
		return 0;
	}
	
}
