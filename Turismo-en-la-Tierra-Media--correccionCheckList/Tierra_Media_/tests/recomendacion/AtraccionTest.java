package recomendacion;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import tiposDeRecomendaciones.TipoDeAtraccion;
import usuario.Usuario;

public class AtraccionTest {

	@Test
	public void agregaBienAtraccionAItinierario() {
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		Usuario usuario = new Usuario("Frodo", 10, 20, TipoDeAtraccion.AVENTURA);

		atraccion.agregarRecomendacionAItinierario(usuario);

		Assert.assertTrue(usuario.tieneAtraccion(atraccion));
	}

	@Test
	public void agregaBienAtraccionesDePromocionAItinierario() {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();

		atracciones.add(new Atraccion("Minas Tirith", 10, 3, 20, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Abismo de Helm", 25, 2, 50, TipoDeAtraccion.PAISAJES));
		atracciones.add(new Atraccion("Erebor", 25, 2, 13, TipoDeAtraccion.PAISAJES));

		Promocion promocion = new Promocion("Pack Test", atracciones);

		Usuario usuario = new Usuario("Sam", 35, 10, TipoDeAtraccion.PAISAJES);

		promocion.agregarRecomendacionAItinierario(usuario);

		Assert.assertTrue(usuario.tieneAtraccion(atracciones.get(0)));
		Assert.assertTrue(usuario.tieneAtraccion(atracciones.get(1)));
		Assert.assertTrue(usuario.tieneAtraccion(atracciones.get(2)));
	}
}
