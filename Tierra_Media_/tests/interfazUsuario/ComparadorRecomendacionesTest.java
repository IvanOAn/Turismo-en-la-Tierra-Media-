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
		Atraccion atraccionMenorPrecio = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionMayorPrecio = new Atraccion("Erebor", 21, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionMenorPrecio);
		arrayAtracciones.add(atraccionMayorPrecio);

		arrayAtracciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccionMayorPrecio, arrayAtracciones.get(0));
	}

	@Test
	public void ordenaBienAtraccionesPorTipo() {
		arrayAtracciones.clear();
		Atraccion atraccionAventura = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionPaisaje = new Atraccion("Erebor", 21, 3, 1, TipoDeAtraccion.PAISAJES);

		arrayAtracciones.add(atraccionPaisaje);
		arrayAtracciones.add(atraccionAventura);

		arrayAtracciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccionAventura, arrayAtracciones.get(0));
	}

	@Test
	public void ordenaBienAtraccionesPorTiempo() {
		arrayAtracciones.clear();
		Atraccion atraccionMenosTiempo = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionMasTiempo = new Atraccion("Erebor", 10, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionMenosTiempo);
		arrayAtracciones.add(atraccionMasTiempo);

		arrayAtracciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccionMasTiempo, arrayAtracciones.get(0));
	}

	@Test
	public void ordenaBienPromocionesPorTipo() {
		arrayAtracciones.clear();
		Atraccion atraccionAventura1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionAventura2 = new Atraccion("Moria 2", 21, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionAventura1);
		arrayAtracciones.add(atraccionAventura2);

		PromocionesPorcentual promocionAventura = new PromocionesPorcentual("Pack aventura", arrayAtracciones, 15);

		arrayAtracciones.clear();
		Atraccion atraccionPaisajes1 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.PAISAJES);
		Atraccion atraccionPaisajes2 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.PAISAJES);

		arrayAtracciones.add(atraccionPaisajes1);
		arrayAtracciones.add(atraccionPaisajes2);

		PromocionesAbsolutas promocionPaisajes = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 3500);

		listaPromociones.add(promocionAventura);
		listaPromociones.add(promocionPaisajes);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocionAventura, listaPromociones.get(0));
	}

	@Test
	public void ordenaBienPromocionesPorPrecio() {
		arrayAtracciones.clear();
		Atraccion atraccionAventura1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionAventura2 = new Atraccion("Moria 2", 21, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionAventura1);
		arrayAtracciones.add(atraccionAventura2);

		PromocionesPorcentual promocionMenorPrecio = new PromocionesPorcentual("Pack aventura", arrayAtracciones, 15);

		arrayAtracciones.clear();
		Atraccion atraccionAventura3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionAventura4 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionAventura3);
		arrayAtracciones.add(atraccionAventura4);

		PromocionesAbsolutas promocionMayorPrecio = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 3500);

		listaPromociones.add(promocionMayorPrecio);
		listaPromociones.add(promocionMenorPrecio);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocionMayorPrecio, listaPromociones.get(0));
	}

	@Test
	public void ordenaBienPromocionesPorTiempo() {
		arrayAtracciones.clear();
		Atraccion atraccionAventura1 = new Atraccion("Moria", 100, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionAventura2 = new Atraccion("Moria 2", 200, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionAventura1);
		arrayAtracciones.add(atraccionAventura2);

		PromocionesAbsolutas promocionMenorTiempo = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 1000);

		arrayAtracciones.clear();
		Atraccion atraccionAventura3 = new Atraccion("Erebor 1", 2100, 5, 2, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionAventura4 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionAventura3);
		arrayAtracciones.add(atraccionAventura4);

		PromocionesAbsolutas promocionMayorTiempo = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 1000);

		listaPromociones.add(promocionMayorTiempo);
		listaPromociones.add(promocionMenorTiempo);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocionMayorTiempo, listaPromociones.get(0));
	}

	@Test
	public void ordenaBienRecomendacionesPorTipoFavoritoDelUsuario() {
		arrayAtracciones.clear();
		Atraccion atraccionDesgustacion1 = new Atraccion("Moria", 1020, 2, 6, TipoDeAtraccion.DEGUSTACION);
		Atraccion atraccionDesgustacion2 = new Atraccion("Moria 2", 2001, 3, 1, TipoDeAtraccion.DEGUSTACION);

		arrayAtracciones.add(atraccionDesgustacion1);
		arrayAtracciones.add(atraccionDesgustacion2);

		PromocionesAbsolutas promocionDegustacion = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 1000);

		Atraccion atraccionAventura = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.AVENTURA);

		listaRecomendaciones.add(atraccionAventura);
		listaRecomendaciones.add(promocionDegustacion);
		listaRecomendaciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(atraccionAventura, listaRecomendaciones.get(0));
	}

	@Test
	public void ordenaBienRecomendacionesMismoTipoDeAtraccion() {
		listaRecomendaciones.clear();
		arrayAtracciones.clear();
		Atraccion atraccionAventura1 = new Atraccion("Moria", 1020, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccionAventura2 = new Atraccion("Moria 2", 2001, 3, 1, TipoDeAtraccion.AVENTURA);

		arrayAtracciones.add(atraccionAventura1);
		arrayAtracciones.add(atraccionAventura2);

		PromocionesAbsolutas promocionMayorPrioridad = new PromocionesAbsolutas("Pack paisajes", arrayAtracciones, 100);
		Atraccion atraccionMenorPrioridad = new Atraccion("Erebor 1", 20000, 5, 2, TipoDeAtraccion.AVENTURA);

		listaRecomendaciones.add(atraccionMenorPrioridad);
		listaRecomendaciones.add(promocionMayorPrioridad);
		listaRecomendaciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Assert.assertEquals(promocionMayorPrioridad, listaRecomendaciones.get(0));
	}
}
