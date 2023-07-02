package recomendacion;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class PromocionesAbsolutasTest {

	@Test
	public void devuelvePrecioCorrecto() {

		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("La Comarca", 5, 2, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Valinor", 50, 15, 2, TipoDeAtraccion.DEGUSTACION));

		PromocionesAbsolutas promoAbsoluta = new PromocionesAbsolutas("Pack Degustacion", atracciones, 45);

		Assert.assertEquals(45, promoAbsoluta.getPrecio(), 0);
	}

}