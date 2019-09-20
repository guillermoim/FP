package fp.grados.tipos.test;

import java.util.List;

import fp.grados.tipos.Alumno;
import fp.grados.utiles.Grados;

public class TestAlumnoConstructorCadena {

	public static void main(String[] args) {
		
		testConstructor();

	}
	
	private static void testConstructor(){
		List<Alumno> alumnos = Grados.createAlumnos("res/alumnos.txt");
		for(Alumno a:alumnos){
			System.out.println(a);
		}
	}

}
