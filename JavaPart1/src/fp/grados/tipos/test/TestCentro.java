package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoEspacio;
import fp.grados.utiles.Grados;


@SuppressWarnings("unused")
public class TestCentro {
	
	public static void main(String[] args) {
		
		/*testConstructorNormal();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		
		testNuevoEspacioNormal();
		testEliminaEspacio();
		testNuevoEspacioExcepcion1();
		testNuevoEspacioExcepcion2();
		
		testOrder1();
		testEquality1();*/
		
		
		Centro c= Grados.createCentro("ETSII","Reina Mercedes",5, 1);
		Centro c2= new CentroImpl("Matematicas","Reina Mercedes",3, 1);
		Espacio e1= new EspacioImpl(TipoEspacio.EXAMEN,"A0.12",50,0);
		Espacio e2= new EspacioImpl(TipoEspacio.OTRO,"A1.12",20,1);
		Espacio e3= new EspacioImpl(TipoEspacio.TEORIA,"A3.12",30,1);
		
		c.nuevoEspacio(e1);
		c.nuevoEspacio(e2);
		c.nuevoEspacio(e3);
		
		
		System.out.println(c.getEspacios());
		System.out.println(c.getEspacioMayorCapacidad());
		System.out.println(Arrays.toString(c.getConteosEspacios()));
		
		Set<Profesor> s1= new HashSet<Profesor>();
		Set<Profesor> s2= new HashSet<Profesor>();
		Departamento d1 =new DepartamentoImpl("LSI");
		Departamento d2 =new DepartamentoImpl("FFI");
		Departamento d3 =new DepartamentoImpl("CED");
		Profesor p1= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d1);
		Profesor p2 = new ProfesorImpl("12345678Z", "Pepe", "A",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.CATEDRATICO,d2);
		Profesor p3 = new ProfesorImpl("12345678Z", "Manuel", "Carranza",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.CATEDRATICO,d3);
		s1.add(p1);
		s1.add(p2);
		s2.add(p3);
		
		
		Despacho des= new DespachoImpl("A2.12",50,1, s1);
		Despacho des2= new DespachoImpl("A1.03",20,2, s2);
		
		c.nuevoEspacio(des2);
		c.nuevoEspacio(des);
		
		/*System.out.println(c.getDespachos());
		System.out.println(c.getDespachos(d2));
		System.out.println(c.getProfesores());
		System.out.println(c.getProfesores(d1));
		System.out.println(c.getDespachosPorProfesor());*/
		
		System.out.println(Grados.getCentrosCreados());
		System.out.println(Grados.getNumCentrosCreados());
		System.out.println(Grados.getMaxPlantas());
		System.out.println(Grados.getMaxSotanos());
		System.out.println(Grados.getMediaPlantas());
		System.out.println(Grados.getMediaSotanos());
		
		Centro c1= Grados.createCentro(c);
		Centro c3 = Grados.createCentro("Matematicas","Reina Mercedes",5, 3);
		
		System.out.println(c1.getEspacios());
		System.out.println(Grados.getCentrosCreados());
		System.out.println(Grados.getNumCentrosCreados());
		System.out.println(Grados.getMaxPlantas());
		System.out.println(Grados.getMaxSotanos());
		System.out.println(Grados.getMediaPlantas());
		System.out.println(Grados.getMediaSotanos());
		
		List<Espacio> espacios= Grados.leeFichero("res/espacios.txt", s-> Grados.createEspacio(s));
		
		for(Espacio e: espacios){
			c3.nuevoEspacio(e);
		}
		
		System.out.println(Grados.getNumCentrosCreados());
		c3.nuevoEspacio(des);
		c3.nuevoEspacio(des2);
		
		
		System.out.println(c3.getEspaciosOrdenadosPorCapacidad());
		System.out.println(c3.getDespachos());
		System.out.println(c.getDespachos(d2));
		System.out.println(c.getDespachos(d1));
		System.out.println(c.getDespachos(d3));
		
		
		
		
		
	}
	
	
	private static void testConstructorNormal(){
		System.out.println("\n==================================Probando el primer constructor");
		testConstructor("ETSII","Reina Mercedes",3, 1);
	}
	
	private static void testConstructorExcepcion1(){
		System.out.println("\n==================================Probando el primer constructor, número de plantas menor que 1");
		testConstructor("ETSII","Reina Mercedes",0, 1);
	}
	
	private static void testConstructorExcepcion2(){
		System.out.println("\n==================================Probando el primer constructor, número de sotanos negativo");
		testConstructor("ETSII","Reina Mercedes",3, -2);
	}
	
	
	private static void testNuevoEspacioNormal(){
		System.out.println("\n==================================Probando nuevoEspacio");
		Centro c = new CentroImpl("ETSII","Reina Mercedes",3, 1);
		Espacio e1 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.12",50,2);
		testNuevoEspacio(c,e1);
	}
	
	private static void testNuevoEspacioExcepcion1(){
		System.out.println("\n==================================Probando nuevoEspacio, sotano incorrecto mayor al numero de sotanos");
		Centro c = new CentroImpl("ETSII","Reina Mercedes",1, 1);
		Espacio e1 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.12",50,-2);
		testNuevoEspacio(c,e1);
	}
	
	private static void testNuevoEspacioExcepcion2(){
		System.out.println("\n==================================Probando nuevoEspacio, planta incorrecta mayor al numero de plantas");
		Centro c = new CentroImpl("ETSII","Reina Mercedes",1, 1);
		Espacio e1 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.12",50,3);
		testNuevoEspacio(c,e1);
	}
	
	private static void testEliminaEspacio(){
		System.out.println("\n==================================Probando eliminaEspacio");
		Centro c = new CentroImpl("ETSII","Reina Mercedes",3, 1);
		Espacio e1 = new EspacioImpl(TipoEspacio.EXAMEN,"A3.12",50,2);
		c.nuevoEspacio(e1);
		testEliminaEspacio(c,e1);
	}
	
	
	private static void testOrder1(){
		Centro c1 = new CentroImpl("ETSII","Reina Mercedes",1, 1);
		Centro c2= new CentroImpl("Derecho","Reina Mercedes",1, 1);
		testOrder(c1,c2);
	}
	
	private static void testEquality1(){
		Centro c1 = new CentroImpl("ETSII","Reina Mercedes",1, 1);
		Centro c2= new CentroImpl("Derecho","Reina Mercedes",1, 1);
		testEquality(c1,c2);
	}
	
	
	
	/////METODOS AUXILIARES
	private static void testConstructor(String nombre,String direccion, Integer plantas,Integer sotanos){
		try {
			Centro c = new CentroImpl(nombre,direccion,plantas,sotanos);
			mostrarCentro(c);
		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testNuevoEspacio(Centro c,Espacio es){
		try {
			System.out.println("Los espacios antes de la operación son: " + c.getEspacios());
			System.out.println("Nuevo espacio: " + es);
			c.nuevoEspacio(es);
			System.out.println("Los espacios después de la operación son: "+ c.getEspacios());
		} catch (ExcepcionCentroOperacionNoPermitida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out.println("******************** Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEliminaEspacio(Centro c,Espacio es){
		System.out.println("Los espacios antes de la operación son: " + c.getEspacios());
		System.out.println("Elimina espacio: " + es);
		c.eliminaEspacio(es);
		System.out.println("Los espacios después de la operación son: "+ c.getEspacios());
	}
	
	private static void testOrder(Centro c1,Centro c2){
		System.out.println("\n");
		mostrarCentro(c1);
		mostrarCentro(c2);
		int cmp=c1.compareTo(c2);
		if(cmp==0){
			System.out.print("c1 and c2 are equal");
		}
		else if(cmp<0){
			System.out.print("c1 is lower than c2");
		}
		else{
			System.out.print("c1 is greater than c2");
		}
	}
	
	private static void testEquality(Centro c1,Centro c2){
		System.out.println("\n");
		mostrarCentro(c1);
		mostrarCentro(c2);
		System.out.println("hashCode of c1:"+c1.hashCode());
		System.out.println("hashCode of c2:"+c2.hashCode());
		System.out.println("Are c1 and c2 equal?: "+c1.equals(c2));
	}
	
	
	
	private static void mostrarCentro(Centro c){
		
		System.out.println("Centro --> <" + c + ">");
		System.out.println("\tNombre: <" + c.getNombre() + ">");
		System.out.println("\tDireccion: <" + c.getDireccion() + ">");
		System.out.println("\tNúmero de plantas: <" + c.getNumeroPlantas() + ">");
		System.out.println("\tNúmero de sótanos: <" + c.getNumeroSotanos() + ">");
		System.out.println("\tEspacios:  <"+ c.getEspacios()+ ">");
	
	}

}