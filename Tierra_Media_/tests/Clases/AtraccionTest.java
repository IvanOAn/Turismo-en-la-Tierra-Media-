package Clases;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class AtraccionTest {

	@Test
	public void agregaBienRecomendacionAItinierario() {
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		Itinerario itinerario = new Itinerario();

		itinerario.agregarRecomendacion(atraccion);

		Assert.assertTrue(itinerario.getItinerario().containsKey(atraccion.getNombre()));
	}

	@Test
	public void agregaBienPromocionAItinierario() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Minas Tirith", 10, 3, 20, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Abismo de Helm", 25, 2, 50, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Erebor", 25, 2, 13, TipoDeAtraccion.PAISAJES));

		Promocion promocion = new Promocion("Pack Test", atracciones);

		Itinerario itinerario = new Itinerario();

		itinerario.agregarRecomendacion(promocion);

		Assert.assertTrue(itinerario.getItinerario().containsKey("Minas Tirith"));
		Assert.assertTrue(itinerario.getItinerario().containsKey("Abismo de Helm"));
		Assert.assertTrue(itinerario.getItinerario().containsKey("Erebor"));

	}

}
