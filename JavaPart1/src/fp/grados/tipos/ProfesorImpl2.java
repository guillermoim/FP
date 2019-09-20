package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;
import fp.grados.tipos.Categoria;

public class ProfesorImpl2 extends PersonaImpl implements Profesor{
	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private Departamento departamento;
	private Map<Asignatura, Double> asignaturasCreditos;
	private static final Double CREDITOS_MAX_DEFAULT = 24.;
	private static final Double CREDITOS_AYUDANTES=6.;
	private static final Double CREDITOS_DEMAS=32.;
	
	
	public ProfesorImpl2(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
	String email, Categoria categoria){		
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdad(fechaNacimiento);
		this.categoria=categoria;
		this.tutorias= new TreeSet<Tutoria>();
		this.departamento=null;
		this.asignaturasCreditos = new HashMap<>();
	}
	
	public ProfesorImpl2(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
		String email, Categoria categoria, Departamento d){		
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdad(fechaNacimiento);
		this.categoria=categoria;
		this.tutorias= new TreeSet<Tutoria>();
		this.asignaturasCreditos = new HashMap<>();
		setDepartamento(d);
	}
	
	private void checkEdad(LocalDate date) {
		Integer edad = (int) date.until(LocalDate.now(), ChronoUnit.YEARS);
		if(edad < 18) {
			throw new ExcepcionProfesorNoValido("El profesor tiene que ser mayor de edad.");
		}
	}
	
		
	public Categoria getCategoria() {
		return categoria;
	}

	
	public SortedSet<Tutoria> getTutorias() {
		return new TreeSet<Tutoria>(this.tutorias);
	}

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos,
			DayOfWeek dia) {
		Tutoria t = new TutoriaImpl(dia, horaComienzo, duracionMinutos);
		this.tutorias.add(t);
	}

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		for(Tutoria t : getTutorias()) {
			if(t.getHoraComienzo().equals(horaComienzo) && t.getDiaSemana().equals(dia)) {
				this.tutorias.remove(t);
			}
		}
	}

	
	public void borraTutorias() {
		this.tutorias.clear();
	}
	
	public String toString() {
		return super.toString() + " (" + getCategoria() + ")"; 
	}

	
	public List<Asignatura> getAsignaturas() {
		return new ArrayList<>(this.asignaturasCreditos.keySet());
	}

	
	public List<Double> getCreditos() {
		return new ArrayList<Double>(this.asignaturasCreditos.values());
	}

	
	public Double getDedicacionTotal() {
		Double creditos = 0.;
		for(Asignatura a : getAsignaturas()) {
			creditos = creditos + this.dedicacionAsignatura(a);
		}
		if(creditos > CREDITOS_MAX_DEFAULT) {
			throw new ExcepcionProfesorOperacionNoPermitida("Los creditos son superior a " + CREDITOS_MAX_DEFAULT);
		}
		return creditos;
	}

	
	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		checkCreditosAsignatura(asig, dedicacion);
		checkAsignaturaDepartamento(asig);
		
		if (asignaturasCreditos.containsKey(asig)) {
				actualizaDedicacion(asig, dedicacion);
			}else{
				nuevaAsignatura(asig, dedicacion);
			}
		checkCreditos();
	}
	
	private void actualizaDedicacion(Asignatura asig, Double dedicacion) {
		asignaturasCreditos.put(asig, dedicacion);
		} 
	
	private void nuevaAsignatura(Asignatura asig, Double dedicacion) {
		asignaturasCreditos.put(asig, dedicacion);
		}
	
	public Double dedicacionAsignatura(Asignatura asig) {
		Double res = 0.0;
		if (asignaturasCreditos.containsKey(asig)) {
			res = asignaturasCreditos.get(asig);
		}
		return res;
	}

	
	public void eliminaAsignatura(Asignatura asig) {
		asignaturasCreditos.remove(asig);
	}

	
	public Departamento getDepartamento() {
		return departamento;
	}
	
	
	public void setFechaNacimiento(LocalDate date) {
		checkEdad(date);
		super.setFechaNacimiento(date);
	}
	
	
	public void setCategoria(Categoria c) {
		checkCategoria(c);
		this.categoria = c;		
	}

	
	public void setDepartamento(Departamento d) {
		if(this.getDepartamento() != d) {			
			if(this.getDepartamento() != null) {
				this.getDepartamento().eliminaProfesor(this);
			}
			this.departamento = d;
			if(d != null) {
				d.nuevoProfesor(this);
			}
		}
	}
	private void checkAsignaturaDepartamento(Asignatura asig) {
		if (getDepartamento() == null ||
				!getDepartamento().getAsignaturas().contains(asig)) {
				throw new ExcepcionProfesorOperacionNoPermitida(
				"Un profesor no puede impartir una asignatura "
				+ "de otro departamento.");
				}
	}
	
	private void checkCreditosAsignatura(Asignatura asig, Double dedicacion) {
		if (dedicacion <= 0 || asig.getCreditos() < dedicacion) {
			throw new ExcepcionProfesorOperacionNoPermitida(
			"La dedicación debe ser mayor que 0 y menor o igual "
			+ "que el número de créditos de la asignatura.");
			}
	}
	
	private void checkCreditos() {
		if(getCategoria().equals(Categoria.AYUDANTE) && getDedicacionTotal()>CREDITOS_AYUDANTES){
			throw new ExcepcionProfesorOperacionNoPermitida(
					"La dedicación debe ser mayor que 0 y menor o igual "
							+ "que el número de créditos de la asignatura.");
			
		}else{
			if(getDedicacionTotal()>CREDITOS_DEMAS){
				throw new ExcepcionProfesorOperacionNoPermitida(
						"La dedicación debe ser mayor que 0 y menor o igual "
								+ "que el número de créditos de la asignatura.");
			}
		}
		
	}
	
	private void checkCategoria(Categoria categoria){
		if(categoria.equals(Categoria.AYUDANTE)){
			if(getDedicacionTotal()>CREDITOS_AYUDANTES){
				throw new ExcepcionProfesorOperacionNoPermitida("");
			}
		}else{
			if(getDedicacionTotal()>CREDITOS_DEMAS){
				throw new ExcepcionProfesorOperacionNoPermitida("");
			}
		}
		
	}
	
	
}
