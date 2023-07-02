package interfazUsuario;

import org.junit.Assert;
import org.junit.Test;
import recomendacion.Atraccion;
import tiposDeRecomendaciones.TipoDeAtraccion;
import usuario.Usuario;

import java.util.Scanner;

public class RecomendadorTest {

	@Test
	public void noOfreceRecomendacionSinCupo() {
		Recomendador recomendador = new Recomendador();

		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Scanner inputAfirmativo = new Scanner("S");
		Atraccion atraccionSinCupo = new Atraccion("Moria", 10, 2, 0, TipoDeAtraccion.AVENTURA);

		Assert.assertFalse(recomendador.ofrecerRecomendacion(usuario, atraccionSinCupo, inputAfirmativo));
	}

	@Test
	public void noOfreceRecomendacionUsuarioSinPresupuesto() {
		Recomendador recomendador = new Recomendador();

		Usuario usuarioSinPresupuesto = new Usuario("Frodo", 0, 10, TipoDeAtraccion.AVENTURA);
		Scanner inputAfirmativo = new Scanner("S");
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 5, TipoDeAtraccion.AVENTURA);

		Assert.assertFalse(recomendador.ofrecerRecomendacion(usuarioSinPresupuesto, atraccion, inputAfirmativo));
	}

	@Test
	public void noOfreceRecomendacionUsuarioSinTiempo() {
		Recomendador recomendador = new Recomendador();

		Usuario usuarioSinTiempo = new Usuario("Frodo", 30, 0, TipoDeAtraccion.AVENTURA);
		Scanner inputAfirmativo = new Scanner("S");
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 5, TipoDeAtraccion.AVENTURA);

		Assert.assertFalse(recomendador.ofrecerRecomendacion(usuarioSinTiempo, atraccion, inputAfirmativo));
	}

	@Test
	public void ofreceBienRecomendacionUsuarioCompra() {
		Recomendador recomendador = new Recomendador();

		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Scanner inputAfirmativo = new Scanner("S");
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 5, TipoDeAtraccion.AVENTURA);

		Assert.assertTrue(recomendador.ofrecerRecomendacion(usuario, atraccion, inputAfirmativo));
	}

	@Test
	public void ofreceBienRecomendacionUsuarioNoCompra() {
		Recomendador recomendador = new Recomendador();

		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		Scanner inputNegativo = new Scanner("N");
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 5, TipoDeAtraccion.AVENTURA);

		Assert.assertFalse(recomendador.ofrecerRecomendacion(usuario, atraccion, inputNegativo));
	}
}
