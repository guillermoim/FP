package fp.grados.tipos.test;

import java.util.List;

import fp.grados.tipos.Persona;
import fp.grados.tipos.PersonaImpl;
import fp.grados.utiles.Grados;

public class TestPersonaConstructorCadena {

	public static void main(String[] args) {
		testConstructorCadenaPersona();

	}
	
	private static void testConstructorCadenaPersona(){
		List<Persona> personas = Grados.leeFichero("res/personas.txt", s -> new PersonaImpl(s));
		for(Persona p:personas){
			System.out.println(p);
		}
	}
	
}
