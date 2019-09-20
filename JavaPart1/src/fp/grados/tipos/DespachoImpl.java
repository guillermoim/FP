package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho{
	private Set<Profesor> profesores;
	private final static TipoEspacio TIPO=TipoEspacio.OTRO;
	
	public DespachoImpl(String nombre, Integer capacidad, 
			Integer planta, Set<Profesor> profesores){
		super(TIPO, nombre, capacidad, planta);
		checkProfesores(profesores, capacidad);
		this.profesores=profesores;
	}
	
	public DespachoImpl(String nombre, Integer capacidad, 
			Integer planta, Profesor profesor){
		super(TIPO, nombre, capacidad, planta);
		this.profesores=new HashSet<Profesor>();
		profesores.add(profesor);
	}
	
	public DespachoImpl(String nombre, Integer capacidad, 
			Integer planta){
		super(TIPO, nombre, capacidad, planta);
		this.profesores=new HashSet<Profesor>();
	}
	
	public DespachoImpl(String s){
		super(s+",OTRO");
		this.profesores=new HashSet<Profesor>();
	}
	
	public Set<Profesor> getProfesores(){
		return new HashSet<Profesor>(profesores);
	}
	
	public void setProfesores(Set<Profesor> nuevosProfesores){
		checkProfesores(nuevosProfesores,this.getCapacidad());
		profesores.addAll(nuevosProfesores);
	}
	
	//Método setTipo redefinido
	
	public void setTipo(TipoEspacio e){
		throw new UnsupportedOperationException();
	}
	
	//Método setCapacidad Redefinido
	
	public void setCapacidad(Integer capacidad){
		checkProfesores(this.profesores, capacidad);
		super.setCapacidad(capacidad);	
	}
	//Método toString redefinido
	
	public String toString(){
		return super.toString()+" "+getProfesores().toString();
	}
	
	//Métodos checkers
	
	public void checkProfesores(Set<Profesor> profesores, Integer capacidad){
		if(profesores.size()>capacidad){
			throw new ExcepcionDespachoNoValido("El número de profesores no puede exceder la capacidad del Despacho");
		}
	}
	
}
