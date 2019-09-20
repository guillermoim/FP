package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado{
	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double numeroMinimoCreditosOptativas;
	
	
	public GradoImpl(String nombre,Set<Asignatura> asignaturasObligatorias,Set<Asignatura> asignaturasOptativas,Double numeroMinimoCreditosOptativas){
		checkNumeroMinimoCreditosOptativas(asignaturasOptativas,numeroMinimoCreditosOptativas);
		checkMismoNumeroCreditosOptativas(asignaturasOptativas);
		checkTodasOptativasCuatrimestrales(asignaturasOptativas);
		this.nombre=nombre;
		this.asignaturasObligatorias=new HashSet<>(asignaturasObligatorias);
		this.asignaturasOptativas=new HashSet<>(asignaturasOptativas);
		this.numeroMinimoCreditosOptativas=numeroMinimoCreditosOptativas;
	}
	
	
	
	private void checkMismoNumeroCreditosOptativas(Set<Asignatura> asignaturasOptativas){
		Double aux=0.0;
		for(Asignatura a: asignaturasOptativas){
			aux = a.getCreditos();
			break;	
		}
		
		for(Asignatura a: asignaturasOptativas){
		if(!aux.equals(a.getCreditos())){
			
			throw new ExcepcionGradoNoValido("El numero de creditos de todas las asignaturas optativas debe ser el mismo");
		}
		
		}	
	}
	
	private void checkNumeroMinimoCreditosOptativas(Set<Asignatura> asignaturasOptativas,Double numeroMinimoCreditosOptativas){
		Double res=0.0;
		for(Asignatura a: asignaturasOptativas){
			res = res + a.getCreditos();
		}
		if(numeroMinimoCreditosOptativas<0 || numeroMinimoCreditosOptativas>res){
		throw new ExcepcionGradoNoValido("El numero de creditos de las asignaturas optativas debe estar entre 0 y los creditos de las asignaturas optativas");
		}
	}
	
	private void checkTodasOptativasCuatrimestrales(Set<Asignatura> asignaturas){
		for(Asignatura a:asignaturas){
			if(a.getTipo().equals(TipoAsignatura.ANUAL)){
				throw new ExcepcionGradoNoValido();
			}
		}
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public Set<Asignatura> getAsignaturasObligatorias() {
		return new HashSet<Asignatura>(asignaturasObligatorias);
	}
	public Set<Asignatura> getAsignaturasOptativas() {
		return new HashSet<Asignatura>(asignaturasOptativas);
	}
	public Double getNumeroMinimoCreditosOptativas() {
		return numeroMinimoCreditosOptativas;
	}
	

	public Double getNumeroTotalCreditos(){
		Double d = 0.0;
		for(Asignatura a: getAsignaturasObligatorias()){
			d = d+ a.getCreditos();
		}
		Double res =  d + getNumeroMinimoCreditosOptativas();
		return res;
	}
	
	public Set<Departamento> getDepartamentos(){
		Set<Departamento> s = new HashSet<Departamento>();
		
		for(Asignatura a : getAsignaturasObligatorias()){
			s.add(a.getDepartamento());
		}
		for(Asignatura a : getAsignaturasOptativas()){
			s.add(a.getDepartamento());
		}
	 return s;
	}
	
	

	public Set<Profesor> getProfesores(){
		Set<Profesor> s = new HashSet<Profesor>();
		for(Departamento d : getDepartamentos()){
			s.addAll(d.getProfesores());
		}
		return s;
	}
				
	
	
	public Set<Asignatura> getAsignaturas(Integer curso){
		Set<Asignatura> s = new HashSet<Asignatura>();
		for (Asignatura a : getAsignaturasObligatorias()){
			if(a.getCurso().equals(curso)){
				s.add(a);
			}
		}
		
		for (Asignatura a : getAsignaturasOptativas()){
			if(a.getCurso().equals(curso)){
				s.add(a);
			}
		}
		return s;
	}
	
	

	public Asignatura getAsignatura(String codigo){
		Asignatura res =null;
		for(Asignatura a: getAsignaturasObligatorias()){
			if(a.getCodigo().equals(codigo)){
				res= a;
				break;
			}
		}
		
		for(Asignatura a: getAsignaturasOptativas()){
			if(a.getCodigo()==codigo){
				res= a;
				break;
			}
		}
		
		return res;
	}
	
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura(){
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		for(Asignatura a: getAsignaturasObligatorias()){
			res.put(a, a.getCreditos());
		}
		for(Asignatura a: getAsignaturasOptativas()){
			res.put(a, a.getCreditos());
		}
		return res;
	}
	
	
	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas(){
		Comparator<Departamento> cmp= Comparator.comparing(x->x.getAsignaturas().size());
		Comparator<Departamento> cmp2= cmp.reversed().thenComparing(Comparator.naturalOrder());
		SortedSet<Departamento> res= new TreeSet<Departamento>(cmp2);
		res.addAll(this.getDepartamentos());
		return res;
		
		
	}
				
	
	
	public boolean equals(Object o){
		boolean res = false;
		if(o instanceof Grado){
			Grado g = (Grado) o;
			res= getNombre().equals(g.getNombre());
		}
		return res;
	}
	
	public int hashCode(){
		return getNombre().hashCode();
	}
	
	public int compareTo(Grado g){
		int res= getNombre().compareTo(g.getNombre());
		return res;
	}
	
	public String toString(){
		return getNombre();
	}
}
