package Clases;

import java.util.Comparator;

public class ComparadorPromociones implements Comparator<Promocion> {

	private TipoDeAtraccion TipoOrden;
	
	public ComparadorPromociones(TipoDeAtraccion tipoOrden) {  
		TipoOrden = tipoOrden;
	}

	@Override
	public int compare(Promocion o1, Promocion o2) {
		if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal() // o1 coincide y o2 no
				&& o2.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal())
			return -1;
		
		else if (o1.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal() // o1 no coincide y o2 si
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal())
			return 1;
		
		else if (o1.getPrecio() == o2.getPrecio()) // o1 y o2 coincide en tipo y precio
			return Double.compare(o2.getTiempoRequerido(), o1.getTiempoRequerido());
			//return (int) (o2.getTiempoRequerido() - o1.getTiempoRequerido()); // comparas por tiempo
		
		return Double.compare(o2.getPrecio(), o1.getPrecio());
		//return (int) (o2.getPrecio() - o1.getPrecio()); // o1 y o2 coinciden en tipo, comparas por precio
	 }
}
