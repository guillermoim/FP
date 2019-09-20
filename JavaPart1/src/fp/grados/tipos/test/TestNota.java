package fp.grados.tipos.test;


import java.util.List;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;

public class TestNota {

	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor1Excepcion1();
		testConstructor1Excepcion2();
		testConstructor1Excepcion3();
		
		testConstructor2Normal();
		testConstructor2Excepcion1();
		testConstructor2Excepcion2();
		
		testEquality1();
		testOrder1();
		
		List<Nota> notas = Grados.leeFichero("res/notas.txt",s->new NotaImpl(s));
		
		for(Nota n:notas){
			System.out.println(n);
		}
	}
	
	private static void testOrder1(){
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura b = new AsignaturaImpl("Matematicas","2050005",12.0, TipoAsignatura.ANUAL, 1,d1);
		Nota n1 = new NotaImpl(a, 2015, Convocatoria.PRIMERA, 9.7, true);
		Nota n2 = new NotaImpl(b, 2014, Convocatoria.PRIMERA, 9.7, true);
		testOrder(n1,n2);
	}
	
	private static void testOrder(Nota n1, Nota n2){
		mostrarNota(n1);
		mostrarNota(n2);
		int cmp= n1.compareTo(n2);
		if(cmp==0){
			System.out.print("n1 and n2 are equal");
		}
		else if(cmp<0){
			System.out.print("n1 is lower than n2");
		}
		else{
			System.out.print("n1 is greater than n2");
		}
	}
		
	private static void testEquality1(){
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura b = new AsignaturaImpl("Matematicas","2050005",12.0, TipoAsignatura.ANUAL, 1,d1);
		Nota n1 = new NotaImpl(a, 2014, Convocatoria.PRIMERA, 9.7, true);
		Nota n2 = new NotaImpl(b, 2014, Convocatoria.PRIMERA, 9.7, true);
		testEquality(n1,n2);
		
	}
	
	private static void testEquality(Nota n1, Nota n2){
		mostrarNota(n1);
		mostrarNota(n2);
		System.out.println("hashCode of n1:"+n1.hashCode());
		System.out.println("hashCode of n2:"+n2.hashCode());
		System.out.println("Are n1 and n2 equal?: "+n1.equals(n2));
		
	}
	
	
	private static void testConstructor1Normal() {
		System.out.println("==================================Probando el primer constructor ");
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		testConstructor1(a, 2016, Convocatoria.PRIMERA, 9.7, true);
	}
	
	private static void testConstructor1Excepcion1() {
		System.out.println("==================================Probando el primer constructor, valor negativo ");
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		testConstructor1(a, 2014, Convocatoria.PRIMERA, -1.0, false);
	}
	
	private static void testConstructor1Excepcion2() {
		System.out.println("==================================Probando el primer constructor, valor mayor que 10 ");
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		testConstructor1(a, 2014, Convocatoria.PRIMERA, 11.0, false);
	}
	
	private static void testConstructor1Excepcion3() {
		System.out.println("==================================Probando el primer constructor, mencion de honor sin tener al menos un  9 ");
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		testConstructor1(a, 2014, Convocatoria.PRIMERA, 8.9, true);
	}
	
	
	
	
	private static void testConstructor2Normal() {
		System.out.println("==================================Probando el segundo constructor ");
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		testConstructor2(a, 2014, Convocatoria.PRIMERA, 6.0);
	}
	
	private static void testConstructor2Excepcion1() {
		System.out.println("==================================Probando el segundo constructor, valor negativo ");
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		testConstructor1(a, 2014, Convocatoria.PRIMERA, -1.0, false);
	}
	
	private static void testConstructor2Excepcion2() {
		System.out.println("==================================Probando el segundo constructor, valor mayor que 10 ");
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		testConstructor1(a, 2014, Convocatoria.PRIMERA, 11.0, false);
	}
	
	

	
	
	private static void testConstructor1(Asignatura asignatura, Integer curso,Convocatoria convocatoria, Double valor, Boolean mencionHonor) {
		try {
			Nota n = new NotaImpl(asignatura, curso, convocatoria,valor, mencionHonor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testConstructor2(Asignatura asignatura, Integer curso,Convocatoria convocatoria, Double valor) {
		try {
			Nota n = new NotaImpl(asignatura, curso, convocatoria,valor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	

	
	private static void mostrarNota(Nota n) {
		System.out.println("Nota --> <" + n + ">");
		System.out.println("\tAsignatura: <" + n.getAsignatura() + ">");
		System.out.println("\tCurso académico: <" + n.getCursoAcademico() + ">");
		System.out.println("\tConvocatoria: <" + n.getConvocatoria() + ">");
		System.out.println("\tValor: <" + n.getValor() + ">");
		System.out.println("\tCalificacion: <" + n.getCalificacion() + ">");
		System.out.println("\tMención de Honor: <" + n.getMencionHonor() + ">");
	}
}