package fp.repaso.defensa;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoEspacio;

public class TestActividad {

	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor1Excepcion();
		testConstructor2Excepcion();
		testConstructor3Excepcion();
		testConstructor4Excepcion();
		testConstructor5Excepcion();
		testConstructor6Excepcion();
		testSetEspacioCorrecto();
		testSetEspacioExcepcion();
	}

	private static void testConstructor1Normal() {
		System.out.println("\n=====Probando el constructor");
		testConstructor1("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
				100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
	}

	private static void testConstructor1Excepcion() {
		System.out.println("\n=====Probando el constructor, con Espacio de tipo OTRO");
		testConstructor1("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.OTRO,"A3.01",250,1),
				100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
	}
	private static void testConstructor2Excepcion() {
		System.out.println("\n=====Probando el constructor, con Espacio de distinto tipo");
		testConstructor1("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.LABORATORIO,"A3.01",250,1),
				100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
	}
	private static void testConstructor3Excepcion() {
		System.out.println("\n=====Probando el constructor, con duración negativa");
		testConstructor1("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
				-1,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
	}
	private static void testConstructor4Excepcion() {
		System.out.println("\n=====Probando el constructor, con exceso de duración");
		testConstructor1("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
				190,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
	}
	private static void testConstructor5Excepcion() {
		System.out.println("\n=====Probando el constructor, con exceso de duración");
		testConstructor1("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.LABORATORIO,"A3.01",250,1),
				130,TipoActividad.LABORATORIO,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
	}
	private static void testConstructor6Excepcion() {
		System.out.println("\n=====Probando el constructor, día erróneo");
		testConstructor1("AA001", DayOfWeek.SUNDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
				100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
	}
	private static void testSetEspacioCorrecto() {
		System.out.println("\n=====Probando setEspacio");

		Actividad a = new ActividadImpl("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
				100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
		testSetEspacio(a, new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,2));
	}

	private static void testSetEspacioExcepcion() {
		System.out.println("\n=====Probando setEspacio, espacio distinto");

		Actividad a = new ActividadImpl("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
				100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
						12d, TipoAsignatura.ANUAL, 1));
		testSetEspacio(a, new EspacioImpl(TipoEspacio.TEORIA,"A3.01",250,2));
	}


	private static void testConstructor1(String codigo, DayOfWeek dia, LocalTime horaComienzo, Espacio espacio,
			Integer duracion, TipoActividad tipo, Asignatura asignatura) {

		try {
			Actividad a = new ActividadImpl(codigo, dia,horaComienzo,espacio,
					duracion, tipo, asignatura);
			mostrarActividad(a);
		} catch (Exception e) {
			System.out.println("Capturada la excepción:\n\t"+e);
		} 

	}



	private static void testSetEspacio(Actividad a, Espacio e) {

		try {
			System.out.println("El espacio antes es: " + a.getEspacio());
			System.out.println("El nuevo espacio es: " + e);
			a.setEspacio(e);
			System.out.println("El espacio después es: " + a.getEspacio());
		} catch (Exception ex) {
			System.out.println("Capturada la excepción: "+ex);
		}
	}

	private static void mostrarActividad(Actividad a) {
		System.out.println("Actividad --> <" + a + ">");
		System.out.println("\tCodigo: <" + a.getCodigo() + ">");
		System.out.println("\tDiaSemana: <" + a.getDiaSemana() + ">");
		System.out.println("\tHoraComienzo: <" + a.getHoraComienzo()+ ">");
		System.out.println("\tDuracion: <" + a.getDuracion() + ">");
		System.out.println("\tHoraFin: <" + a.getHoraFinal() + ">");
		System.out.println("\tEspacio  <" + a.getEspacio() + ">");
		System.out.println("\tTipoActividad:  <" + a.getTipoActividad() + ">");
		System.out.println("\tAsignatura: <" + a.getAsignatura() + ">");
	}
}
