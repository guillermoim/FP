package fp.repaso.defensa;

import java.util.ArrayList;
import java.util.List;

import fp.grados.tipos.Alumno;

public class CuadranteImpl implements Cuadrante{
	private Integer semana;
	private Alumno alumno;
	private List<Actividad> actividades;
	
	public CuadranteImpl(Integer semana, Alumno alumno){
		checkSemana(semana);
		this.semana=semana;
		this.alumno=alumno;
		this.actividades= new ArrayList<Actividad>();
	}

	
	public Integer getSemana() {
		return semana;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	
	public void nuevaActividad(Actividad a) {
		if(getAlumno().estaMatriculadoEn(a.getAsignatura())){
			this.actividades.add(a);
		}else{
			throw new ExcepcionCuadranteNoValido();
		}
	}

	public void eliminaActividad(Actividad a) {
		if(actividades.contains(a)){
			actividades.remove(a);
		}
		
	}

	
	public Boolean contieneActividad(Actividad a) {
		return actividades.contains(a);
	}
	
	//Métodos toString, hashCode, equals, compareTo
	public String toString(){
		return getAlumno()+ "- Semana "+getSemana()+": "+getActividades();
	}
	
	public int hashCode(){
		return getAlumno().hashCode()+getSemana();
	}
	
	public boolean equals(Object o){
		boolean r=false;
		if(o instanceof Cuadrante){
			Cuadrante c= (Cuadrante) o;
			r=getAlumno().equals(c.getAlumno()) && getSemana().equals(c.getSemana());
		}
		return r;
	}
	
	public int compareTo(Cuadrante c){
		int r=getAlumno().compareTo(c.getAlumno());
		if(r==0){
			r=getSemana().compareTo(c.getSemana());
		}
		return r;
	}

	private void checkSemana(Integer i){
		if(i>15){
			throw new ExcepcionCuadranteNoValido();
		}
	}

}
