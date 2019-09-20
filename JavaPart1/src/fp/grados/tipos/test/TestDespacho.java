package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;
import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoEspacio;



public class TestDespacho {

	public static void main(String[] args) {
		
		testConstructor1Normal();
		testConstructor1Excepcion();
		testConstructor2Normal();
		testConstructor3Normal();
		testSetTipoExcepcion();
		testSetProfesoresNormal();
		testSetProfesoresExcepcion();
		testSetCapacidadNormal();
		testSetCapacidadExcepcion();
		
		
		
	}
	
	private static void testConstructor1Normal(){
		System.out.println("\n==================================Probando el primer constructor");
		Set<Profesor> s= new HashSet<Profesor>();
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p1= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		Profesor p2 = new ProfesorImpl("12345678Z", "Pepe", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.CATEDRATICO,d1);
		s.add(p1);
		s.add(p2);
		testConstructor1("A3.12",50,3, s);
	}
	
	private static void testConstructor1Excepcion(){
		System.out.println("\n==================================Probando el primer constructor,más profesores que capacidad");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p1= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
		Profesor p2= new ProfesorImpl("12345678Z", "Pepe", "Nadie Nadie",LocalDate.of(1950, 3, 15), "pepe.nadie@us.es",Categoria.AYUDANTE,d1);
		Set<Profesor> profesores= new HashSet<Profesor>();
		profesores.add(p1);
		profesores.add(p2);
		testConstructor1("A3.12",1,1, profesores);
	}
	
	
	
	private static void testConstructor2Normal(){
		System.out.println("\n==================================Probando el segundo constructor");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p1= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		testConstructor2("A3.12",50,3, p1);
	}
	
	
	private static void testConstructor3Normal(){
		System.out.println("\n==================================Probando el tercer constructor");
		testConstructor3("A3.12",50,3);
	}
	
	
	private static void testSetProfesoresNormal(){
		System.out.println("\n==================================Probando setProfesores");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p1= new ProfesorImpl("12345678Z", "Manuel", "Carranza",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.COLABORADOR,d1);
		Profesor p2= new ProfesorImpl("12345678Z", "Pepe", "Lopez",LocalDate.of(1950, 3, 15), "pepe.nadie@us.es",Categoria.INTERINO,d1);
		Set<Profesor> profesores= new HashSet<Profesor>();
		profesores.add(p1);
		profesores.add(p2);
		Despacho d = new DespachoImpl("A3.12",10,3,profesores);	
		
		Profesor p3= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
		Profesor p4= new ProfesorImpl("12345678Z", "Pepe", "Nadie Nadie",LocalDate.of(1950, 3, 15), "pepe.nadie@us.es",Categoria.AYUDANTE,d1);
		Set<Profesor> nuevosProfesores= new HashSet<Profesor>();
		nuevosProfesores.add(p3);
		nuevosProfesores.add(p4);
		testSetProfesores(d,nuevosProfesores);	
	}
	
	
	private static void testSetProfesoresExcepcion(){
		System.out.println("\n==================================Probando setProfesores, mayor número de profesores que capacidad");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p1= new ProfesorImpl("12345678Z", "Manuel", "Carranza",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.COLABORADOR,d1);
		Set<Profesor> profesores= new HashSet<Profesor>();
		profesores.add(p1);
		Despacho d = new DespachoImpl("A3.12",1,3,profesores);	
		
		Profesor p3= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
		Profesor p4= new ProfesorImpl("12345678Z", "Pepe", "Nadie Nadie",LocalDate.of(1950, 3, 15), "pepe.nadie@us.es",Categoria.AYUDANTE,d1);
		Set<Profesor> nuevosProfesores= new HashSet<Profesor>();
		nuevosProfesores.add(p3);
		nuevosProfesores.add(p4);
		testSetProfesores(d,nuevosProfesores);	
	}
	
	
	private static void testSetCapacidadNormal(){
		System.out.println("\n==================================Probando setCapacidad");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p1= new ProfesorImpl("12345678Z", "Manuel", "Carranza",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.COLABORADOR,d1);
		Set<Profesor> profesores= new HashSet<Profesor>();
		profesores.add(p1);
		Despacho d = new DespachoImpl("A3.12",3,1,profesores);	
		testSetCapacidad(d,5);
	}
	
	private static void testSetCapacidadExcepcion(){
		System.out.println("\n==================================Probando setCapacidad, menor capacidad que profesores");
		Departamento d1 =new DepartamentoImpl("LSI");
		Profesor p1= new ProfesorImpl("12345678Z", "Manuel", "Carranza",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.COLABORADOR,d1);
		Profesor p2= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
		Set<Profesor> profesores= new HashSet<Profesor>();
		profesores.add(p1);
		profesores.add(p2);
		Despacho d = new DespachoImpl("A3.12",3,1,profesores);	
		testSetCapacidad(d,1);
	}
	
	private static void testConstructor1(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores){
		try {
			Despacho d = new DespachoImpl(nombre, capacidad, planta,profesores);
			mostrarDespacho(d);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testConstructor2(String nombre, Integer capacidad, Integer planta, Profesor p){
		try {
			Despacho d = new DespachoImpl(nombre, capacidad, planta,p);
			mostrarDespacho(d);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testConstructor3(String nombre, Integer capacidad, Integer planta){
		try {
			Despacho d = new DespachoImpl(nombre, capacidad, planta);
			mostrarDespacho(d);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
	
	private static void testSetTipoExcepcion(){
		System.out.println("\n==================================Probando setTipo");
		Despacho d = new DespachoImpl("A3.12",50,3);	
		try {
			d.setTipo(TipoEspacio.EXAMEN);
		} catch (UnsupportedOperationException e) {
			System.out
					.println("******************** Se ha capturado la excepción UnsupportedOperationException");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	
	private static void testSetProfesores(Despacho d,Set<Profesor> nuevosProfesores){
		try {
			System.out.println("Las capacidad es : " + d.getCapacidad());
			System.out.println("Las profesores antes de la operación son: " + d.getProfesores());
			System.out.println("Los nuevos profesores son: " + nuevosProfesores);
			d.setProfesores(nuevosProfesores);
			System.out.println("Los profesores después de la operación son: " +d.getProfesores());
		}
		catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testSetCapacidad(Despacho d,Integer capacidad){
		try {
			System.out.println("El número de profesores es: "+d.getProfesores().size());
			System.out.println("La capacidad antes de la operación es: " + d.getCapacidad());
			System.out.println("La nueva capacidad es: " + capacidad);
			d.setCapacidad(capacidad);
			System.out.println("La capacidad después de la operación es: " +d.getCapacidad());
		}
		catch (ExcepcionEspacioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void mostrarDespacho(Despacho d){
		
		System.out.println("Despacho --> <" + d + ">");
		System.out.println("\tTipo: <" + d.getTipo() + ">");
		System.out.println("\tNombre: <" + d.getNombre() + ">");
		System.out.println("\tCapacidad: <" + d.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + d.getPlanta() + ">");
		System.out.println("\tProfesores:  <"+ d.getProfesores()+ ">");
	
	}
}
