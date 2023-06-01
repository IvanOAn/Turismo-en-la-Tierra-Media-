package Clases;

import java.util.Comparator;

public class ComparadorPromociones implements Comparator<Promocion> {

	private TipoDeAtraccion TipoOrden;
	
	public ComparadorPromociones(TipoDeAtraccion tipoOrden) {  
		TipoOrden = tipoOrden;
	}

	@Override
	public int compare(Promocion o1, Promocion o2) {
		if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal())
			return -1;
		
		else if (o1.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal())
			return 1;
		
		else if (o1.getPrecio() == o2.getPrecio()) 
			return Double.compare(o2.getTiempoRequerido(), o1.getTiempoRequerido());
		
		return Double.compare(o2.getPrecio(), o1.getPrecio());
	 }
}
