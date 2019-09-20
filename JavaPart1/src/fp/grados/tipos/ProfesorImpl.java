package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;
import fp.grados.tipos.Categoria;


public class ProfesorImpl extends PersonaImpl implements Profesor{
	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private Departamento departamento;
	private List<Asignatura> asignaturas;
	private List<Double> creditos;
	private static final Double CREDITOS_MAX = 24.;
	private static final Double CREDITOS_AYUDANTES=6.;
	private static final Double CREDITOS_DEMAS=32.;
	
	
	public ProfesorImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
	String email, Categoria categoria){		
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdad(fechaNacimiento);
		this.categoria=categoria;
		this.tutorias= new TreeSet<Tutoria>();
		this.departamento=null;
		this.asignaturas= new ArrayList<Asignatura>();
		this.creditos= new ArrayList<Double>();
	}
	
	public ProfesorImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
		String email, Categoria categoria, Departamento d){		
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdad(fechaNacimiento);
		this.categoria=categoria;
		this.tutorias= new TreeSet<Tutoria>();
		this.asignaturas= new ArrayList<Asignatura>();
		this.creditos= new ArrayList<Double>();
		setDepartamento(d);
	}
	
	private void checkEdad(LocalDate date) {
		Integer edad = (int) date.until(LocalDate.now(), ChronoUnit.YEARS);
		if(edad < 18) {
			throw new ExcepcionProfesorNoValido("El profesor tiene que ser mayor de edad.");
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
		return new ArrayList<Asignatura>(this.asignaturas);
	}

	
	public List<Double> getCreditos() {
		return new ArrayList<Double>(this.creditos);
	}

	
	public Double getDedicacionTotal() {
		Double creditos = 0.;
		for(Asignatura a : getAsignaturas()) {
			creditos = creditos + this.dedicacionAsignatura(a);
		}
		if(creditos > CREDITOS_MAX) {
			throw new ExcepcionProfesorOperacionNoPermitida("Los creditos son superior a " + CREDITOS_MAX);
		}
		return creditos;
	}

	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		checkAsignaturaDepartamento(asig);
		checkCreditosAsignatura(asig, dedicacion);
		
		if(getAsignaturas().contains(asig)) {
			this.creditos.set(asignaturas.indexOf(asig), dedicacion);
		} else {
			this.asignaturas.add(asig);
			this.creditos.add(dedicacion);
		}
		checkCreditos();
	}

	public Double dedicacionAsignatura(Asignatura asig) {
		if(getAsignaturas().contains(asig)) {
			return creditos.get(asignaturas.indexOf(asig));
		} else {
			return 0.0;
		}
	}

	public void eliminaAsignatura(Asignatura asig) {
		if(getAsignaturas().contains(asig)) {
			this.creditos.remove(asignaturas.indexOf(asig));
			this.asignaturas.remove(asig);
		}
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
