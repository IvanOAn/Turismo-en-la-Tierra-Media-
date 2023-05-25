package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Atraccion vec[] = new Atraccion[5];
		Set<Atraccion> atracciones = new TreeSet<Atraccion>();
		// List<Atraccion>atracciones=new LinkedList<>();

		vec[0] = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		vec[1] = new Atraccion("Minas Tirith", 140, 2, 6, TipoDeAtraccion.AVENTURA);
		vec[2] = new Atraccion("La Comarca", 10, 22, 6, TipoDeAtraccion.DEGUSTACION);
		vec[3] = new Atraccion("Mordor", 1, 1, 6, TipoDeAtraccion.AVENTURA);
		vec[4] = new Atraccion("Abismo de Helm", 100, 25, 6, TipoDeAtraccion.PAISAJES);

		/*Archivo arUsuarios = new Archivo("Usuarios");
		System.out.println(arUsuarios.leerArchivoUsuarios().toString());

		Archivo arAtracciones = new Archivo("Atracciones");
		System.out.println(arAtracciones.leerArchivoAtracciones().toString());
		;*/
		
		Recomendador recom=new Recomendador();
		
		recom.cargarUsuarios();
		recom.cargarAtracciones();
		recom.cargarPromociones();

		for (int i = 0; i < vec.length; i++) {
			atracciones.add(vec[i]);
		}
 
		// Collections.sort(atracciones, new
		// ComparadorAtracciones(TipoDeAtraccion.Aventura));

		for (Atraccion atraccion : atracciones) {
			System.out.println(atraccion);
		}

		ArrayList<Atraccion> atrac = new ArrayList<>();
		atrac.add(vec[0]);
		atrac.add(vec[1]);
		//Promocion proA = new PromocionesAbsolutas(atrac, 120);

		atrac.clear();
		atrac.add(vec[4]);
		//Promocion proP = new PromocionPorcentual(atrac, 10);

		atrac.add(vec[2]);
		//Promocion proAxB = new PromocionesAxB(atrac);

		/*Set<Promocion> promociones = new TreeSet<Promocion>();
		promociones.add(proAxB);
		promociones.add(proP);
		promociones.add(proA);*/

		// System.out.println(proAxB);

		Usuario user = new Usuario("Galardier", 300, 50, TipoDeAtraccion.AVENTURA);
		Iterator<Atraccion> itAtracciones = atracciones.iterator();
		//Iterator<Promocion> itPromociones = promociones.iterator();

		/*
		 * while(user.estado() && itAtracciones.hasNext()) {
		 * atracciones=Atraccion.atraccionesDisponibles(user,atracciones);
		 * itAtracciones=atracciones.iterator();
		 * 
		 * System.out.println(); for (Atraccion atraccion : atracciones) {
		 * System.out.println(atraccion); }
		 * 
		 * user.agregarAtraccion(itAtracciones.next()); }
		 */

		Promocion auxP = null;
		Atraccion auxA = null;
		boolean band = false;
/*
		while (user.estado() && (promociones.size() != 0 || atracciones.size() != 0)) {

			promociones = Promocion.promocionesDisponibles(user, promociones);
			itPromociones = promociones.iterator();

			boolean salir = false;
			while (itPromociones.hasNext() && (!salir || band)) {
				auxP = itPromociones.next();
				if (auxP.getTipoDeAtraccion() == user.getTipoDeAtraccionPreferida() || band) {
					user.agregarPromocion(auxP);
					promociones = Promocion.promocionesDisponibles(user, promociones);
					itPromociones = promociones.iterator();
				} else {
					salir = true;
				}
			}

			atracciones = Atraccion.atraccionesDisponibles(user, atracciones);
			itAtracciones = atracciones.iterator();

			salir = false;
			while (itAtracciones.hasNext() && (!salir || band)) {
				auxA = itAtracciones.next();
				if (auxA.getTipoDeAtraccion() == user.getTipoDeAtraccionPreferida() || band) {
					user.agregarAtraccion(auxA);
					atracciones = Atraccion.atraccionesDisponibles(user, atracciones);
					itAtracciones = atracciones.iterator();
				} else {
					salir = true;
				}
			}

			if (!band)
				band = true;
		}*/
	}
}
