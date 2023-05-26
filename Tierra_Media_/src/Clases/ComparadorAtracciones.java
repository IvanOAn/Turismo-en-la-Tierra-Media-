package Clases;

import java.util.Comparator;

public class ComparadorAtracciones implements Comparator<Atraccion>{
	
	private TipoDeAtraccion TipoOrden;
	
	public ComparadorAtracciones(TipoDeAtraccion tipoOrden) {
		TipoOrden = tipoOrden;
	}

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
		if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal() // o1 coincide y o2 no
				&& o2.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal())
			return -1;
		
		else if (o1.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal() /// o1 no coincide y o2 si
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal())
			return 1;
		
		else if (o1.getCosto() == o2.getCosto()) // o1 y o2 coinciden en tipo y costo
			return Double.compare(o2.getTiempo(), o1.getTiempo());
			//return (int) (o2.getTiempo() - o1.getTiempo()); // comparas por tiempo
		
		return Double.compare(o2.getCosto(), o1.getCosto());
		//return (int) (o2.getCosto() - o1.getCosto()); // o1 y o2 coinciden en tipo, comparas por costo
	 	}
	
	/*@Override
	public int compare(Atraccion o1, Atraccion o2) {
		if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()) {
			if (o1.getCosto() == o2.getCosto())
				return (int) (o2.getTiempo() - o1.getTiempo());
			else
				return (int) (o2.getCosto() - o1.getCosto());
		} else if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal())
			return -1;
		else if (o1.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal())
			return 1;
		else if (o1.getCosto() == o2.getCosto()) 
				return (int) (o2.getTiempo() - o1.getTiempo());
			else
				return (int) (o2.getCosto() - o1.getCosto());
	 	}*/
}
