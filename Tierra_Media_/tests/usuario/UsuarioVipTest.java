package usuario;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import recomendacion.Atraccion;
import recomendacion.PromocionesAbsolutas;
import tiposDeRecomendaciones.TipoDeAtraccion;

public class UsuarioVipTest {

	@Test
	public void compraAtraccionConCupon() {
		UsuarioVip usuario = new UsuarioVip("Frodo", 30, 10, TipoDeAtraccion.AVENTURA, 1);
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		usuario.comprarRecomendacion(atraccion);

		Assert.assertEquals(0, usuario.getCupones());
	}
	
	@Test
	public void compraPromocionesConCupones() {
		UsuarioVip usuario = new UsuarioVip("Frodo", 30, 10, TipoDeAtraccion.AVENTURA,2);

		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		LinkedList<Atraccion> listaPromocion = new LinkedList<Atraccion>();
		listaPromocion.add(atraccion1);
		listaPromocion.add(atraccion2);

		PromocionesAbsolutas promocion = new PromocionesAbsolutas("Pack aventura", listaPromocion, 15);
		usuario.comprarRecomendacion(promocion);

		Assert.assertEquals(1, usuario.getCupones());
	}
	
	@Test
	public void compraAtraccionConCuponSinAfectarPresupuesto() {
		UsuarioVip usuario = new UsuarioVip("Frodo", 30, 10, TipoDeAtraccion.AVENTURA, 1);
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		usuario.comprarRecomendacion(atraccion);

		Assert.assertEquals(usuario.getPresupuestoInicial(), usuario.getPresupuesto(),0);
	}
	
	@Test
	public void compraAtraccionConCuponAfectandoPresupuesto() {
		UsuarioVip usuario = new UsuarioVip("Frodo", 30, 10, TipoDeAtraccion.AVENTURA, 0);
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		usuario.comprarRecomendacion(atraccion);

		Assert.assertEquals(20, usuario.getPresupuesto(),0);
	}
}
