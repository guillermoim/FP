package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.grados.excepciones.ExcepcionPersonaNoValida;
import fp.grados.tipos.Persona;
import fp.grados.tipos.PersonaImpl;
import fp.grados.utiles.Grados;

public class TestPersona {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();
		testConstructor1Excepcional4();
		
		testConstructor2Normal();
		testConstructor2Excepcional1();
		testConstructor2Excepcional2();
		testConstructor2Excepcional3();

		
		testSetEmailNormal();
		testSetEmailExcepcional1();


		testSetDNINormal();
		testSetDNIExcepcional1();
		testSetDNIExcepcional2();
		testSetDNIExcepcional3();
		
		testEquality1();
		testOrder1();
		
		List<Persona> personas = Grados.leeFichero("res/personas.txt",s->new PersonaImpl(s));
		
		for(Persona e:personas){
			System.out.println(e);
		}
		
		
	}
	
	private static void testOrder1(){
		Persona p1 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		Persona p2 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1965, 4, 8), "juan.nadie25@gmail.com");
		testOrder(p1,p2);
	}
	
	private static void testOrder(Persona p1, Persona p2){
		mostrarPersona(p1);
		mostrarPersona(p2);
		int cmp=p1.compareTo(p2);
		if(cmp==0){
			System.out.print("p1 and p2 are equal\n");
		}
		else if(cmp<0){
			System.out.print("p1 is lower than p2");
		}
		else{
			System.out.print("p1 is greater than p2");
		}
	}
	
	
	private static void testEquality1(){
		//create 2 different object of type Persona
		//invoke testEquality
		Persona p1 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		Persona p2 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1965, 4, 8), "juan.nadie25@gmail.com");
		testEquality(p1,p2);
		
	}
	
	private static void testEquality(Persona p1,Persona p2){
		//print the two object using mostrarPersona
		//invoke hashCode method for each person and print the results
		//invoke equals method with p1 and p2 and print the result
			mostrarPersona(p1);
			mostrarPersona(p2);
			System.out.println("hashCode of p1:"+p1.hashCode());
			System.out.println("hashCode of p2:"+p2.hashCode());
			System.out.println("Are p1 and p2 equal?: "+p1.equals(p2));
		
	}
		
		
	
	private static void testConstructor1(String dni, String nombre, String apellidos,LocalDate fechaNacimiento, String email) {
		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos, fechaNacimiento, email);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	private static void testConstructor1Normal() {
		System.out.println("==================================Probando el primer constructor");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional1() {
		System.out.println("==================================Probando el primer constructor con dni sin letra");
		testConstructor1("123456789", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional2() {
		System.out.println("==================================Probando el primer constructor con dni de longitud menor de la esperada");
		testConstructor1("1234567X", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional3() {
		System.out.println("==================================Probando el primer constructor con letra en dni que no se corresponde a los dígitos");
		testConstructor1("12345678X", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional4() {
		System.out.println("==================================Probando el primer constructor con email sin arroba");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadiegmail.com");
	}	
	
	
	
	
	
	private static void testConstructor2(String dni, String nombre, String apellidos,LocalDate fechaNacimiento){
		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos, fechaNacimiento);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}
	
	private static void testConstructor2Normal() {
		System.out.println("\n\n==================================Probando el segundo constructor");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional1() {
		System.out.println("==================================Probando el segundo constructor con dni sin letra");
		testConstructor2("123456789", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional2() {
		System.out.println("==================================Probando el segundo constructor con dni de longitud menor de la esperada");
		testConstructor2("1234567X", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional3() {
		System.out.println("==================================Probando el segundo constructor con letra en dni que no se corresponde a los dígitos");
		testConstructor2("12345678X", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15));
	}


	

	
	
	
	private static void testSetEmail(Persona p,String email){
		try {
			System.out.println("El email antes de la operación es: "+  p.getEmail());
			System.out.println("El nuevo email es: "+  email);
			p.setEmail(email);;
			System.out.println("El email después de la operación es: "+  p.getEmail());
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
			
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada. setEmail no funciona correctamente");
		}
	}

	private static void testSetEmailNormal(){
		System.out.println("\n\n==================================Probando setEmail");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "juan.nadie_nadie@gmail.com");	
	}
	
	private static void testSetEmailExcepcional1(){
		System.out.println("==================================Probando setEmail sin @");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "juan.nadie_nadiegmail.com");	
	}
	

	
	
	
	
	private static void testSetDNI(Persona p, String nuevoDNI) {

		try {
			System.out.println("El dni antes de la operación es: "+  p.getDNI());
			System.out.println("El nuevo dni es: "+  nuevoDNI);
			p.setDNI(nuevoDNI);
			System.out.println("El dni después de la operación es: "+  p.getDNI());
		} catch (ExcepcionPersonaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}
	
	private static void testSetDNINormal(){
		System.out.println("\n\n==================================Probando setDNI");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677J");	
	}
	
	private static void testSetDNIExcepcional1(){
		System.out.println("==================================Probando setDNI con dni sin letra");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "123456779");	
	}

	private static void testSetDNIExcepcional2(){
		System.out.println("==================================Probando setDNI con dni de longitud menor de la esperada");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677");	
	}
	

	private static void testSetDNIExcepcional3(){
		System.out.println("==================================Probando setDNI con letra en dni que no se corresponde a los dígitos");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677X");	
	}
		
	
	
	

	private static void mostrarPersona(Persona p) {
		System.out.println("Persona --> <" + p + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tFecha Nacimiento: <"+ p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
	}

}