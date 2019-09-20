package fp.repaso.defensa;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.Espacio;

public class ActividadImpl implements Actividad {
	
	private String codigo;
	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private Espacio espacio;
	private Integer duracion;
	private TipoActividad tipo;
	private Asignatura asignatura;
	
	public ActividadImpl(String codigo, DayOfWeek dia, LocalTime horaC,  Espacio e, Integer d,
			TipoActividad tipo, Asignatura asig){
		checkDuracion( d,  tipo);
		checkEspacio(e,  tipo);
		checkDia(dia);
		this.codigo=codigo;
		this.diaSemana=dia;
		this.horaComienzo=horaC;
		this.espacio=e;
		this.duracion=d;
		this.tipo=tipo;
		this.asignatura=asig;
	}

	
	public String getCodigo() {
		return this.codigo;
	}

	
	public DayOfWeek getDiaSemana() {
		return this.diaSemana;
	}

	
	public LocalTime getHoraComienzo() {
		return this.horaComienzo;
	}

	
	public LocalTime getHoraFinal() {
		return getHoraComienzo().plusMinutes(getDuracion());
	}

	
	public Espacio getEspacio() {
		
		return this.espacio;
	}
	
	public void setEspacio(Espacio e){
		this.espacio=e;
	}
	
	public Integer getDuracion() {
		
		return this.duracion;
	}

	
	public TipoActividad getTipoActividad() {
		
		return this.tipo;
	}

	
	public Asignatura getAsignatura() {
		return this.asignatura;
	}
	
	//Método toString, hashCode, equals, compareTo
	
	public String toString(){
		return getCodigo()+": "+ getPrimeraLetra(getDiaSemana())+getHoraComienzo()+
				"-"+getHoraFinal()+", "+getEspacio()+getAsignatura()+"("+getTipoActividad()+")";
	}
	
	public Integer hashCoe(){
		return getCodigo().hashCode();
	}
	
	public boolean equals(Object o){
		boolean r=false;
		if(o instanceof Actividad){
			Actividad a= (Actividad ) o;
			r= getCodigo().equals(a.getCodigo());
		}
		return r;
	}
	
	public int compareTo(Actividad a){
		return getCodigo().compareTo(a.getCodigo());
	}
	
	//
	public void checkDuracion(Integer duracion, TipoActividad tipo){
		if(duracion>120 && !tipo.equals(TipoActividad.EXAMEN)){
			throw new ExcepcionActividadNoValida();
		}
		if(duracion>180 && tipo.equals(TipoActividad.EXAMEN)){
			throw new ExcepcionActividadNoValida();
		}
	}
	
	public void checkDia(DayOfWeek dia){
		if(dia.equals(DayOfWeek.SATURDAY) ||dia.equals(DayOfWeek.SUNDAY)){
			throw new ExcepcionActividadNoValida();
		}
		
	}
	
	public void checkEspacio(Espacio e, TipoActividad tipo){
		if(!e.getTipo().toString().matches(tipo.toString())){
			throw new ExcepcionActividadNoValida();
		}
	}
	
	private String getPrimeraLetra(DayOfWeek dia){
		DayOfWeek x=getDiaSemana();
		String c;
		switch(x){
		case MONDAY:
			c="L";
			break;
		case TUESDAY:
			c="M";
			break;
		case WEDNESDAY:
			c="X";
			break;
		case THURSDAY:
			c="J";
			break;
		case FRIDAY:
			c="V";
			break;
		default:
			c="";
			break;
		}
		return c;
	}
	
	
}
