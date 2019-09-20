package fp.grados.tipos.test;

import java.util.List;

import fp.grados.tipos.Espacio;
import fp.grados.utiles.Grados;

public class TestEspacioConstructorCadena {

	public static void main(String[] args) {
		
		test();
		

	}
	
	private static void test(){
		List<Espacio> lista = Grados.createEspacios("res/espacios.txt");
		for(Espacio e:lista){
			System.out.println(e);
		}
		
	}

}
