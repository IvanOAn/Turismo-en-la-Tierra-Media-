package Clases;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PromocionesPorcentualTest {

	@Test
	public void devuelvePrecioConPorcentajeAplicado() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Minas Tirith", 10, 3, 20, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Abismo de Helm", 25, 2, 50, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Erebor", 25, 2, 13, TipoDeAtraccion.PAISAJES));

		PromocionesPorcentual promo = new PromocionesPorcentual("Pack Porcentual", atracciones, 50);

		assertEquals(30, promo.getPrecio(), 0);
	}

}