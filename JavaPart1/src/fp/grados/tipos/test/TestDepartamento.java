package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestDepartamento {

	public static void main(String[] args) {

		testGetProfesoresPorAsignatura();
		
		testConstructorNormal();
		
		testRelacionBidireccionalAsignaturas();
		
		testRelacionBidireccionalProfesores();
		
		testIgualdad();
		testOrden();
	}
	//Casos de prueba 	T9
	
	private static void testGetProfesoresPorAsignatura(){
		System.out
		.println("==================================Probando getProfesoresPorAsignatura");
		System.out
		.println("*Creando departamento y añadiendo dos asignaturas*");

		Departamento d = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, d);
		Asignatura adda = new AsignaturaImpl("Analisis de Datos y Diseño de Algoritmos",
				"2050002", 12.0, TipoAsignatura.ANUAL, 1, d);
		
		d.nuevaAsignatura(fp);
		d.nuevaAsignatura(adda);
		System.out
		.println("*Añadiendo profesores a asignaturas*");
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es",Categoria.AYUDANTE);
		p1.setDepartamento(d);
		Profesor p2 = new ProfesorImpl("12345678Z", "Pepe", "Nadie Nadie",LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es",Categoria.CATEDRATICO);
		p2.setDepartamento(d);
		p1.imparteAsignatura(fp, 3.);
		p2.imparteAsignatura(adda, 3.);
		
		
		mostrarProfesoresPorAsignatura(d);
	}
	
	private static void mostrarProfesoresPorAsignatura(Departamento d){
		
		System.out.println(d.getProfesoresPorAsignatura());
		
	}
	
	
	
	
	
	
	// Casos de prueba
	
	private static void testConstructorNormal() {
		System.out
				.println("==================================Probando el primer constructor");
		Departamento d = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		mostrarDepartamento(d);
	}
	
	private static void testRelacionBidireccionalAsignaturas() {
		System.out.println("\n==================================Probando la "
				+ "relación bidireccional entre Departamento y Asignatura");
		Departamento lsi = new DepartamentoImpl(
				"Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl(
				"Ciencias de la Computación e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, lsi);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		
		System.out.println("\n****Añadimos la asignatura FP al departamento CCIA, "
			+ "mediante la operación nuevaAsignatura del tipo Departamento:");
		ccia.nuevaAsignatura(fp);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);

		System.out.println("\n****Cambiamos al departamento LSI la asignatura FP, "
			+ "mediante la operación setDepartamento del tipo Asignatura:");
		fp.setDepartamento(lsi);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		
		System.out.println("\n****Eliminamos del departamento LSI la asignatura FP, "
			+ "mediante la operación eliminaAsignatura del tipo Departamento:");

		lsi.eliminaAsignatura(fp);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		
	}
	
	private static void testRelacionBidireccionalProfesores() {
		System.out.println("\n==================================Probando la "
				+ "relación bidireccional entre Departamento y Asignatura");
		Departamento lsi = new DepartamentoImpl(
				"Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl(
				"Ciencias de la Computación e Inteligencia Artificial");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es",Categoria.AYUDANTE);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(p);
		
		System.out.println("\n****Añadimos la asignatura FP al departamento CCIA, "
			+ "mediante la operación nuevaAsignatura del tipo Departamento:");
		ccia.nuevoProfesor(p);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(p);

		System.out.println("\n****Cambiamos al departamento LSI la asignatura FP, "
			+ "mediante la operación setDepartamento del tipo Asignatura:");
		p.setDepartamento(lsi);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(p);
		
		System.out.println("\n****Eliminamos del departamento LSI la asignatura FP, "
			+ "mediante la operación eliminaAsignatura del tipo Departamento:");

		lsi.eliminaProfesor(p);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(p);
		
	}
	
	private static void testIgualdad() {
		System.out.println("\n===============================Probando igualdad con dos objetos iguales");		
		Departamento d1 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento d2 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");		
		Departamento d3 = new DepartamentoImpl("Arquitectura y Tecnología de Computadores");

		System.out.println("Código hash del objeto d1 (" + d1 + "): " + d1.hashCode());
		System.out.println("Código hash del objeto d2 (" + d2 + "): " + d2.hashCode());
		System.out.println("Código hash del objeto d3 (" + d3 + "): " + d3.hashCode());
		
		System.out.println("¿Es d1 igual a d2? (debe ser true): " + d1.equals(d2));
		System.out.println("¿Es d1 distinto de d3? (debe ser true): " + !d1.equals(d3));
	}

	// Nuevo en T4:
	private static void testOrden() {
		System.out.println("\n===============================Probando orden natural");
		// Creamos cuatro objetos tales que menor < igual1 == igual2 < mayor
		Departamento menor = new DepartamentoImpl("Arquitectura y Tecnología de Computadores");
		Departamento igual1 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento igual2 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento mayor = new DepartamentoImpl("Tecnología Electrónica");

		System.out.print("(debe ser ANTES) ");
		compara(menor,igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1,igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2,mayor);
	}
	
	// Métodos auxiliares
	// Nuevo en T4:
	private static void compara(Departamento d1, Departamento d2) {
		System.out.print("El objeto <" + d1 + ">");
		if (d1.compareTo(d2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (d1.compareTo(d2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + d2 + ">");
	}

	private static void mostrarDepartamento(Departamento d) {
		System.out.println("Departamento --> <" + d + ">");
		System.out.println("Asignaturas: <" + d.getAsignaturas() + ">");
		// TODO: Descomentar cuando se implemente la propiedad "profesores".
		 System.out.println("\tProfesores: <" + d.getProfesores() + ">");		
	}
	
	private static void mostrarAsignatura(Asignatura a) {
		System.out.println("Asignatura --> <" + a + ">");
		System.out.println("\tDepartamento: <" + a.getDepartamento() + ">");
	}
	
	private static void mostrarProfesor(Profesor p){
		System.out.println("Profesor --> <" + p + ">");
		System.out.println("\tDepartamento: <" + p.getDepartamento() + ">");
	}
	
}