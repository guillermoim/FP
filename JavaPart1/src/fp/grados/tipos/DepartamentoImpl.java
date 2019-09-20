package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class DepartamentoImpl implements Departamento {
	private String nombre;
	private Set<Profesor> profesores;
	private Set<Asignatura> asignaturas;
	
	public DepartamentoImpl(String nombre){
		this.nombre=nombre;
		this.profesores= new HashSet<Profesor>();
		this.asignaturas= new HashSet<Asignatura>();
	}
	
	//T12
	
	public Profesor  getProfesorMaximaDedicacionMediaPorAsignatura(){
		Optional<Profesor> res = getProfesores().stream()
				.filter(prof-> !prof.getAsignaturas().isEmpty())
				.max(Comparator.comparing(x -> x.getDedicacionTotal()/x.getAsignaturas().size()));
		
		return res.get();
	}
	
	
	//Boletin t9
	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura(){
		SortedMap<Asignatura, SortedSet<Profesor>> res = new TreeMap<>();
		for(Profesor p: getProfesores()){
			añadeProfesor(p, res);
		}
		
		return res;
	}
	
	private void añadeProfesor(Profesor p, SortedMap<Asignatura, SortedSet<Profesor>> m){
		for(Asignatura a: p.getAsignaturas()){
			
			if(m.containsKey(a)){
				
				m.get(a).add(p);
		
			}else{
				
				SortedSet<Profesor> conjunto = new TreeSet<Profesor>();
				conjunto.add(p);
				m.put(a, conjunto);
				
			}
		}
	}
	
	public SortedMap<String, SortedSet<Tutoria>> getTutoriasPorProfesor(){
		SortedMap<String, SortedSet<Tutoria>> res = new TreeMap<>();
		for(Profesor p:getProfesores()){
			res.put(p.toString(), p.getTutorias());
		}
		return res;
	}
	
	
	public void borraTutorias(){
		for(Profesor p:getProfesores()){
			p.borraTutorias();
		}
	}
	
	public void borraTutorias(Categoria c){
		for(Profesor p:getProfesores()){
			if(p.getCategoria().equals(c)){
				p.borraTutorias();
			}
		}
	}
	
	public Boolean existeProfesorAsignado(Asignatura a){
		Boolean r=false;
		for(Profesor p:getProfesores()){
			if(p.getAsignaturas().contains(a)){
			r=true;
			break;
			}
		}
		return r;
	}
	
	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = true;
		for(Asignatura a : getAsignaturas()) {
			if(!existeProfesorAsignado(a)) {
				res = false;
				break;
			}
		}
		return res;
	}
	
	public void eliminaAsignacionProfesorado(Asignatura a){
		for(Profesor p:getProfesores()){
			if(p.getAsignaturas().contains(a)){
				p.eliminaAsignatura(a);
			}
		}
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Set<Profesor> getProfesores(){
		return new HashSet<Profesor>(profesores);
	}
	
	public Set<Asignatura> getAsignaturas(){
		return new HashSet<Asignatura>(asignaturas);
	}
	
	public void nuevaAsignatura(Asignatura a){
		asignaturas.add(a);
		a.setDepartamento(this);
	}
	
	public void eliminaAsignatura(Asignatura a){
		if(asignaturas.contains(a)){
			asignaturas.remove(a);
			a.setDepartamento(null);
		}
		
	}
	
	public void nuevoProfesor(Profesor p){
		profesores.add(p);
		p.setDepartamento(this);
	}
	
	public void eliminaProfesor(Profesor p){
		if(profesores.contains(p)){
			profesores.remove(p);
			p.setDepartamento(null);
		}
	}
	//Método toString, equals, hashCode y compareTo
	
	public String toString(){
		return getNombre();
	}
	
	public boolean equals(Object o){
		boolean r= false;
		if(o instanceof Departamento){
			Departamento d=(Departamento) o;
			r=getNombre().equals(d.getNombre());
		}
		return r;
	}
	
	public int hashCode(){
		return getNombre().hashCode();
	}
	
	public int compareTo(Departamento d){
		int r=getNombre().compareTo(d.getNombre());
		return r;
	}
}
