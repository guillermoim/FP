package fp.grados.tipos.test;

import java.util.List;

import fp.grados.tipos.Asignatura;
import fp.grados.utiles.Grados;

public class TestAsignaturasConstructorCadena {
	
	public static void main(String[] args) {
		test();
		
	}
	
	private static void test(){
		List<Asignatura> lista = Grados.createAsignaturas("res/asignaturas.txt");
		for(Asignatura a:lista){
			System.out.println(a);
		}
	}
}
