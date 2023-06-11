package Clases;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class AtraccionTest {

	@Test
	public void agregaBienRecomendacionAItinierario() {
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		Usuario usuario = new Usuario("Frodo", 10, 20, TipoDeAtraccion.AVENTURA);

		atraccion.agregarRecomendacionAItinierario(usuario);

		Assert.assertTrue(usuario.getItinerario().containsKey(atraccion.getNombre()));
	}

	@Test
	public void agregaBienPromocionAItinierario() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Minas Tirith", 10, 3, 20, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Abismo de Helm", 25, 2, 50, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Erebor", 25, 2, 13, TipoDeAtraccion.PAISAJES));

		Promocion promocion = new Promocion("Pack Test", atracciones);

		Usuario usuario = new Usuario("Sam", 35, 10, TipoDeAtraccion.PAISAJES);

		promocion.agregarRecomendacionAItinierario(usuario);

		Assert.assertTrue(usuario.getItinerario().containsKey("Minas Tirith"));
		Assert.assertTrue(usuario.getItinerario().containsKey("Abismo de Helm"));
		Assert.assertTrue(usuario.getItinerario().containsKey("Erebor"));

	}
}
