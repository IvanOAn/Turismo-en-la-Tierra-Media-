package Clases;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PromocionesAbsolutasTest {

	@Test
	public void devuelvePrecioCorrecto() {

		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("La Comarca", 5, 2, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Valinor", 50, 15, 2, TipoDeAtraccion.DEGUSTACION));

		PromocionesAbsolutas promo = new PromocionesAbsolutas("Pack Precio", atracciones, 45);

		assertEquals(45, promo.getPrecio(), 0);
	}

}