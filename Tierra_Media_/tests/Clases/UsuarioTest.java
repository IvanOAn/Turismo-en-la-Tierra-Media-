package Clases;

import org.junit.Assert;

import java.util.LinkedList;

import org.junit.Test;

public class UsuarioTest {

	@Test
	public void deuvuelveBienEstado() {
		Usuario usuarioEstadoBien = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Usuario usuarioSinPresupuesto = new Usuario("Galardiel", 0, 5, TipoDeAtraccion.DEGUSTACION);
		Usuario usuarioSinTiempo = new Usuario("Galardiel", 100, 0, TipoDeAtraccion.DEGUSTACION);

		Assert.assertTrue(usuarioEstadoBien.estado());
		Assert.assertFalse(usuarioSinPresupuesto.estado());
		Assert.assertFalse(usuarioSinTiempo.estado());

	}

	@Test
	public void compraBienAtraccion() {
		double presupuesto = 30;
		double tiempoDisp = 10;
		Usuario usuario = new Usuario("Frodo", presupuesto, tiempoDisp, TipoDeAtraccion.AVENTURA);

		double precio = 10;
		double duracion = 2;
		int cupo = 6;

		Atraccion atraccion = new Atraccion("Moria", precio, duracion, cupo, TipoDeAtraccion.AVENTURA);

		double presupuestoEsperado = presupuesto - precio;
		double tiempoDispEsperado = tiempoDisp - duracion;
		int cupoEsperado = cupo - 1;

		usuario.comprarRecomendacion(atraccion);

		Assert.assertTrue(usuario.getItinerario().containsKey(atraccion.getNombre()));

		Assert.assertEquals(presupuestoEsperado, usuario.getPresupuesto(), 0.0001);
		Assert.assertEquals(tiempoDispEsperado, usuario.getTiempoDisponible(), 0.0001);
		Assert.assertEquals(cupoEsperado, atraccion.getCupo());
	}

	@Test
	public void compraBienPromocionPorcentual() {
		double presupuesto = 30;
		double tiempoDisp = 10;
		Usuario usuario = new Usuario("Frodo", presupuesto, tiempoDisp, TipoDeAtraccion.AVENTURA);

		double precio = 10;
		double duracion = 2;
		int cupo = 6;

		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();
		listaPromocion.add(new Atraccion("Moria", precio, duracion, cupo, TipoDeAtraccion.AVENTURA));
		listaPromocion.add(new Atraccion("Moria 2", precio, duracion, cupo, TipoDeAtraccion.AVENTURA));

		PromocionesPorcentual promocion = new PromocionesPorcentual("Pack aventura", listaPromocion, 20);

		double presupuestoEsperado = presupuesto - (2 * precio * 0.8);
		double tiempoDispEsperado = tiempoDisp - duracion * 2;
		int cupoEsperado = cupo - 1;

		usuario.comprarRecomendacion(promocion);

		for (int i = 0; i < listaPromocion.size(); i++) {
			Assert.assertTrue(usuario.getItinerario().containsKey(listaPromocion.get(i).getNombre()));
		}

		Assert.assertEquals(presupuestoEsperado, usuario.getPresupuesto(), 0.0001);
		Assert.assertEquals(tiempoDispEsperado, usuario.getTiempoDisponible(), 0.0001);
		Assert.assertEquals(cupoEsperado, promocion.getCupo());
	}

	@Test
	public void compraBienPromocionAbsoluta() {
		double presupuesto = 30;
		double tiempoDisp = 10;
		Usuario usuario = new Usuario("Frodo", presupuesto, tiempoDisp, TipoDeAtraccion.AVENTURA);

		double precio = 10;
		double duracion = 2;
		int cupo = 6;

		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();
		listaPromocion.add(new Atraccion("Moria", precio, duracion, cupo, TipoDeAtraccion.AVENTURA));
		listaPromocion.add(new Atraccion("Moria 2", precio, duracion, cupo, TipoDeAtraccion.AVENTURA));

		double precioPromo = 15;

		PromocionesAbsolutas promocion = new PromocionesAbsolutas("Pack aventura", listaPromocion, precioPromo);

		double presupuestoEsperado = presupuesto - precioPromo;
		double tiempoDispEsperado = tiempoDisp - duracion * 2;
		int cupoEsperado = cupo - 1;

		usuario.comprarRecomendacion(promocion);

		for (int i = 0; i < listaPromocion.size(); i++) {
			Assert.assertTrue(usuario.getItinerario().containsKey(listaPromocion.get(i).getNombre()));
		}

		Assert.assertEquals(presupuestoEsperado, usuario.getPresupuesto(), 0.0001);
		Assert.assertEquals(tiempoDispEsperado, usuario.getTiempoDisponible(), 0.0001);
		Assert.assertEquals(cupoEsperado, promocion.getCupo());
	}

	@Test
	public void compraBienPromocionAxB() {
		double presupuesto = 30;
		double tiempoDisp = 10;
		Usuario usuario = new Usuario("Frodo", presupuesto, tiempoDisp, TipoDeAtraccion.AVENTURA);

		double precio = 10;
		double duracion = 2;
		int cupo = 6;
		int cupoGratis = 3;

		Atraccion atraccionGratis = new Atraccion("Moria Gratis", precio, duracion, cupoGratis,
				TipoDeAtraccion.AVENTURA);

		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();
		listaPromocion.add(new Atraccion("Moria", precio, duracion, cupo, TipoDeAtraccion.AVENTURA));
		listaPromocion.add(new Atraccion("Moria 2", precio, duracion, cupo, TipoDeAtraccion.AVENTURA));

		PromocionesAxB promocion = new PromocionesAxB("Pack aventura", listaPromocion, atraccionGratis);

		double presupuestoEsperado = presupuesto - precio * 2;
		double tiempoDispEsperado = tiempoDisp - duracion * 3;
		int cupoEsperado = cupoGratis - 1;

		usuario.comprarRecomendacion(promocion);

		for (int i = 0; i < listaPromocion.size() - 1; i++) {
			Assert.assertTrue(usuario.getItinerario().containsKey(listaPromocion.get(i).getNombre()));
		}

		Assert.assertTrue(usuario.getItinerario().containsKey(atraccionGratis.getNombre()));

		Assert.assertEquals(presupuestoEsperado, usuario.getPresupuesto(), 0.0001);
		Assert.assertEquals(tiempoDispEsperado, usuario.getTiempoDisponible(), 0.0001);
		Assert.assertEquals(cupoEsperado, promocion.getCupo());
	}

	@Test
	public void devuelveBienRecomendacionDisponibleParaOfrecer() {
		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionComprada = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionNoComprada = new Atraccion("Moria2", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();

		listaPromocion.add(atraccionNoComprada);

		Promocion promocion = new Promocion("Pack prueba", listaPromocion);

		usuario.comprarRecomendacion(atraccionComprada);

		Assert.assertTrue(usuario.recomendacionDisponibleParaOfrecer(promocion));
		listaPromocion.add(atraccionComprada);
		Assert.assertFalse(usuario.recomendacionDisponibleParaOfrecer(promocion));
	}

}