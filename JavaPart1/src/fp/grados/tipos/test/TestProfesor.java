package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TutoriaImpl;



public class TestProfesor {

	public static void main(String[] args) {
		
		testConstructorNormal();
		testConstructorExcepcion();
		
		testSetFechaNacimientoNormal();
		testSetFechaNacimientoExcepcion();
		
		testSetCategoriaNormal();
		
		testNuevaTutoriaNormal();
		
		testBorraTutoriaNormal();
		
		testBorraTutoriasNormal();
		
		
			
		
		
	}
	
	
	private static void testConstructorNormal() {
		System.out.println("\n==================================Probando el primer constructor");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
	}

	private static void testConstructorExcepcion() {
		System.out.println("\n==================================Probando el primer constructor,edad menor de 18");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1997, 3, 15), "juan.nadie@gmail.com",Categoria.COLABORADOR,d1);
	}
	
	private static void testSetFechaNacimientoNormal(){
		System.out.println("\n==================================Probando setFechaNacimiento");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		testSetFechaNacimiento(p, LocalDate.of(1995, 3, 10));
	}
	
	
	private static void testSetFechaNacimientoExcepcion(){
		System.out.println("\n==================================Probando setFechaNacimiento, menor de 18 a�os");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		testSetFechaNacimiento(p, LocalDate.of(2000, 3, 10));
	}
	
	
	private static void testSetCategoriaNormal(){
		System.out.println("\n==================================Probando setCategoria");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		testSetCategoria(p, Categoria.CATEDRATICO);
	}
	
	
	private static void testNuevaTutoriaNormal(){
		System.out.println("\n==================================Probando nuevaTutoria");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		p.nuevaTutoria(LocalTime.of(12, 30), 60, DayOfWeek.FRIDAY);
		testNuevaTutoria(p, LocalTime.of(10, 30),120,DayOfWeek.MONDAY);
	}
	
	private static void testBorraTutoriaNormal(){
		System.out.println("\n==================================Probando borraTutoria");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		p.nuevaTutoria(LocalTime.of(12, 30), 60, DayOfWeek.FRIDAY);
		p.nuevaTutoria(LocalTime.of(12, 30),60,DayOfWeek.TUESDAY);
		testBorraTutoria(p, LocalTime.of(12, 30),DayOfWeek.TUESDAY);
	}
	
	
	private static void testBorraTutoriasNormal(){
		System.out.println("\n==================================Probando borraTutorias");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		p.nuevaTutoria(LocalTime.of(12, 30), 60, DayOfWeek.FRIDAY);
		p.nuevaTutoria(LocalTime.of(12, 30),60,DayOfWeek.TUESDAY);
		testBorraTutorias(p);
	}
	
	
	
	
	private static void testConstructor(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, 
			String email, Categoria categoria,Departamento d) {
		try {
			Profesor p = new ProfesorImpl(dni, nombre, apellidos, fechaNacimiento,email,categoria,d);
			mostrarProfesor(p);
		} catch (ExcepcionProfesorNoValido e) {
			System.out.println("******************** Se ha capturado la excepci�n ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepci�n inesperada.");
		}
	}
	
	private static void testSetCategoria(Profesor p, Categoria categoria){
			System.out.println("La categoria antes de la operaci�n es: "+ p.getCategoria());
			System.out.println("El nuevo valor es: "+  categoria);
			p.setCategoria(categoria);
			System.out.println("La categoria despu�s de la operaci�n es: "+ p.getCategoria());

	}
	
	
	private static void testSetFechaNacimiento(Profesor p,LocalDate d){
		try {
			System.out.println("La fecha de nacimiento antes de la operaci�n es: "+ p.getFechaNacimiento());
			System.out.println("El nuevo valor es: "+  d);
			p.setFechaNacimiento(d);
			System.out.println("La categoria despu�s de la operaci�n es: "+ p.getFechaNacimiento());
		} catch (ExcepcionProfesorNoValido a) {
			System.out.println("******************** Se ha capturado la excepci�n ExcepcionEspacioNoValido");
		} catch (Exception a) {
			System.out.println("******************** ���Se ha capturado una EXCEPCI�N INESPERADA!!!");
		}
		
	}
		
	
	private static void testNuevaTutoria(Profesor p, LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia){
		
			System.out.println("Las tutoria antes de la operaci�n son: " + p.getTutorias());
			System.out.println("Nueva tutoria: " + new TutoriaImpl(dia,horaComienzo, duracionMinutos));
			p.nuevaTutoria(horaComienzo,duracionMinutos,dia);
			System.out.println("Las tutorias despu�s de la operaci�n son: "+ p.getTutorias());
	}
	
	private static void testBorraTutoria(Profesor p, LocalTime horaComienzo, DayOfWeek dia){
		
		System.out.println("Las tutoria antes de la operaci�n son: " + p.getTutorias());
		System.out.println("Borra tutoria: " + new TutoriaImpl(dia,horaComienzo, 30));
		p.borraTutoria(horaComienzo,dia);
		System.out.println("Las tutorias despu�s de la operaci�n son: "+ p.getTutorias());
		
	}
	
	private static void testBorraTutorias(Profesor p){
		System.out.println("Las tutoria antes de la operaci�n son: " + p.getTutorias());
		System.out.println("Borrar todas las tutorias: ");
		p.borraTutorias();
		System.out.println("Las tutorias despu�s de la operaci�n son: "+ p.getTutorias());
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




