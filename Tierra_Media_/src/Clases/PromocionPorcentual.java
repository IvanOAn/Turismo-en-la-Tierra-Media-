package Clases;

import java.util.ArrayList;
import java.util.List;

public class PromocionPorcentual extends Promocion{
	private double porcentajeDescuento;
	
	public PromocionPorcentual(String nombre,int cupo, double tiempoRequerido, TipoDeAtraccion tipoDeAtraccion,
			List<String> atraccionesIncluidas, double precio, double porcentajeDescuento) {
		super(nombre,cupo, tiempoRequerido, tipoDeAtraccion, atraccionesIncluidas, precio);
		this.porcentajeDescuento = porcentajeDescuento;
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
