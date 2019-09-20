package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;

public class TestGestionExpedienteAlumno {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 2,d1);
		Asignatura b = new AsignaturaImpl("Matematicas","2050005",12.0, TipoAsignatura.ANUAL, 1,d1);
		Nota n1 = new NotaImpl(a, 2015, Convocatoria.PRIMERA, 9.7, true);
		Nota n2 = new NotaImpl(b, 2014, Convocatoria.PRIMERA, 6.0, false);
		Nota n3 = new NotaImpl(b, 2014, Convocatoria.PRIMERA,7.0,false);
		
		Alumno al= new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Alumno alum= Grados.createAlumno(al);
		alum.matriculaAsignatura(a);
		alum.matriculaAsignatura(b);
		System.out.println(alum);
	
		System.out.println(alum.getExpediente());
		alum.evaluaAlumno(a, 2015, Convocatoria.PRIMERA, 9.7,true);
		alum.evaluaAlumno(a, 2015, Convocatoria.SEGUNDA, 9.6,false);
		alum.evaluaAlumno(a, 2015, Convocatoria.SEGUNDA, 9.0,false);
		alum.evaluaAlumno(b, 2015, Convocatoria.PRIMERA,9.0,false);
		alum.evaluaAlumno(b, 2015, Convocatoria.SEGUNDA, 6.0,false);
		
		System.out.println(alum.getAsignaturas());
		System.out.println(alum.getExpediente());
		System.out.println(alum.getCalificacionPorAsignatura());

	}

}
