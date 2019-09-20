package fp.repaso.defensa;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoEspacio;

public class TestActividad2 {

	public static void main(String[] args) {
			testIgualdad();
			testOrdenacion();

		}
		private static void testIgualdad(){
			Actividad a1 = new ActividadImpl("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
					new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
					100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
							12d, TipoAsignatura.ANUAL, 1));
			Actividad a2 = new ActividadImpl("AA007", DayOfWeek.MONDAY,  LocalTime.of(14,30),
					new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
					100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
							12d, TipoAsignatura.ANUAL, 1));
			Actividad a3 = new ActividadImpl("AA001", DayOfWeek.THURSDAY,  LocalTime.of(14,30),
					new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
					100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
							12d, TipoAsignatura.ANUAL, 1));
			System.out.println("Probando igualdad");
			System.out.println("a1-->"+a1+"\n\tHashCode-->"+a1.hashCode());
			System.out.println("a2-->"+a2+"\n\tHashCode-->"+a2.hashCode());
			System.out.println("a3-->"+a3+"\n\tHashCode-->"+a3.hashCode());
			System.out.println("¿a1 igual a2?-->"+a1.equals(a2));
			System.out.println("¿a1 igual a3?-->"+a1.equals(a3));
			System.out.println("¿a2 igual a3?-->"+a2.equals(a3));
		}
		private static void testOrdenacion(){
			Actividad a1 = new ActividadImpl("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
					new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
					100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
							12d, TipoAsignatura.ANUAL, 1));
			Actividad a2 = new ActividadImpl("AA007", DayOfWeek.MONDAY,  LocalTime.of(14,30),
					new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
					100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
							12d, TipoAsignatura.ANUAL, 1));
			Actividad a3 = new ActividadImpl("AA001", DayOfWeek.THURSDAY,  LocalTime.of(14,30),
					new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
					100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
							12d, TipoAsignatura.ANUAL, 1));
			Actividad a4 = new ActividadImpl("A 001", DayOfWeek.THURSDAY,  LocalTime.of(14,30),
					new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),
					100,TipoActividad.EXAMEN,new AsignaturaImpl("Fundamentos de Programación", "0000230",
							12d, TipoAsignatura.ANUAL, 1));
			System.out.println("\nProbando comparaciones");
			System.out.println("a1-->"+a1);
			System.out.println("a2-->"+a2);
			System.out.println("a3-->"+a3);
			System.out.println("a4-->"+a4);
			System.out.println("a1 ordenado con a2-->"+a1.compareTo(a2));
			System.out.println("a1 ordenado con a3-->"+a1.compareTo(a3));
			System.out.println("a1 ordenado con a4-->"+a1.compareTo(a4));
		}

}
