package fp.grados.tipos.test;

import java.util.List;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;



public class TestAsignatura {

	public static void main(String[] args) {
		testConstructorNormal();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructorExcepcion4();
		testConstructorExcepcion5();
		testConstructorExcepcion6();
		testConstructorExcepcion7();		
		
		testEquality1();
		testOrder1();
		
		List<Asignatura> asignaturas = Grados.leeFichero("res/asignaturas.txt",s->new AsignaturaImpl(s));
		
		for(Asignatura e:asignaturas){
			System.out.println(e);
		}
	}
	
	private static void testOrder1(){
		Departamento d1 =new DepartamentoImpl("LSI");
		Departamento d2= new DepartamentoImpl("DTE");
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001", 12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura a2 = new AsignaturaImpl("Fundamentos de Programación","2050005",12.0, TipoAsignatura.ANUAL, 1,d2);
		testOrder(a1,a2);
	}
	
	private static void testOrder(Asignatura a1, Asignatura a2){
		mostrarAsignatura(a1);
		mostrarAsignatura(a2);
		int cmp=a1.compareTo(a2);
		if(cmp==0){
			System.out.print("a1 and a2 are equal");
		}
		else if(cmp<0){
			System.out.print("a1 is lower than a2");
		}
		else{
			System.out.print("a1 is greater than a2");
		}
	}
		
	private static void testEquality1(){
		Departamento d1 =new DepartamentoImpl("LSI");
		Departamento d2= new DepartamentoImpl("DTE");
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001", 12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura a2 = new AsignaturaImpl("Fundamentos de Programación","2050005",12.0, TipoAsignatura.ANUAL, 1,d2);
		testEquality(a1,a2);
		
	}
	
	private static void testEquality(Asignatura a1, Asignatura a2){
		mostrarAsignatura(a1);
		mostrarAsignatura(a2);
		System.out.println("hashCode of a1:"+a1.hashCode());
		System.out.println("hashCode of a2:"+a2.hashCode());
		System.out.println("Are a1 and a2 equal?: "+a1.equals(a2));
		
	}
	
	/******************************** CASOS DE PRUEBA **************************/

	private static void testConstructorNormal() {
		System.out.println("==================================Probando el constructor");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos Físicos de la Informática","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
	}

	private static void testConstructorExcepcion1() {
		System.out.println("==================================Probando el constructor, código de asignatura más largo");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos de Programación","20500010",12.0, TipoAsignatura.ANUAL, 1,d1);
	}
	
	private static void testConstructorExcepcion2() {
		System.out.println("==================================Probando el constructor, código de asignatura más corto");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos de Programación","205000",12.0, TipoAsignatura.ANUAL, 1,d1);
	}
	
	private static void testConstructorExcepcion3() {
		System.out.println("==================================Probando el constructor, código de asignatura no numérico");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos de Programación","2A50001",12.0, TipoAsignatura.ANUAL, 1,d1);
	}
	
	private static void testConstructorExcepcion4() {
		System.out.println("==================================Probando el constructor, créditos incorrectos (0.0)");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos de Programación","2050001",0.0, TipoAsignatura.ANUAL, 1,d1);
	}
		
	private static void testConstructorExcepcion5() {
		System.out.println("==================================Probando el constructor, créditos incorrectos (-1.0)");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos de Programación","2050001",-1.0, TipoAsignatura.ANUAL, 1,d1);
	}
	
	
	private static void testConstructorExcepcion6() {
		System.out.println("==================================Probando el constructor, curso menor de 1");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, -2,d1);
	}
	
	private static void testConstructorExcepcion7() {
		System.out.println("==================================Probando el constructor, curso mayor de 4");
		Departamento d1 =new DepartamentoImpl("LSI");
		testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 5,d1);
	}
	
	/******************************** METODOS AUXILIARES **************************/
	
	private static void testConstructor(String nombre, String codigo, Double creditos,TipoAsignatura tipo, Integer curso,Departamento d) {
		try {
			Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso,d);
			mostrarAsignatura(a);
		} catch (ExcepcionAsignaturaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionAsignaturaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}


	private static void mostrarAsignatura(Asignatura a) {		
		System.out.println("Asignatura --> <" + a + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tAcrónimo: <" + a.getAcronimo() + ">");
		System.out.println("\tCódigo: <" + a.getCodigo() + ">");
		System.out.println("\tCréditos: <" + a.getCreditos() + ">");
		System.out.println("\tTipo: <" + a.getTipo() + ">");
		System.out.println("\tCurso: <" + a.getCurso() + ">");
		System.out.println("\tDepartamento: <" +a.getDepartamento()+ ">");
	}

}
