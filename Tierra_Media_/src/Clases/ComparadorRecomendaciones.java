package Clases;

import java.util.Comparator;

public class ComparadorRecomendaciones implements Comparator<Recomendacion> {
	
	private TipoDeAtraccion TipoOrden;
	
	public ComparadorRecomendaciones(TipoDeAtraccion tipoOrden) {
		TipoOrden = tipoOrden;
	}
	
	@Override
	public int compare(Recomendacion o1, Recomendacion o2) {
		
		if(o1.prioridad()<o2.prioridad()) {
			return -1;
		}
		else 
			if (o1.getTipoDeRecomendacion().ordinal() == TipoOrden.ordinal()
				&& o2.getTipoDeRecomendacion().ordinal() != TipoOrden.ordinal())
			return -1;
		else if (o1.getTipoDeRecomendacion().ordinal() != TipoOrden.ordinal()
				&& o2.getTipoDeRecomendacion().ordinal() == TipoOrden.ordinal())
			return 1;
		else if (o1.getPrecio() == o2.getPrecio()) 
				return (int) (o2.getTiempo() - o1.getTiempo());
		return (int) (o2.getPrecio() - o1.getPrecio());
		}
		
}

