package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.*;


public class TestTutoria {

	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor1Excepcion1();
		testConstructor1Excepcion2();
		testConstructor1Excepcion3();
		
		testConstructor2Normal();
		testConstructor2Excepcion1();
		testConstructor2Excepcion2();
		testConstructor2Excepcion3();
		
		testEquality1();
		testOrder1();
		
		
		
	}
	
	private static void testOrder1(){
		Tutoria t1 = new TutoriaImpl(DayOfWeek.TUESDAY, LocalTime.of(11, 30, 0),LocalTime.of(12, 30, 0));
		Tutoria t2 = new TutoriaImpl(DayOfWeek.TUESDAY, LocalTime.of(10, 30, 0),LocalTime.of(12, 30, 0));
		testOrder(t1,t2);
	}
	
	private static void testOrder(Tutoria t1, Tutoria t2){
		mostrarTutoria(t1);
		mostrarTutoria(t2);
		int cmp=t1.compareTo(t2);
		if(cmp==0){
			System.out.print("t1 and t2 are equal");
		}
		else if(cmp<0){
			System.out.print("t1 is lower than t2");
		}
		else{
			System.out.print("t1 is greater than t2\n");
		}
	}
		
	private static void testEquality1(){
		Tutoria t1 = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(9, 30, 0),LocalTime.of(12, 30, 0));
		Tutoria t2 = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(10, 30, 0),LocalTime.of(12, 30, 0));
		testEquality(t1,t2);
		
	}
	
	private static void testEquality(Tutoria t1, Tutoria t2){
		mostrarTutoria(t1);
		mostrarTutoria(t2);
		System.out.println("hashCode of t1:"+t1.hashCode());
		System.out.println("hashCode of b2:"+t2.hashCode());
		System.out.println("Are b1 and b2 equal?: "+t1.equals(t2));
		
	}
	
	private static void testConstructor1Normal() {
		System.out.println("==================================Probando el primer constructor ");
		testConstructor1(DayOfWeek.MONDAY, LocalTime.of(10, 30, 0),LocalTime.of(12, 30, 0));
	}
	
	private static void testConstructor1Excepcion1() {
		System.out.println("==================================Probando el primer constructor, dia sabado ");
		testConstructor1(DayOfWeek.SATURDAY, LocalTime.of(10, 30, 0),LocalTime.of(12, 30, 0));
	}
	
	private static void testConstructor1Excepcion2() {
		System.out.println("==================================Probando el primer constructor, dia domingo");
		testConstructor1(DayOfWeek.SUNDAY, LocalTime.of(10, 30, 0),LocalTime.of(12, 30, 0));
	}
	private static void testConstructor1Excepcion3() {
		System.out.println("==================================Probando el primer constructor, duracion menor de 15 minutos ");
		testConstructor1(DayOfWeek.WEDNESDAY, LocalTime.of(10, 30, 0),LocalTime.of(10, 40, 0));
	}
	
	
	
	
	private static void testConstructor2Normal() {
		System.out.println("==================================Probando el segundo constructor ");
		testConstructor2(DayOfWeek.THURSDAY, LocalTime.of(10, 30, 0),120);
	}
	
	private static void testConstructor2Excepcion1() {
		System.out.println("==================================Probando el segundo constructor, dia sabado ");
		testConstructor2(DayOfWeek.SATURDAY, LocalTime.of(10, 30, 0),120);
	}
	
	private static void testConstructor2Excepcion2() {
		System.out.println("==================================Probando el segundo constructor, dia domingo ");
		testConstructor2(DayOfWeek.SUNDAY, LocalTime.of(10, 30, 0),120);
	}
	
	
	private static void testConstructor2Excepcion3() {
		System.out.println("==================================Probando el segundo constructor, duracion menor de 15 minutos ");
		testConstructor2(DayOfWeek.WEDNESDAY, LocalTime.of(10, 30, 0),5);
	}
	
	
	
	
	private static void testConstructor1(DayOfWeek d,LocalTime hc, LocalTime hf) {
		try {
			Tutoria t = new TutoriaImpl(d,hc,hf);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testConstructor2(DayOfWeek d,LocalTime hc, Integer du) {
		try {
			Tutoria t = new TutoriaImpl(d,hc,du);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void mostrarTutoria(Tutoria t){
		
		System.out.println("Tutoria --> <" + t + ">");
		System.out.println("\tDía de la semana: <" + t.getDiaSemana() + ">");
		System.out.println("\tHora de comienzo: <" + t.getHoraComienzo() + ">");
		System.out.println("\tHora de fin: <" + t.getHoraFin() + ">");
		System.out.println("\tDuración: <" + t.getDuracion() + ">");

	}
	
}
