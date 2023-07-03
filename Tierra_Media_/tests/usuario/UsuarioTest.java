package usuario;

import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;
import recomendacion.Atraccion;
import recomendacion.Promocion;
import recomendacion.PromocionesAbsolutas;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class UsuarioTest {

	@Test
	public void deuvuelveBienEstado() {
		Usuario usuarioEstadoBien = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Assert.assertTrue(usuarioEstadoBien.estado());
	}

	@Test
	public void deuvuelveBienEstadoSinTiempo() {
		Usuario usuarioSinTiempo = new Usuario("Galardiel", 100, 0, TipoDeAtraccion.DEGUSTACION);
		Assert.assertFalse(usuarioSinTiempo.estado());
	}

	@Test
	public void deuvuelveBienEstadoSinPresupuesto() {
		Usuario usuarioSinPresupuesto = new Usuario("Galardiel", 0, 5, TipoDeAtraccion.DEGUSTACION);
		Assert.assertFalse(usuarioSinPresupuesto.estado());
	}

	@Test
	public void compraBienAtraccionPrecio() {

		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		usuario.comprarRecomendacion(atraccion);

		Assert.assertEquals(20, usuario.getPresupuesto(), 0.0001);
	}

	@Test
	public void compraBienAtraccionTiempo() {

		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		usuario.comprarRecomendacion(atraccion);

		Assert.assertEquals(8, usuario.getTiempoDisponible(), 0.0001);
	}

	@Test
	public void compraBienPromocionAbsolutaTiempo() {

		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);

		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();
		listaPromocion.add(atraccion1);
		listaPromocion.add(atraccion2);

		PromocionesAbsolutas promocion = new PromocionesAbsolutas("Pack aventura", listaPromocion, 15);
		usuario.comprarRecomendacion(promocion);

		Assert.assertEquals(6, usuario.getTiempoDisponible(), 0.0001);
	}

	@Test
	public void compraBienPromocionAbsolutaPrecio() {

		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);

		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();
		listaPromocion.add(atraccion1);
		listaPromocion.add(atraccion2);

		PromocionesAbsolutas promocion = new PromocionesAbsolutas("Pack aventura", listaPromocion, 15);
		usuario.comprarRecomendacion(promocion);

		Assert.assertEquals(15, usuario.getPresupuesto(), 0.0001);
	}

	@Test
	public void devuelveBienPromocionConAtraccionYaComprada() {
		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);

		Atraccion atraccionComprada = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionNoComprada = new Atraccion("Moria2", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();

		listaPromocion.add(atraccionNoComprada);
		listaPromocion.add(atraccionComprada);

		Promocion promocion = new Promocion("Pack prueba", listaPromocion);

		usuario.comprarRecomendacion(atraccionComprada);

		Assert.assertTrue(promocion.recomendacionIncluyeAtraccionComprada(usuario));
	}

}