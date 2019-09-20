package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TutoriaImpl;



public class TestProfesor2 {

	public static void main(String[] args) {
		
		testConstructorNormal();
		testConstructorExcepcion();
		
		testSetFechaNacimientoNormal();
		testSetFechaNacimientoExcepcion();
		
		testSetCategoriaNormal();
		
		testNuevaTutoriaNormal();
		
		testBorraTutoriaNormal();
		
		testBorraTutoriasNormal();
		
		testImparteAsignaturaNormal();
		testImparteAsignaturaExcepcion1();
		testImparteAsignaturaExcepcion2();
		testImparteAsignaturaExcepcion3();
		
		testDedicacionAsignatura();
		
		testEliminaAsignatura();
		
		
			
		
		
	}
	
	
	private static void testConstructorNormal() {
		System.out.println("\n==================================Probando el primer constructor");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
	}

	private static void testConstructorExcepcion() {
		System.out.println("\n==================================Probando el primer constructor,edad menor de 18");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1998, 3, 15), "juan.nadie@gmail.com",Categoria.COLABORADOR,d1);
	}
	
	private static void testSetFechaNacimientoNormal(){
		System.out.println("\n==================================Probando setFechaNacimiento");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		testSetFechaNacimiento(p, LocalDate.of(1995, 3, 10));
	}
	
	
	private static void testSetFechaNacimientoExcepcion(){
		System.out.println("\n==================================Probando setFechaNacimiento, menor de 18 años");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		testSetFechaNacimiento(p, LocalDate.of(2000, 3, 10));
	}
	
	
	private static void testSetCategoriaNormal(){
		System.out.println("\n==================================Probando setCategoria");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		testSetCategoria(p, Categoria.CATEDRATICO);
	}
	
	
	private static void testNuevaTutoriaNormal(){
		System.out.println("\n==================================Probando nuevaTutoria");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		p.nuevaTutoria(LocalTime.of(12, 30), 60, DayOfWeek.FRIDAY);
		testNuevaTutoria(p, LocalTime.of(10, 30),120,DayOfWeek.MONDAY);
	}
	
	private static void testBorraTutoriaNormal(){
		System.out.println("\n==================================Probando borraTutoria");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		p.nuevaTutoria(LocalTime.of(12, 30), 60, DayOfWeek.FRIDAY);
		p.nuevaTutoria(LocalTime.of(12, 30),60,DayOfWeek.TUESDAY);
		testBorraTutoria(p, LocalTime.of(12, 30),DayOfWeek.TUESDAY);
	}
	
	
	private static void testBorraTutoriasNormal(){
		System.out.println("\n==================================Probando borraTutorias");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		p.nuevaTutoria(LocalTime.of(12, 30), 60, DayOfWeek.FRIDAY);
		p.nuevaTutoria(LocalTime.of(12, 30),60,DayOfWeek.TUESDAY);
		testBorraTutorias(p);
	}
	
	
	
	
	private static void testConstructor(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, 
			String email, Categoria categoria,Departamento d) {
		try {
			Profesor p = new ProfesorImpl2(dni, nombre, apellidos, fechaNacimiento,email,categoria,d);
			mostrarProfesor(p);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testSetCategoria(Profesor p, Categoria categoria){
			System.out.println("La categoria antes de la operación es: "+ p.getCategoria());
			System.out.println("El nuevo valor es: "+  categoria);
			p.setCategoria(categoria);
			System.out.println("La categoria después de la operación es: "+ p.getCategoria());

	}
	
	
	private static void testSetFechaNacimiento(Profesor p,LocalDate d){
		try {
			System.out.println("La fecha de nacimiento antes de la operación es: "+ p.getFechaNacimiento());
			System.out.println("El nuevo valor es: "+  d);
			p.setFechaNacimiento(d);
			System.out.println("La categoria después de la operación es: "+ p.getFechaNacimiento());
		} catch (ExcepcionProfesorNoValido a) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido");
		} catch (Exception a) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
		
	}
		
	
	private static void testNuevaTutoria(Profesor p, LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia){
		
			System.out.println("Las tutoria antes de la operación son: " + p.getTutorias());
			System.out.println("Nueva tutoria: " + new TutoriaImpl(dia,horaComienzo, duracionMinutos));
			p.nuevaTutoria(horaComienzo,duracionMinutos,dia);
			System.out.println("Las tutorias después de la operación son: "+ p.getTutorias());
	}
	
	private static void testBorraTutoria(Profesor p, LocalTime horaComienzo, DayOfWeek dia){
		
		System.out.println("Las tutoria antes de la operación son: " + p.getTutorias());
		System.out.println("Borra tutoria: " + new TutoriaImpl(dia,horaComienzo, 30));
		p.borraTutoria(horaComienzo,dia);
		System.out.println("Las tutorias después de la operación son: "+ p.getTutorias());
		
	}
	
	private static void testBorraTutorias(Profesor p){
		System.out.println("Las tutoria antes de la operación son: " + p.getTutorias());
		System.out.println("Borrar todas las tutorias: ");
		p.borraTutorias();
		System.out.println("Las tutorias después de la operación son: "+ p.getTutorias());
	}
	
	

	private static void testImparteAsignaturaNormal() {
		System.out 
		.println("\n====Probando el método imparteAsignatura");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
		Asignatura adda=new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos","2050010",12.0,TipoAsignatura.ANUAL,2);
		
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		
		Profesor profesor = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevoProfesor(profesor);
		
		System.out.println("*** Añadiendo las asignaturas FP y ADDA al profesor "+profesor);
		profesor.imparteAsignatura(fp, 6.0);
		profesor.imparteAsignatura(adda, 6.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** Modificando la dedicación en FP");
		profesor.imparteAsignatura(fp, 3.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
	}

	private static void testImparteAsignaturaExcepcion1() {
		System.out
		.println("\n====Probando el método imparteAsignatura, añadiendo asignatura que no es del departamento del profesor");
		try{
			Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
			
			Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
			// La asignatura no se añade al departamento del profesor
			
			Profesor profesor = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
			lsi.nuevoProfesor(profesor);
			
			System.out.println("*** Añadiendo una asignatura que no es del departamento del profesor");
			profesor.imparteAsignatura(fp, 6.0);
			System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
			System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
		}catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("******** Capturada excepción ExcepcionProfesorOperacionNoPermitida: "+e.getMessage());
		}catch(Exception e){
			System.out.println("******** Capturada excepción inesperada!!!!!");
		}		
	}
	
	private static void testImparteAsignaturaExcepcion2() {
		System.out
		.println("\n====Probando el método imparteAsignatura, excediendo el número de créditos de la asignatura");
		try{
			Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
			
			Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
			lsi.nuevaAsignatura(fp);
			
			Profesor profesor = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
			lsi.nuevoProfesor(profesor);
			
			System.out.println("*** Añadiendo una asignatura, excediendo el número de créditos de la asignatura");
			profesor.imparteAsignatura(fp, 20.0);
			System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
			System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
		}catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("******** Capturada excepción ExcepcionProfesorOperacionNoPermitida: "+e.getMessage());
		}catch(Exception e){
			System.out.println("******** Capturada excepción inesperada!!!!!");
		}
	}

	private static void testImparteAsignaturaExcepcion3() {
		System.out
		.println("\n====Probando el método imparteAsignatura, número de créditos igual a 0.");
		try{
			Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
			
			Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
			lsi.nuevaAsignatura(fp);
			
			Profesor profesor = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
			lsi.nuevoProfesor(profesor);
			
			System.out.println("*** Añadiendo una asignatura, número de créditos igual a 0.");
			profesor.imparteAsignatura(fp, 0.0);
			System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
			System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
		}catch(ExcepcionProfesorOperacionNoPermitida e){
			System.out.println("******** Capturada excepción ExcepcionProfesorOperacionNoPermitida: "+e.getMessage());
		}catch(Exception e){
			System.out.println("******** Capturada excepción inesperada!!!!!");
		}
	}
	
	private static void testDedicacionAsignatura() {
		System.out
		.println("\n====Probando el método dedicacionAsignatura");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
		Asignatura adda=new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos","2050010",12.0,TipoAsignatura.ANUAL,2);
		Asignatura so=new AsignaturaImpl("Sistemas Operativos","2050014",6.0,TipoAsignatura.PRIMER_CUATRIMESTRE,2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevaAsignatura(so);
		
		Profesor profesor = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevoProfesor(profesor);
		
		System.out.println("*** Añadiendo las asignaturas FP y ADDA al profesor "+profesor);
		profesor.imparteAsignatura(fp, 6.0);
		profesor.imparteAsignatura(adda, 6.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** La dedicación en FP es: "+profesor.dedicacionAsignatura(fp));
		System.out.println("*** La dedicación en ADDA es: "+profesor.dedicacionAsignatura(adda));
		System.out.println("*** La dedicación en SO es: "+profesor.dedicacionAsignatura(so));
	}
	
	private static void testEliminaAsignatura() {
		System.out
		.println("\n====Probando el método eliminaAsignatura");
		Asignatura fp=new AsignaturaImpl("Fundamentos de Programacion","2050001",12.0,TipoAsignatura.ANUAL,1);
		Asignatura adda=new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos","2050010",12.0,TipoAsignatura.ANUAL,2);
		Asignatura so=new AsignaturaImpl("Sistemas Operativos","2050014",6.0,TipoAsignatura.PRIMER_CUATRIMESTRE,2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		lsi.nuevaAsignatura(fp);
		lsi.nuevaAsignatura(adda);
		lsi.nuevaAsignatura(so);
		
		Profesor profesor = new ProfesorImpl2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@.us.es", Categoria.TITULAR);
		lsi.nuevoProfesor(profesor);
		
		System.out.println("*** Añadiendo las asignaturas FP y ADDA al profesor "+profesor);
		profesor.imparteAsignatura(fp, 6.0);
		profesor.imparteAsignatura(adda, 6.0);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** Eliminando FP ");
		profesor.eliminaAsignatura(fp);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
	
		System.out.println("*** Eliminando SO ");
		profesor.eliminaAsignatura(so);
		System.out.println("*** Asignaturas del profesor: "+profesor.getAsignaturas());
		System.out.println("*** Créditos del profesor: "+profesor.getCreditos());
	}
	
	
	private static void mostrarProfesor(Profesor p) {
		System.out.println("Profesor --> <" + p + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"+ p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
		System.out.println("\tCategoria:  <" + p.getCategoria() + ">");
		System.out.println("\tTutorias:  <" + p.getTutorias()+ ">");
		System.out.println("\tDepartamento:  <" +p.getDepartamento()+ ">");
	}
}




