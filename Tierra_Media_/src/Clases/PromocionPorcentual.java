package Clases;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion{
	private double porcentajeDescuento;
	
	public PromocionPorcentual(ArrayList<Atraccion> atraccionesIncluidas,double porcentajeDescuento) {
		super(atraccionesIncluidas);
		this.precio = this.precio-(this.precio*porcentajeDescuento/100);
	}

	@Override
	public String toString() {
		return super.toString()+"PromocionPorcentual [porcentajeDescuento=" + porcentajeDescuento + "]";
	}

	@Override
	public int compareTo(Promocion o) {
		//TODO: definir como se ordenan las promociones
		return 0;
	}
	
}
