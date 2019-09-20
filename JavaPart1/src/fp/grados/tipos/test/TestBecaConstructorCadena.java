package fp.grados.tipos.test;

import java.util.List;

import fp.grados.tipos.Beca;
import fp.grados.utiles.Grados;

public class TestBecaConstructorCadena {

	public static void main(String[] args) {
		
		test();

	}
	
	private static void test(){
		List<Beca> lista = Grados.createBecas("res/becas.txt");
		for(Beca b:lista){
			System.out.println(b);
		}
	}

}
