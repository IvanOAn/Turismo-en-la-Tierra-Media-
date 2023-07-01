package interfazUsuario;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import recomendacion.Atraccion;
import recomendacion.Promocion;
import recomendacion.PromocionesAbsolutas;
import recomendacion.PromocionesPorcentual;
import recomendacion.Recomendacion;
import tiposDeRecomendaciones.TipoDeAtraccion;
import usuario.Usuario;

public class ComparadorRecomendacionesTest {

	private Usuario usuario;
	private ArrayList<Atraccion> arrayAtracciones;
	private LinkedList<Promocion> listaPromociones;
	private LinkedList<Recomendacion> listaRecomendaciones;

	@Before
	public void setUp() {
		usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);
		arrayAtracciones = new ArrayList<Atraccion>();
		listaPromociones = new LinkedList<Promocion>();
		listaRecomendaciones = new LinkedList<Recomendacion>();
	}

	@Test
	public void ordenaBienAtraccionesPorPrecio() {
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Erebor", 21, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion2);
		arrayAtracciones.add(atraccion1);

		arrayAtracciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccion2, arrayAtracciones.get(0));
	}

	@Test
	public void ordenaBienAtraccionesPorTipo() {
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Erebor", 21, 3, 1, TipoDeAtraccion.PAISAJES);

		arrayAtracciones.add(atraccion2);
		arrayAtracciones.add(atraccion1);

		arrayAtracciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccion1, arrayAtracciones.get(0));
	}

	@Test
	public void ordenaBienAtraccionesPorTiempo() {
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Erebor", 10, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion2);
		arrayAtracciones.add(atraccion1);

		arrayAtracciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccion2, arrayAtracciones.get(0));
	}

	@Test
	public void ordenaBienPromocionesPorTipo() {
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 21, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion1);
		arrayAtracciones.add(atraccion2);

		PromocionesPorcentual promocion1 = new PromocionesPorcentual("Pack aventura", arrayAtracciones, 15);

		arrayAtracciones.clear();
		Atraccion atraccion3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.PAISAJES);
		Atraccion atraccion4 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.PAISAJES);

		arrayAtracciones.add(atraccion3);
		arrayAtracciones.add(atraccion4);

		PromocionesAbsolutas promocion2 = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 3500);

		listaPromociones.add(promocion2);
		listaPromociones.add(promocion1);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocion1, listaPromociones.get(0));
	}

	@Test
	public void ordenaBienPromocionesPorPrecio() {
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 21, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion1);
		arrayAtracciones.add(atraccion2);

		PromocionesPorcentual promocion1 = new PromocionesPorcentual("Pack aventura", arrayAtracciones, 15);

		arrayAtracciones.clear();
		Atraccion atraccion3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion4 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion3);
		arrayAtracciones.add(atraccion4);

		PromocionesAbsolutas promocion2 = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 3500);

		listaPromociones.add(promocion2);
		listaPromociones.add(promocion1);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocion2, listaPromociones.get(0));
	}

	@Test
	public void ordenaBienPromocionesPorTiempo() {
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 1020, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 2001, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion1);
		arrayAtracciones.add(atraccion2);

		PromocionesAbsolutas promocion1 = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 1000);

		arrayAtracciones.clear();
		Atraccion atraccion3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion4 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion3);
		arrayAtracciones.add(atraccion4);

		PromocionesAbsolutas promocion2 = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 1000);

		listaPromociones.add(promocion2);
		listaPromociones.add(promocion1);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocion2, listaPromociones.get(0));
	}

	@Test
	public void ordenaBienRecomendacionesPorTipo() {
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 1020, 2, 6, TipoDeAtraccion.DEGUSTACION);
		Atraccion atraccion2 = new Atraccion("Moria 2", 2001, 3, 1, TipoDeAtraccion.DEGUSTACION);

		arrayAtracciones.add(atraccion1);
		arrayAtracciones.add(atraccion2);

		PromocionesAbsolutas promocion1 = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 1000);

		arrayAtracciones.clear();
		Atraccion atraccion3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion3);

		listaRecomendaciones.add(atraccion3);
		listaRecomendaciones.add(promocion1);
		listaRecomendaciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccion3, listaRecomendaciones.get(0));
	}

	@Test
	public void ordenaBienRecomendacionesPorPrecio() {
		listaRecomendaciones.clear();
		arrayAtracciones.clear();
		Atraccion atraccion1 = new Atraccion("Moria", 1020, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 2001, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion1);
		arrayAtracciones.add(atraccion2);

		PromocionesAbsolutas promocion1 = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 1000);

		arrayAtracciones.clear();
		Atraccion atraccion3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccion3);

		listaRecomendaciones.add(atraccion3);
		listaRecomendaciones.add(promocion1);
		listaRecomendaciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocion1, listaRecomendaciones.get(0));
	}
}
