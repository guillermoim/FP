package fp.grados.tipos;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro{
	
	private String nombre;
	private String direccion;
	private Integer plantas;
	private Integer sotanos;
	private Set<Espacio> espacios;
	
	public CentroImpl(String nombre, String direccion, Integer plantas, Integer sotanos){
		checkPlantas(plantas);
		checkSotanos(sotanos);
		
		this.nombre=nombre;
		this.direccion=direccion;
		this.plantas=plantas;
		this.sotanos=sotanos;
		this.espacios=new HashSet<Espacio>();
	}
	
	public SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad(){
		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad)
									.reversed()
									.thenComparing(Comparator.naturalOrder());
		SortedSet<Espacio> res = new TreeSet<Espacio>(cmp);
		res.addAll(espacios);
		return res;
	}
	
	
	public SortedMap<String, Despacho> getDespachosPorProfesor(){
		SortedMap<String, Despacho> res = new TreeMap<>();
		for(Despacho d: getDespachos()){
			añadeProfesor(d, res);
		}
		
		return res;
		
	}
	
	private void añadeProfesor(Despacho d, SortedMap<String, Despacho> m){
		for(Profesor p:d.getProfesores()){
			m.put(p.toString(), d);
		}
		
	}
	

	
	public Integer[] getConteosEspacios(){
		Integer[] conteo={0,0,0,0,0};
		for(Espacio e:getEspacios()){
			switch(e.getTipo()){
			case TEORIA:
				conteo[0]++;
				break;
			case LABORATORIO:
				conteo[1]++;
				break;
			case SEMINARIO:
				conteo[2]++;
				break;
			case EXAMEN:
				conteo[3]++;
				break;
			case OTRO:
				conteo[4]++;
				break;
			}
		}
		return conteo;
	}
	
	public Set<Despacho> getDespachos(){
		Set<Despacho> conjunto= new HashSet<Despacho>();
		for(Espacio e:getEspacios()){
			if(e instanceof Despacho){
				conjunto.add((Despacho) e);
			}
		}
		return conjunto;
	}
	
	public Set<Despacho> getDespachos(Departamento d){
		Set<Despacho> conjunto= new HashSet<Despacho>();
		for(Despacho elem:getDespachos()){
			if(existeProfesorDepartamento(elem.getProfesores(),d)){
				conjunto.add(elem);	
			}	
		}return conjunto;
	}
	
	public Set<Profesor> getProfesores(){
		Set<Profesor> profesores= new HashSet<Profesor>();
		for(Despacho despacho:getDespachos()){
			profesores.addAll(despacho.getProfesores());
		}
		return profesores;
	}
	
	public Set<Profesor> getProfesores(Departamento d){
		Set<Profesor> profesores = new HashSet<Profesor>();
		for(Despacho elem:getDespachos()){
			for(Profesor p:elem.getProfesores()){
				if(p.getDepartamento().equals(d)){
					profesores.add(p);
				}
				
			}
		}return profesores;
	}
	
	public Espacio getEspacioMayorCapacidad(){
		Espacio espacioMayor=null;
		for(Espacio espacio:getEspacios()){
			if(espacioMayor==null || espacio.getCapacidad()>espacioMayor.getCapacidad()){
				espacioMayor=espacio;
			}
		}return espacioMayor;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getDireccion(){
		return direccion;
	}
	
	public Integer getNumeroPlantas(){
		return plantas;
	}
	
	public Integer getNumeroSotanos(){
		return sotanos;
	}
	
	public Set<Espacio> getEspacios(){
		return new HashSet<Espacio>(espacios);
	}
	
	public void nuevoEspacio(Espacio e){
		checkNuevoEspacio(e);
		espacios.add(e);
	}
	
	public void eliminaEspacio(Espacio e){
		espacios.remove(e);
	}
	
	
	//Método toString, equals, hashCode y compareTo
	public String toString(){
		return getNombre();
	}
	
	public boolean equals(Object o){
		boolean r=false;
		if(o instanceof Centro){
			Centro c=(Centro) o;
			r= getNombre().equals(c.getNombre());
		}
		return r;
	}
	
	public int hashCode(){
		return getNombre().hashCode();
	}
	
	public int compareTo(Centro c){
		int r= getNombre().compareTo(c.getNombre());
		return r;
	}
	
	
	//Métodos chekers
	private void checkPlantas(Integer plantas){
		if(plantas<1){
			throw new ExcepcionCentroNoValido("El centro debe tener al menos una planta");
		}
	}
	
	private void checkSotanos(Integer plantas){
		if(plantas<0){
			throw new ExcepcionCentroNoValido("El numero de sotanos no puede ser negativo");
		}
	}
	
	private void checkNuevoEspacio(Espacio e){
		if(e.getPlanta()<-this.getNumeroSotanos()|| e.getPlanta()>this.getNumeroPlantas()-1){
			throw new ExcepcionCentroOperacionNoPermitida("El nuevo espacio debe estar en las plantas comprendidas (-s, p-1)");
			
		}
	}
	
	//Metodo auxiliar
	
	private boolean existeProfesorDepartamento(Set<Profesor> profesores,Departamento d) {
		Boolean existe= false;
		for(Profesor p: profesores) {
			if(p.getDepartamento().equals(d)) {
			existe= true;
			break;
			}
		}return existe;
	}
	
	
}
	
