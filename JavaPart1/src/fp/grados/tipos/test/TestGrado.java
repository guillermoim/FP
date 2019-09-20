package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;


public class TestGrado {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Departamento d1 =new DepartamentoImpl("LSI");
		Departamento d2 =new DepartamentoImpl("CCIA");
		Departamento d3 =new DepartamentoImpl("DTE");
		
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d1);
		Profesor p2 = new ProfesorImpl("12345678Z", "Manuel", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@us.es",Categoria.CATEDRATICO,d2);
		
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación","2050001", 12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura a2 = new AsignaturaImpl("Física","2050005",12.0, TipoAsignatura.ANUAL, 3,d2);
		Asignatura a3 = new AsignaturaImpl("Mates","2050006", 12.0, TipoAsignatura.SEGUNDO_CUATRIMESTRE, 2,d3);
		Asignatura a4 = new AsignaturaImpl("Circuitos","2050008",12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2,d2);
		Asignatura a5 = new AsignaturaImpl("Calculo","2050004",12.0, TipoAsignatura.SEGUNDO_CUATRIMESTRE, 1,d2);
		
	
		
		Set<Asignatura> obligatorias = new HashSet<Asignatura>();
		obligatorias.add(a1);
		obligatorias.add(a2);
		Set<Asignatura> optativas = new HashSet<Asignatura>();
		optativas.add(a3);
		optativas.add(a4);
		optativas.add(a5);
		
		
		
		Grado g = new GradoImpl("Grado en Ingeniería Informática",obligatorias,optativas, 30.0);
		
		System.out.println(g);
		System.out.println(g.getAsignaturasObligatorias());
		System.out.println(g.getAsignaturasOptativas());
		System.out.println(g.getNumeroMinimoCreditosOptativas());
		System.out.println(g.getNumeroTotalCreditos());
		System.out.println(g.getDepartamentos());
		System.out.println(g.getProfesores());
		System.out.println(g.getAsignaturas(1));
		System.out.println(g.getAsignatura("2050007"));
		System.out.println(g.getCreditosPorAsignatura());
		
		Grado g1 = Grados.createGrado("Grado en Ingeniería Informática",obligatorias,optativas, 20.0);
		System.out.println(g1.getNumeroTotalCreditos());
		System.out.println(g1.getAsignaturas(4));
		System.out.println(g1.getAsignatura("2050004"));
		System.out.println(g1.getDepartamentos());
		System.out.println(g1.getProfesores());
		System.out.println(g1.getCreditosPorAsignatura());
		
		
	
	}

}
