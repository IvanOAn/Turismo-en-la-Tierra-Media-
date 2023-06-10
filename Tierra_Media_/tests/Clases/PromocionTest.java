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

		assertEquals(5, promocion.getDuracion(), 0);
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
		List<Atraccion> atraccionesAventura = new LinkedList<Atraccion>();
		atraccionesAventura.add(new Atraccion("Moria 1", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atraccionesAventura.add(new Atraccion("Moria 2", 20, 4, 10, TipoDeAtraccion.AVENTURA));

		List<Atraccion> atraccionesPaisajes = new LinkedList<Atraccion>();
		atraccionesPaisajes.add(new Atraccion("Erebor 1", 15, 1, 23, TipoDeAtraccion.PAISAJES));
		atraccionesPaisajes.add(new Atraccion("Erebor 2", 5, 4, 10, TipoDeAtraccion.PAISAJES));

		List<Atraccion> atraccionesDegustacion = new LinkedList<Atraccion>();
		atraccionesDegustacion.add(new Atraccion("Comarca 1", 5, 5, 30, TipoDeAtraccion.DEGUSTACION));
		atraccionesDegustacion.add(new Atraccion("Comarca 2", 25, 4, 25, TipoDeAtraccion.DEGUSTACION));

		Promocion promocionAventura = new Promocion("Pack Paisajes", atraccionesAventura);
		Promocion promocionPaisajes = new Promocion("Pack Paisajes", atraccionesPaisajes);
		Promocion promocionDegustacion = new Promocion("Pack Paisajes", atraccionesDegustacion);

		assertEquals(TipoDeAtraccion.AVENTURA, promocionAventura.getTipoDeAtraccion());
		assertEquals(TipoDeAtraccion.PAISAJES, promocionPaisajes.getTipoDeAtraccion());
		assertEquals(TipoDeAtraccion.DEGUSTACION, promocionDegustacion.getTipoDeAtraccion());
	}

	@Test
	public void decrementaCupoBien() {
		List<Atraccion> atraccion = new LinkedList<Atraccion>();
		atraccion.add(new Atraccion("Abismo de Helm", 25, 2, 1, TipoDeAtraccion.PAISAJES));

		Promocion promocion = new Promocion("Pack del Abismo", atraccion);
		promocion.decrementarCupo();

		assertEquals(0, promocion.getCupo(), 0);
	}

	@Test
	public void detectaAtraccionesCompradas() {
		Usuario usuario = new Usuario("Frodo", 50, 10, TipoDeAtraccion.AVENTURA);

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Isildur", 25, 2, 0, TipoDeAtraccion.AVENTURA));

		Promocion promocion = new Promocion("Pack Aventura", atracciones);

		assertFalse(promocion.recomendacionIncluyeAtraccionComprada(usuario));

		usuario.comprarRecomendacion(atracciones.get(0));

		assertTrue(promocion.recomendacionIncluyeAtraccionComprada(usuario));
	}

	@Test
	public void agregaRecomendacionesBien() {
		Itinerario itinerario = new Itinerario();

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		atracciones.add(new Atraccion("Comarca", 10, 3, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Lothlorien", 25, 2, 0, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Comarca 2", 10, 3, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Lothlorien 2", 25, 2, 0, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Comarca 3", 10, 3, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Lothlorien 3", 25, 2, 0, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Comarca 4", 10, 3, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Lothlorien 4", 25, 2, 0, TipoDeAtraccion.DEGUSTACION));

		Promocion promocion = new Promocion("Pack Degustación", atracciones);

		promocion.agregarRecomendacionAItinierario(itinerario);

		for (Atraccion atraccion : promocion.atraccionesIncluidas) {
			assertTrue(itinerario.getItinerario().containsValue(atraccion));
		}

	}

	@Test
	public void igualaPromocionesBien() {
		List<Atraccion> atraccionesAventura = new LinkedList<Atraccion>();
		atraccionesAventura.add(new Atraccion("Isildur", 25, 2, 0, TipoDeAtraccion.AVENTURA));
		atraccionesAventura.add(new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA));

		List<Atraccion> atraccionesPaisajes = new LinkedList<Atraccion>();
		atraccionesPaisajes.add(new Atraccion("Erebor 1", 15, 1, 23, TipoDeAtraccion.PAISAJES));
		atraccionesPaisajes.add(new Atraccion("Erebor 2", 5, 4, 10, TipoDeAtraccion.PAISAJES));

		Promocion promocion1 = new Promocion("Primera Promocion", atraccionesAventura);
		Promocion promocion2 = new Promocion("Segunda Promocion", atraccionesPaisajes);

		assertFalse(promocion1.equals(promocion2));

		promocion2 = new Promocion("Primera Promocion", atraccionesAventura);

		assertTrue(promocion1.equals(promocion2));
	}
}