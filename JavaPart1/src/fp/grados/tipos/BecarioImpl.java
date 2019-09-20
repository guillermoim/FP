package fp.grados.tipos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionBecarioNoValido;

public class BecarioImpl extends AlumnoImpl implements Becario{
	private Beca beca;
	private LocalDate fechaComienzo;
	
	public BecarioImpl(String dni, String nombre, String apellidos, 
			LocalDate fecha,String email, Beca beca, LocalDate fechaComienzo){
		super(dni, nombre, apellidos, fecha, email);
		this.beca=beca;
		checkFechaComienzo(fechaComienzo);
		this.fechaComienzo=fechaComienzo;
	}
	
	public BecarioImpl(String dni, String nombre, String apellidos, 
			LocalDate fecha,String email,String codigo, Double cuantia, Integer duracion, TipoBeca tipo,
			LocalDate fechaComienzo){
		super(dni, nombre, apellidos, fecha, email);
		checkFechaComienzo(fechaComienzo);
		this.beca= new BecaImpl(codigo, cuantia, duracion, tipo);
		this.fechaComienzo=fechaComienzo;
	}
	
	public Beca getBeca(){
		return beca;
	}
	
	public LocalDate getFechaComienzo(){
		return fechaComienzo;
	}
	
	public void setFechaComienzo(LocalDate fecha){
		checkFechaComienzo(fecha);
		this.fechaComienzo=fecha;
	}
	
	public LocalDate getFechaFin(){
		return getFechaComienzo().plus(getBeca().getDuracion(), ChronoUnit.MONTHS);
	}

	//Método setEmail redefinido
	
	public void setEmail(String email){
		throw new UnsupportedOperationException();
	}
	
	//Método toString redefinido
	
	public String toString(){
		return super.toString() + " " + getBeca().toString();
	}
	
	//Métodos checkers
	
	private void checkFechaComienzo(LocalDate fecha){
		if(!fecha.isAfter(LocalDate.now())){
			throw new ExcepcionBecarioNoValido("");
		}
	}
	
	
	
}
