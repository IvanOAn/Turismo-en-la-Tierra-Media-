package Clases;

import java.util.Comparator;

public class ComparadorRecomendaciones implements Comparator<Recomendacion> {

	private TipoDeAtraccion TipoOrden;

	public ComparadorRecomendaciones(TipoDeAtraccion tipoOrden) {
		TipoOrden = tipoOrden;
	}

	@Override
	public int compare(Recomendacion o1, Recomendacion o2) {
		// Promociones que le gustan - Atracciones que le gustan - Promociones que no -
		// Atracciones que no

		// Comparas por tipo favorito
		if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal())
			return -1;

		else if (o1.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal())
			return 1;

		// aca coinciden los tipos, primero comparas por prioridad
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		} else if (!o1.esPromocion() && o2.esPromocion()) {
			return 1;
		}

		// aca coinciden los tipos y la prioridad
		if (o1.getPrecio() == o2.getPrecio())
			return Double.compare(o2.getDuracion(), o1.getDuracion());

		return Double.compare(o2.getPrecio(), o1.getPrecio());
	}

}