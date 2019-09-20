package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;

public class TutoriaImpl implements Tutoria {
	//Atributos
	private DayOfWeek diaSemana;
	private LocalTime horaInicio, horaFin;
	
	
	//Constructores
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFin){
		checkDia(diaSemana);
		checkHoraInicio(horaInicio);
		checkHoraFin(horaFin);
		checkDuracion((int) horaInicio.until(horaFin, ChronoUnit.MINUTES));
		
		this.diaSemana=diaSemana;
		this.horaInicio=horaInicio;
		this.horaFin=horaFin;
		
	}
	
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaInicio, Integer duracion){
		checkDia(diaSemana);
		checkHoraInicio(horaInicio);
		checkDuracion(duracion);
		
		this.diaSemana=diaSemana;
		this.horaInicio=horaInicio;
		horaFin=getHoraComienzo().plusMinutes(duracion);
		
	}
	
	public TutoriaImpl(String s){
		String[] aux = s.split(",");
		if(aux.length!=3){
			throw new IllegalArgumentException(
					"El formato de la cadena de entrada no es correcto.");
					}
		DayOfWeek dia = DayOfWeek.valueOf(getDia(aux[0].trim()));
		LocalTime inicio = LocalTime.parse(aux[1].trim(), DateTimeFormatter.ofPattern("H:m"));
		LocalTime fin = LocalTime.parse(aux[2].trim(), DateTimeFormatter.ofPattern("H:m"));
		Integer duracion = (int) inicio.until(fin, ChronoUnit.MINUTES);
		
		checkDia(dia);
		checkHoraInicio(inicio);
		checkDuracion(duracion);
		
		this.diaSemana = dia;
		this.horaInicio = inicio;
		this.horaFin = fin;
		
		checkHoraFin(getHoraFin());
		
	}
	
	//Métodos de la clase tutoria
	public DayOfWeek getDiaSemana(){
		return diaSemana;	
	}
	
	public LocalTime getHoraComienzo(){
		return horaInicio;	
	}
	
	public LocalTime getHoraFin(){
		return horaFin;
	}
	
	public Integer getDuracion(){
		return (int) horaInicio.until(horaFin, ChronoUnit.MINUTES);
	}
	
	//Metodos toString, equals, hashCode, y compareTo
	public String toString(){
		return getPrimeraLetra(getDiaSemana())+" "+ getHoraComienzo()+"-"+getHoraFin();
	}
	
	public boolean equals(Object o){
		boolean r=false;
		if(o instanceof Tutoria){
			Tutoria t= (Tutoria) o;
			r=getDiaSemana().equals(t.getDiaSemana())&& getHoraComienzo().equals(t.getHoraComienzo());
		}
		return r;
	}
	
	public int hashCode(){
		return getDiaSemana().hashCode()+ 31* getHoraComienzo().hashCode();
		
	}
	
	public int compareTo(Tutoria t){
		int r= getDiaSemana().compareTo(t.getDiaSemana());
		if(r==0){
			r=getHoraComienzo().compareTo(t.getHoraComienzo());
		}
		return r;
	}
	
	//Métodos checkers
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
	
	
	private void checkDia(DayOfWeek diaSemana){
		if(diaSemana==DayOfWeek.SATURDAY ||diaSemana==DayOfWeek.SUNDAY){
			throw new ExcepcionTutoriaNoValida("La tutoría debe ser entre Lunes y Viernes");
		}
	}
	private void checkHoraInicio(LocalTime horaInicio){
		if(horaInicio.isBefore(LocalTime.of(8, 30))|| horaInicio.isAfter(LocalTime.of(21,00 ))){
			throw new ExcepcionTutoriaNoValida("La tutoría no puede empezar antes de las 8:30 ni después de las 21:00");
		}
	}
	private void checkHoraFin(LocalTime horaFin){
		if(horaFin.isBefore(LocalTime.of(9, 0))||horaFin.isAfter(LocalTime.of(21, 30))){
			throw new ExcepcionTutoriaNoValida("La tutoría no puede terminar antes de las 9:00 ni después de las 21:00");
		}
	}
	
	private void checkDuracion(Integer duracion){
		if(duracion<30){
			throw new ExcepcionTutoriaNoValida("La duración debe ser al menos de 30 minutos.");
		}
	}

	private String getDia(String s){
		String res= s;
		switch(s){
		case "L": res="MONDAY"; break;
		case "M": res="TUESDAY"; break;
		case "X": res="WEDNESDAY"; break;
		case "J": res="THURSDAY"; break;
		case "V": res="FRIDAY"; break;
		case "S": res="SATURDAY"; break;
		case "D": res="SUNDAY";break;
		default: throw new ExcepcionTutoriaNoValida();
			
		}
		
		return res;
	}
}
