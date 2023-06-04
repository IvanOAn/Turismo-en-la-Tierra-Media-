package Clases;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PromocionTest {

	@Test
	public void devuelveCupoBien() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Isildur", 25, 2, 0, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA));

		Promocion promocion = new Promocion("Pack Test", atracciones);

		assertEquals(0, promocion.getCupo());
	}

	@Test
	public void devuelveTiempoRequeridoBien() {

		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Comarca", 10, 3, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Lothlorien", 25, 2, 0, TipoDeAtraccion.DEGUSTACION));

		Promocion promocion = new Promocion("Pack Test del Tiempo", atracciones);

		assertEquals(5, promocion.getTiempo(), 0);
	}

	@Test
	public void devuelveAtraccionesIncluidasBien() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Minas Tirith", 10, 3, 20, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Abismo de Helm", 25, 2, 50, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Erebor", 25, 2, 13, TipoDeAtraccion.PAISAJES));

		Promocion promocion = new Promocion("Pack Test de las Atracciones Bien Incluidas", atracciones);

		assertEquals(atracciones, promocion.getAtraccionesIncluidas());
	}

	@Test
	public void devuelveTipoDeAtraccionBien() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));

		Promocion promocion = new Promocion("Pack Tipo", atracciones);
		assertEquals(TipoDeAtraccion.AVENTURA, promocion.getTipoDeAtraccion());
	}

}
