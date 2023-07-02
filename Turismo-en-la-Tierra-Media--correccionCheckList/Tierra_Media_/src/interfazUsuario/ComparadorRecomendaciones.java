package interfazUsuario;

import java.util.Comparator;

import recomendacion.Recomendacion;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class ComparadorRecomendaciones implements Comparator<Recomendacion> {

	private TipoDeAtraccion TipoOrden;

	public ComparadorRecomendaciones(TipoDeAtraccion tipoOrden) {
		TipoOrden = tipoOrden;
	}

	@Override
	public int compare(Recomendacion o1, Recomendacion o2) {

		if (o1.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal()) {
			return -1;
		} else if (o1.getTipoDeAtraccion().ordinal() != TipoOrden.ordinal()
				&& o2.getTipoDeAtraccion().ordinal() == TipoOrden.ordinal()) {
			return 1;
		}
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		} else if (!o1.esPromocion() && o2.esPromocion()) {
			return 1;
		}

		if (o1.getPrecio() == o2.getPrecio()) {
			return Double.compare(o2.getDuracion(), o1.getDuracion());
		}
		return Double.compare(o2.getPrecio(), o1.getPrecio());
	}
}