package Clases;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PromocionesAxBTest {

	@Test
	public void devuelvePrecioCorrecto() {

		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Isildur", 25, 2, 5, TipoDeAtraccion.AVENTURA));
		Atraccion gratis = new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA);

		PromocionesAxB promo = new PromocionesAxB("asd", atracciones, gratis);

		assertEquals(35, promo.getPrecio(), 0);
	}

	@Test
	public void devuelveTiempoCorrecto() {

		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Isildur", 25, 2, 5, TipoDeAtraccion.AVENTURA));
		Atraccion gratis = new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA);

		PromocionesAxB promo = new PromocionesAxB("asd", atracciones, gratis);

		assertEquals(9, promo.getDuracion(), 0);
	}

	@Test
	public void devuelveCupoCorrecto() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Isildur", 25, 2, 5, TipoDeAtraccion.AVENTURA));
		Atraccion gratis = new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA);

		PromocionesAxB promo = new PromocionesAxB("asd", atracciones, gratis);

		assertEquals(5, promo.getCupo(), 0);
	}
}