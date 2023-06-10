package Clases;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class ComparadorRecomendacionesTest {

	@Test
	public void ordenaBienAtracciones() {
		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);

		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion4 = new Atraccion("Erebor 3", 21, 3, 1, TipoDeAtraccion.PAISAJES);
		Atraccion atraccion3 = new Atraccion("Erebor 2", 21, 5, 2, TipoDeAtraccion.PAISAJES);
		Atraccion atraccion2 = new Atraccion("Erebor 1", 40, 3, 1, TipoDeAtraccion.PAISAJES);

		ArrayList<Atraccion> arrayAtracciones = new ArrayList<Atraccion>();

		arrayAtracciones.add(atraccion4);
		arrayAtracciones.add(atraccion1);
		arrayAtracciones.add(atraccion3);
		arrayAtracciones.add(atraccion2);

		arrayAtracciones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Atraccion[] arrayEsperado = { atraccion1, atraccion2, atraccion3, atraccion4 };

		for (int i = 0; i < arrayAtracciones.size(); i++) {
			Assert.assertEquals(arrayEsperado[i], arrayAtracciones.get(i));
		}
	}

	@Test
	public void ordenaBienPromociones() {
		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);

		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 21, 3, 1, TipoDeAtraccion.AVENTURA);
		LinkedList<Atraccion> listaPromo1 = new LinkedList<Atraccion>();
		listaPromo1.add(atraccion1);
		listaPromo1.add(atraccion2);

		PromocionesPorcentual promocion1 = new PromocionesPorcentual("Pack aventura", listaPromo1, 15);

		Atraccion atraccion3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.PAISAJES);
		Atraccion atraccion4 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.PAISAJES);
		LinkedList<Atraccion> listaPromo2 = new LinkedList<Atraccion>();
		listaPromo2.add(atraccion3);
		listaPromo2.add(atraccion4);

		PromocionesAbsolutas promocion2 = new PromocionesAbsolutas("Pack paisajes", listaPromo2, 3500);

		Atraccion atraccion5 = new Atraccion("La Comarca 1", 21, 5, 2, TipoDeAtraccion.DEGUSTACION);
		Atraccion atraccion6 = new Atraccion("La Comarca 2", 40, 3, 1, TipoDeAtraccion.DEGUSTACION);
		LinkedList<Atraccion> listaPromo3 = new LinkedList<Atraccion>();
		listaPromo3.add(atraccion5);

		PromocionesAxB promocion3 = new PromocionesAxB("Pack degustacion", listaPromo3, atraccion6);

		LinkedList<Promocion> listaPromociones = new LinkedList<Promocion>();
		listaPromociones.add(promocion2);
		listaPromociones.add(promocion3);
		listaPromociones.add(promocion1);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Promocion[] arrayEsperado = { promocion1, promocion2, promocion3 };

		for (int i = 0; i < listaPromociones.size(); i++) {
			Assert.assertEquals(arrayEsperado[i], listaPromociones.get(i));
		}
	}

	@Test
	public void ordenaBienRecomendaciones() {
		Usuario usuario = new Usuario("Frodo", 30, 10, TipoDeAtraccion.AVENTURA);

		Atraccion atraccion1 = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion atraccion2 = new Atraccion("Moria 2", 21, 3, 1, TipoDeAtraccion.AVENTURA);
		LinkedList<Atraccion> listaPromo1 = new LinkedList<Atraccion>();
		listaPromo1.add(atraccion1);
		listaPromo1.add(atraccion2);

		PromocionesPorcentual promocion1 = new PromocionesPorcentual("Pack aventura", listaPromo1, 15);

		Atraccion atraccion3 = new Atraccion("Erebor 1", 210, 5, 2, TipoDeAtraccion.PAISAJES);
		Atraccion atraccion4 = new Atraccion("Erebor 2", 4000, 3, 1, TipoDeAtraccion.PAISAJES);
		LinkedList<Atraccion> listaPromo2 = new LinkedList<Atraccion>();
		listaPromo2.add(atraccion3);
		listaPromo2.add(atraccion4);

		PromocionesAbsolutas promocion2 = new PromocionesAbsolutas("Pack paisajes", listaPromo2, 3500);

		Atraccion atraccion5 = new Atraccion("La Comarca 1", 21, 5, 2, TipoDeAtraccion.DEGUSTACION);
		Atraccion atraccion6 = new Atraccion("La Comarca 2", 40, 3, 1, TipoDeAtraccion.DEGUSTACION);
		LinkedList<Atraccion> listaPromo3 = new LinkedList<Atraccion>();
		listaPromo3.add(atraccion5);

		PromocionesAxB promocion3 = new PromocionesAxB("Pack degustacion", listaPromo3, atraccion6);

		LinkedList<Recomendacion> listaPromociones = new LinkedList<Recomendacion>();
		listaPromociones.add(atraccion3);
		listaPromociones.add(atraccion5);
		listaPromociones.add(promocion3);
		listaPromociones.add(promocion1);
		listaPromociones.add(atraccion1);
		listaPromociones.add(promocion2);

		listaPromociones.sort(new ComparadorRecomendaciones(usuario.getTipoDeAtraccionPreferida()));

		Recomendacion[] arrayEsperado = { promocion1, atraccion1, promocion2, promocion3, atraccion3, atraccion5 };

		for (int i = 0; i < listaPromociones.size(); i++) {
			Assert.assertEquals(arrayEsperado[i], listaPromociones.get(i));
		}
	}

}
