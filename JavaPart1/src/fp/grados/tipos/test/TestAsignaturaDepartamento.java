package fp.grados.tipos.test;


import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;


public class TestAsignaturaDepartamento {

	public static void main(String[] args) {
		
		
		Departamento d1 =new DepartamentoImpl("LSI");
		Departamento d2 =new DepartamentoImpl("CCIA");
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001", 12.0, TipoAsignatura.ANUAL, 1,d1);
		@SuppressWarnings("unused")
		Asignatura a2 = new AsignaturaImpl("Fundamentos de Programación","2050005",12.0, TipoAsignatura.ANUAL, 1,d2);
		
		System.out.println("the department " +d1+" has the subjects"+d1.getAsignaturas());
		System.out.println("the department " +d2+" has the subjects"+d2.getAsignaturas());
		
		
		a1.setDepartamento(d2);
		System.out.println("After executing setDepartamento");
		System.out.println("the department " +d1+" has the subjects"+d1.getAsignaturas());
		System.out.println("the department " +d2+" has the subjects"+d2.getAsignaturas());
		System.out.println("the subject "+a1+" belongs to the department: "+a1.getDepartamento() );
		
		d2.nuevaAsignatura(a1);
		System.out.println(d2.getAsignaturas());
		System.out.println(a1.getDepartamento());
		
		
	}
}

	