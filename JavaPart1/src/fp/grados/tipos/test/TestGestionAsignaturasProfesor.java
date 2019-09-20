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

public class TestGestionAsignaturasProfesor {
	
	public static void main(String[] args) {
		
	Departamento d1 =new DepartamentoImpl("LSI");
	Departamento d2 =new DepartamentoImpl("Fisica Aplicada");
	Profesor p1= new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
	Profesor p2= new ProfesorImpl("12345678Z", "Manuel", "Carranza",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.AYUDANTE,d2);
	
	Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001", 20.0, TipoAsignatura.ANUAL, 1,d1);
	Asignatura a2 = new AsignaturaImpl("Fisica","2050005", 12.0, TipoAsignatura.ANUAL, 1,d2);
	Asignatura a3 = new AsignaturaImpl("ADDA","2050003", 12.0, TipoAsignatura.ANUAL, 1,d1);
	p1.imparteAsignatura(a1, 20.0);
	p1.imparteAsignatura(a3, 4.0);
	p2.imparteAsignatura(a2, 5.0);
	System.out.println(p1.getAsignaturas());
	System.out.println(p2.getAsignaturas());
	System.out.println(p1.dedicacionAsignatura(a1));
	
	//p1.imparteAsignatura(a1, 12.0);
	System.out.println(p1.dedicacionAsignatura(a1));
	
	System.out.println(p1.getCreditos());
	System.out.println(p1.getDedicacionTotal());
	
	p1.eliminaAsignatura(a3);
	p1.imparteAsignatura(a1, 12.0);
	System.out.println(p1.getAsignaturas());
	System.out.println(p1.getCreditos());
	
	System.out.println(p1.getDedicacionTotal());
	
	
	
	
	}
	
	
	
}



