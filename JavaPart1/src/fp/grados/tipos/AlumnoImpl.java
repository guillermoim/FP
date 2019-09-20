package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.tipos.Expediente;
import fp.grados.tipos.ExpedienteImpl;

public class AlumnoImpl extends PersonaImpl implements Alumno{
	private Set<Asignatura> asignaturas;
	private Expediente expediente;
	
	public AlumnoImpl(String dni, String nombre, String apellidos,LocalDate fecha, String email){
		super(dni,nombre,apellidos, fecha, email);
		checkEmailUniversidad(email);
		this.asignaturas= new HashSet<Asignatura>();
		this.expediente= new ExpedienteImpl();
	}
	
	public AlumnoImpl(String s){
		super(s);
		checkEmailUniversidad(super.getEmail());
		this.asignaturas= new HashSet<Asignatura>();
		this.expediente= new ExpedienteImpl();
		
	}
	
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura(){
		SortedMap<Asignatura, Calificacion> res =  new TreeMap<>();
		for(Nota n : getExpediente().getNotas()){
			if(res.containsKey(n.getAsignatura())){
				if(res.get(n.getAsignatura()).compareTo(n.getCalificacion())<0){
					res.put(n.getAsignatura(), n.getCalificacion());
				}
			}else{
				res.put(n.getAsignatura(), n.getCalificacion());
			}
		}
		return res;
	}
	
	//Boletin T12
	
	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso(){
		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso)
				.reversed()
				.thenComparing(Comparator.naturalOrder());
		SortedSet<Asignatura> res = new TreeSet<Asignatura>(cmp);
		res.addAll(asignaturas);
		return res; 
	}
	
	public Integer getCurso(){
		int r=0;
		for(Asignatura elem:getAsignaturas()){
			if(elem.getCurso()>r){
				r=elem.getCurso();
			}
		} 
		return r;
	}

	public Set<Asignatura> getAsignaturas(){
		return new HashSet<Asignatura>(asignaturas);
	}
	
	public void matriculaAsignatura(Asignatura asig){
		if(estaMatriculadoEn(asig)){
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno ya está matriculado en esta asignatura.");
		}
		asignaturas.add(asig);
	}
	
	public void eliminaAsignatura(Asignatura asig){
		if(!estaMatriculadoEn(asig)){
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado en esta asignatura.");
		}
		asignaturas.remove(asig);
	}
	
	public Boolean estaMatriculadoEn(Asignatura asig){
		return asignaturas.contains(asig);
	}
	
	public Expediente getExpediente(){
		return expediente;
	}
	
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria,
			Double nota, Boolean mencionHonor){
		checkAsignatura(a);
		Nota n= new NotaImpl(a, curso, convocatoria, nota, mencionHonor);
		expediente.nuevaNota(n);
	}
	
	public void evaluaAlumno(Asignatura a, Integer curso, 
			Convocatoria convocatoria, Double nota){
		checkAsignatura(a);
		Nota n= new NotaImpl(a, curso, convocatoria, nota );
		expediente.nuevaNota(n);
	}
	
	//Método setEmail redefinido
	
	public void setEmail(String email){
		checkEmailUniversidad(email);
		super.setEmail(email);
	}
	//Método toString redefinido
	
	public String toString(){
		return "("+getCurso()+"º) " + super.toString();
	}
	
	//Método checkers
	
	private void checkEmailUniversidad(String email){
		if((!email.endsWith("@alum.us.es"))||(email=="")){
			throw new ExcepcionAlumnoNoValido("El email debe terminar en @alum.us.es");
		}
	}
	
	private void checkAsignatura(Asignatura a){
		if(!estaMatriculadoEn(a)){
			throw new ExcepcionAlumnoOperacionNoPermitida("El email debe terminar en @alum.us.es");
		}
	}
	
	
}
