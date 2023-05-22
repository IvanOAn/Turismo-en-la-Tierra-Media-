package Clases;

import java.util.Comparator;

public class ComparadorAtracciones implements Comparator<Atraccion>{
	
	private TipoDeAtraccion TipoOrden;
	
	public ComparadorAtracciones(TipoDeAtraccion tipoOrden) {
		TipoOrden = tipoOrden;
	}

	@Override
	public int compare(Atraccion o1, Atraccion o2) {
		if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal())
			return -1;
		else if (o1.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal())
			return 1;
		else if (o1.getCosto() == o2.getCosto()) 
				return (int) (o2.getTiempo() - o1.getTiempo());
		return (int) (o2.getCosto() - o1.getCosto());
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
