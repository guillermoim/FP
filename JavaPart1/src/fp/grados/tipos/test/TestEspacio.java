package fp.grados.tipos.test;

import java.util.List;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.*;
import fp.grados.utiles.Grados;

public class TestEspacio {

	public static void main(String[] args) {
		
		testConstructorNormal();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		
		testSetCapacidadNormal();
		testSetCapacidadExcepcion1();
		testSetCapacidadExcepcion2();
		
		testEquality1();
		testOrder1();
		
		List<Espacio> espacios = Grados.leeFichero("res/espacios.txt",s->new EspacioImpl(s));
		
		for(Espacio e:espacios){
			System.out.println(e);
		}
	}

	private static void testOrder1(){
		Espacio e1 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.12",50,-1);
		Espacio e2 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.10",50,5);
		testOrder(e1,e2);
	}
	private static void testOrder(Espacio e1, Espacio e2){
		mostrarEspacio(e1);
		mostrarEspacio(e2);
		int cmp=e1.compareTo(e2);
		if(cmp==0){
			System.out.print("e1 and e2 are equal");
		}
		else if(cmp<0){
			System.out.print("e1 is lower than e2");
		}
		else{
			System.out.print("e1 is greater than e2");
		}
	}
		
	private static void testEquality1(){
		Espacio e1 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.09",50,5);
		Espacio e2 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.10",50,3);
		testEquality(e1,e2);
		
	}
	
	private static void testEquality(Espacio e1, Espacio e2){
		mostrarEspacio(e1);
		mostrarEspacio(e2);
		System.out.println("hashCode of e1:"+e1.hashCode());
		System.out.println("hashCode of e2:"+e2.hashCode());
		System.out.println("Are e1 and e2 equal?: "+e1.equals(e2));
		
	}
	
	private static void testConstructorNormal() {
		System.out.println("==================================Probando el constructor");
		testConstructor(TipoEspacio.EXAMEN,"A3.10",50,3);
	}

	private static void testConstructorExcepcion1(){
		System.out.println("==================================Probando el constructor, capacidad igual a 0");
		testConstructor(TipoEspacio.EXAMEN,"A3.10",0,3);
	}
	
	private static void testConstructorExcepcion2(){
		System.out.println("==================================Probando el constructor, capacidad negativa");
		testConstructor(TipoEspacio.EXAMEN,"A3.10",-1,3);
	}
	
	private static void testSetCapacidadNormal(){
		System.out.println("==================================Probando setCapacidad");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN,"A3.10",50,3);
		testSetCapacidad(e, 80);
	}
	
	private static void testSetCapacidadExcepcion1(){
		System.out.println("==================================Probando setCapacidad, capacidad igual a 0");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN,"A3.10",50,3);
		testSetCapacidad(e, 0);
	}
	
	private static void testSetCapacidadExcepcion2(){
		System.out.println("==================================Probando setCapacidad, capacidad negativa");
		Espacio e = new EspacioImpl(TipoEspacio.EXAMEN,"A3.10",50,3);
		testSetCapacidad(e, -20);
	}
	
	
	
	private static void testConstructor(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		try {
			Espacio e = new EspacioImpl(tipo, nombre,capacidad,planta);
			mostrarEspacio(e);
		} catch (ExcepcionEspacioNoValido e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testSetCapacidad(Espacio e, Integer capacidad){
		try {
			System.out.println("La capacidad antes de la operación es: "+ e.getCapacidad());
			System.out.println("El nuevo valor es: "+  capacidad);
			e.setCapacidad(capacidad);
			System.out.println("La capacidad después de la operación es: "+ e.getCapacidad());
		} catch (ExcepcionEspacioNoValido a) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido");
		} catch (Exception a) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void mostrarEspacio(Espacio e){
		
		System.out.println("Espacio --> <" + e + ">");
		System.out.println("\tTipo: <" + e.getTipo() + ">");
		System.out.println("\tNombre: <" + e.getNombre() + ">");
		System.out.println("\tCapacidad: <" + e.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + e.getPlanta() + ">");
	
	}
}