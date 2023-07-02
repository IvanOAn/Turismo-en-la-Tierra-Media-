package recomendacion;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import tiposDeRecomendaciones.TipoDeAtraccion;
import usuario.Usuario;

public class PromocionTest {

	@Test
	public void devuelveCupoBien() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Isildur", 25, 2, 0, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA));

		Promocion promocion = new Promocion("Pack Test", atracciones);

		Assert.assertEquals(0, promocion.getCupo());
	}

	@Test
	public void devuelveTiempoRequeridoBien() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Comarca", 10, 3, 20, TipoDeAtraccion.DEGUSTACION));
		atracciones.add(new Atraccion("Lothlorien", 25, 2, 0, TipoDeAtraccion.DEGUSTACION));

		Promocion promocion = new Promocion("Pack Test del Tiempo", atracciones);

		Assert.assertEquals(5, promocion.getDuracion(), 0.000001);
	}

	@Test
	public void devuelveTipoDeAtraccionAventuraBien() {
		List<Atraccion> atraccionesAventura = new LinkedList<Atraccion>();
		atraccionesAventura.add(new Atraccion("Moria 1", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atraccionesAventura.add(new Atraccion("Moria 2", 20, 4, 10, TipoDeAtraccion.AVENTURA));

		Promocion promocionAventura = new Promocion("Pack Paisajes", atraccionesAventura);

		Assert.assertEquals(TipoDeAtraccion.AVENTURA, promocionAventura.getTipoDeAtraccion());
	}

	@Test
	public void devuelveTipoDeAtraccionPaisajesBien() {
		List<Atraccion> atraccionesPaisajes = new LinkedList<Atraccion>();
		atraccionesPaisajes.add(new Atraccion("Erebor 1", 15, 1, 23, TipoDeAtraccion.PAISAJES));
		atraccionesPaisajes.add(new Atraccion("Erebor 2", 5, 4, 10, TipoDeAtraccion.PAISAJES));

		Promocion promocionPaisajes = new Promocion("Pack Paisajes", atraccionesPaisajes);

		Assert.assertEquals(TipoDeAtraccion.PAISAJES, promocionPaisajes.getTipoDeAtraccion());
	}

	@Test
	public void devuelveTipoDeAtraccionDegustacionBien() {

		List<Atraccion> atraccionesDegustacion = new LinkedList<Atraccion>();
		atraccionesDegustacion.add(new Atraccion("Comarca 1", 5, 5, 30, TipoDeAtraccion.DEGUSTACION));
		atraccionesDegustacion.add(new Atraccion("Comarca 2", 25, 4, 25, TipoDeAtraccion.DEGUSTACION));

		Promocion promocionDegustacion = new Promocion("Pack Paisajes", atraccionesDegustacion);

		Assert.assertEquals(TipoDeAtraccion.DEGUSTACION, promocionDegustacion.getTipoDeAtraccion());
	}

	@Test
	public void igualaPromocionesConMetodoEqualsBien() {
		List<Atraccion> atraccionesAventura = new LinkedList<Atraccion>();
		atraccionesAventura.add(new Atraccion("Isildur", 25, 2, 0, TipoDeAtraccion.AVENTURA));
		atraccionesAventura.add(new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA));

		Promocion promocion1 = new Promocion("Pack Aventura", atraccionesAventura);
		Promocion promocion2 = new Promocion("Pack Aventura", atraccionesAventura);

		Assert.assertTrue(promocion1.equals(promocion2));
	}

	@Test
	public void diferenciaPromocionesConMetodoEqualsBien() {
		List<Atraccion> atraccionesAventura = new LinkedList<Atraccion>();
		atraccionesAventura.add(new Atraccion("Isildur", 25, 2, 0, TipoDeAtraccion.AVENTURA));
		atraccionesAventura.add(new Atraccion("Gondor", 15, 4, 15, TipoDeAtraccion.AVENTURA));

		List<Atraccion> atraccionesPaisajes = new LinkedList<Atraccion>();
		atraccionesPaisajes.add(new Atraccion("Erebor 1", 15, 1, 23, TipoDeAtraccion.PAISAJES));
		atraccionesPaisajes.add(new Atraccion("Erebor 2", 5, 4, 10, TipoDeAtraccion.PAISAJES));

		Promocion promocion1 = new Promocion("Pack Aventura", atraccionesAventura);
		Promocion promocion2 = new Promocion("Pack Paisajes", atraccionesPaisajes);

		Assert.assertFalse(promocion1.equals(promocion2));
	}

	@Test
	public void detectaAtraccionesCompradasEnPromocionBien() {
		Usuario usuario = new Usuario("Frodo", 50, 10, TipoDeAtraccion.AVENTURA);

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Isildur", 25, 2, 0, TipoDeAtraccion.AVENTURA));

		Promocion promocion = new Promocion("Pack Aventura", atracciones);
		usuario.comprarRecomendacion(atracciones.get(0));

		Assert.assertTrue(promocion.recomendacionIncluyeAtraccionComprada(usuario));
	}

	@Test
	public void decrementaCupoPromocionBien() {
		List<Atraccion> atraccion = new LinkedList<Atraccion>();
		atraccion.add(new Atraccion("Abismo de Helm", 25, 2, 1, TipoDeAtraccion.PAISAJES));

		Promocion promocion = new Promocion("Pack del Abismo", atraccion);
		promocion.decrementarCupo();

		Assert.assertEquals(0, promocion.getCupo(), 0);
	}

	@Test
	public void decrementaCupoAtraccionDePromocionBien() {
		Usuario usuario = new Usuario("Frodo", 50, 10, TipoDeAtraccion.AVENTURA);

		Atraccion atraccionCupo20 = new Atraccion("Moria", 10, 3, 20, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionCupo1 = new Atraccion("Isildur", 25, 2, 1, TipoDeAtraccion.AVENTURA);

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		atracciones.add(atraccionCupo20);
		atracciones.add(atraccionCupo1);

		Promocion promocion = new Promocion("Pack Aventura", atracciones);
		usuario.comprarRecomendacion(promocion);

		Assert.assertEquals(0, atraccionCupo1.getCupo());
	}
}