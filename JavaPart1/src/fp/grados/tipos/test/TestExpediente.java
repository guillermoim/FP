package fp.grados.tipos.test;

import java.util.List;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Expediente;
import fp.grados.tipos.ExpedienteImpl;
import fp.grados.tipos.ExpedienteImpl2;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;

public class TestExpediente {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Departamento d1 =new DepartamentoImpl("LSI");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura b = new AsignaturaImpl("Matematicas","2050005",12.0, TipoAsignatura.ANUAL, 1,d1);
		Asignatura c = new AsignaturaImpl("Fisica","2051205",12.0, TipoAsignatura.ANUAL, 1,d1);
		Nota n1 = new NotaImpl(a, 2014, Convocatoria.PRIMERA, 8.0, false);
		Nota n2 = new NotaImpl(a, 2014, Convocatoria.SEGUNDA, 6.0, false);
		Nota n3 = new NotaImpl(a, 2015, Convocatoria.TERCERA,7.0,false);
		Nota n4 = new NotaImpl(b, 2014, Convocatoria.SEGUNDA,8.0,false);
		
		
		
		Expediente e1= new ExpedienteImpl2();
		Expediente e2= new ExpedienteImpl();
		Expediente e3= new ExpedienteImpl2();
		
		
		e1.nuevaNota(n1);
		e1.nuevaNota(n2);
		e2.nuevaNota(n1);
		e2.nuevaNota(n2);
		
		System.out.println(e1.getNotas());
		
		System.out.println(e1.equals(e2));
		
		e1.nuevaNota(n3);
		e1.nuevaNota(n4);
		
		
		System.out.println(e1.getNotas());
		
		System.out.println(e1.getNotaMedia());
		System.out.println(e3.getNotaMedia());
		
		List<Nota> notas= Grados.leeFichero("res/notas.txt", s-> new NotaImpl(s));
		Expediente e4= new ExpedienteImpl();
		Expediente e5= new ExpedienteImpl();
		
		for(Nota n: notas){
			e4.nuevaNota(n);
		}
		
		
		for(Nota n : e4.getNotasOrdenadasPorAsignatura()){
			System.out.println(n);
		}
		
		System.out.println("\n"+e4.getMejorNota());
		
	}

}
