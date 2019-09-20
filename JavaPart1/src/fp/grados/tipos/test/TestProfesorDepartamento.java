package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;

public class TestProfesorDepartamento {
	
@SuppressWarnings("unused")
public static void main(String[] args) {
	

	Departamento d1 =new DepartamentoImpl("LSI");
	Departamento d2 =new DepartamentoImpl("CCIA");
	Departamento d3= Grados.createDepartamento("LSI");
	Departamento d4= Grados.createDepartamento("CCIA");
	
	Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.CATEDRATICO,d3);
	
	Profesor p2 = new ProfesorImpl("12345678Z", "Pepe", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.COLABORADOR,d3);
	
	Profesor p3 = new ProfesorImpl("12345678Z", "Manuel", "A",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.INTERINO,d4);
	Profesor p4 = new ProfesorImpl("12345678Z", "Javier", "B",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",Categoria.AYUDANTE,d4);
	
	System.out.println("the department " +d3+" has the teachers"+d3.getProfesores());
	System.out.println("the department " +d4+" has the teachers"+d4.getProfesores());
	
	p1.setDepartamento(d4);
	System.out.println("After executing setDepartamento");
	System.out.println("the department " +d3+" has the teachers"+d3.getProfesores());
	System.out.println("the department " +d4+" has the teachers"+d4.getProfesores());
	
	System.out.println("Teacher "+p1+" is in the department "+p1.getDepartamento());
	
	p1.nuevaTutoria(LocalTime.of(11, 30, 0),60,DayOfWeek.TUESDAY);
	p2.nuevaTutoria(LocalTime.of(10, 30, 0),90,DayOfWeek.WEDNESDAY);
	p1.nuevaTutoria(LocalTime.of(9, 30, 0),60,DayOfWeek.FRIDAY);
	
	System.out.println(p1.getTutorias());
	System.out.println(p2.getTutorias());
	
	d3.borraTutorias();
	System.out.println(p1.getTutorias());
	System.out.println(p2.getTutorias());
	
	
	p1.nuevaTutoria(LocalTime.of(11, 30, 0),60,DayOfWeek.TUESDAY);
	p1.setDepartamento(d3);
	p2.nuevaTutoria(LocalTime.of(10, 30, 0),90,DayOfWeek.WEDNESDAY);
	p1.nuevaTutoria(LocalTime.of(9, 30, 0),60,DayOfWeek.FRIDAY);
	
	d3.borraTutorias(Categoria.CATEDRATICO);
	System.out.println(p1.getTutorias());
	System.out.println(p2.getTutorias());
	
	Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001", 12.0, TipoAsignatura.ANUAL, 1,d3);
	Asignatura a2 = new AsignaturaImpl("Fisica","1123456",12.0, TipoAsignatura.ANUAL, 1,d3);
	Asignatura a3 = new AsignaturaImpl("Mates","2123454",12.0, TipoAsignatura.ANUAL, 1,d3);
	

	
	p1.imparteAsignatura(a1, 1.0);
	p1.imparteAsignatura(a2, 0.5);
	p2.imparteAsignatura(a3, 12.0);
	
	
	System.out.println(d3.getAsignaturas());
	System.out.println(d3.existeProfesorAsignado(a2));
	System.out.println(d3.estanTodasAsignaturasAsignadas());
	
	System.out.println(p1.getAsignaturas());
	
	d3.eliminaAsignacionProfesorado(a2);
	System.out.println(d3.getAsignaturas());
	System.out.println(p1.getAsignaturas());
	System.out.println(p2.getAsignaturas());
	System.out.println(d3.existeProfesorAsignado(a1));
	System.out.println(d3.estanTodasAsignaturasAsignadas());
	
	
	System.out.println(d3.getProfesoresPorAsignatura());
	System.out.println(d3.getTutoriasPorProfesor());
	
	
	
	
	
}

}