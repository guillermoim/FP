package fp.repaso.defensa;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoEspacio;

public class TestCuadrante01 {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();
		testConstructor2Excepcion();
		testNuevaActividadCorrecta();
		testNuevaActividadErronea();
	}

	private static void testConstructor1Normal() {
		System.out.println("\n=====Probando el constructor");
		testConstructor1(7,new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es"));
	}

	private static void testConstructor1Excepcion() {
		System.out.println("\n=====Probando el constructor, con semana errónea >15");
		testConstructor1(17,new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es"));
	}
	private static void testConstructor2Excepcion() {
		System.out.println("\n=====Probando el constructor, con semana errónea <1");
		testConstructor1(0,new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es"));
	}
	private static void testNuevaActividadCorrecta() {
		System.out.println("\n=====Probando Nueva 2 Actividades correctas");
		Alumno al=new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura as=new AsignaturaImpl("Fundamentos de Programación", "0000230",
				12d, TipoAsignatura.ANUAL, 1);
		al.matriculaAsignatura(as);
		Cuadrante c = new CuadranteImpl(7,al);
		Actividad ac1 = new ActividadImpl("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),	100,TipoActividad.EXAMEN,as);
		testNuevaActividad(c, ac1);
		Actividad ac2 = new ActividadImpl("AA001", DayOfWeek.THURSDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),	100,TipoActividad.EXAMEN,as);
		testNuevaActividad(c, ac2);
	}
	private static void testNuevaActividadErronea() {
		System.out.println("\n=====Probando Nueva Actividad con alumno no matriculado en asignatura");
		Alumno al=new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura as1=new AsignaturaImpl("Matemáticas", "9900230",
				10d, TipoAsignatura.PRIMER_CUATRIMESTRE, 3);
		al.matriculaAsignatura(as1);
		System.out.println("Asignaturas del Alumno: "+al.getAsignaturas());
		Cuadrante c = new CuadranteImpl(7,al);
		Asignatura as2=new AsignaturaImpl("Fundamentos de Programación", "0000230",
				12d, TipoAsignatura.ANUAL, 1);
		Actividad ac = new ActividadImpl("AA001", DayOfWeek.MONDAY,  LocalTime.of(14,30),
				new EspacioImpl(TipoEspacio.EXAMEN,"A3.01",250,1),	100,TipoActividad.EXAMEN,as2);
		testNuevaActividad(c, ac);
	}


	private static void testConstructor1(Integer semana, Alumno alumno) {
		try {
			Cuadrante c = new CuadranteImpl(semana, alumno);
			mostrarCuadrante(c);
		} catch (Exception e) {
			System.out.println("Capturada la excepción:\n\t"+e);
		} 

	}
	private static void testNuevaActividad(Cuadrante c, Actividad a) {

		try {
			System.out.println("Cuadrante antes: " + c);
			System.out.println("Actividad a añadir: " + a);
			c.nuevaActividad(a);
			System.out.println("Cuadrante después es: " + c);
		} catch (Exception ex) {
			System.out.println("Capturada la excepción: "+ex);
		}
	}

	private static void mostrarCuadrante(Cuadrante c) {
		System.out.println("Cuadrante --> <" + c + ">");
		System.out.println("\tSemana: <" + c.getSemana()+ ">");
		System.out.println("\tAlumno: <" + c.getAlumno() + ">");
		System.out.println("\tActividades: <" + c.getActividades()+ ">");
	}

}
