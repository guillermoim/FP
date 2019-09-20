package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;

public class NotaImpl implements Nota{
	private Asignatura asignatura;
	private Integer cursoAcademico;
	private Convocatoria convocatoria;
	private Double valor;
	private Boolean mencionHonor;
	
	public NotaImpl(Asignatura asignatura, Integer curso,Convocatoria convocatoria, Double valor, Boolean mencionHonor){
		checkValor(valor);
		checkMencionHonor(valor,mencionHonor);
		this.asignatura=asignatura;
		this.cursoAcademico= curso;
		this.convocatoria=convocatoria;
		this.valor=valor;
		this.mencionHonor=mencionHonor;
	}

	public NotaImpl(Asignatura asignatura, Integer curso,Convocatoria convocatoria, Double valor){
		checkValor(valor);
		this.asignatura=asignatura;
		this.cursoAcademico= curso;
		this.convocatoria=convocatoria;
		this.valor=valor;
		mencionHonor=false;
	}
	
	//Fundamentos de Programacion#1234567#12.0#ANUAL#1,2014,PRIMERA,10.0,true
	public NotaImpl(String s){
		String [] aux= s.split(";");
		if(aux.length!=5){
			throw new IllegalArgumentException();
		}
		Double val=new Double(aux[3].trim());
		
		checkValor(val);
		checkMencionHonor(val,new Boolean(aux[4].trim()));
		this.asignatura=new AsignaturaImpl(aux[0].trim());
		this.cursoAcademico= new Integer(aux[1].trim());
		this.convocatoria=Convocatoria.valueOf(aux[2].trim());
		this.valor=val;
		this.mencionHonor=new Boolean(aux[4].trim());
		
	}
	
	private void checkValor(Double valor){
		if (valor<0 || valor>10){
			throw new ExcepcionNotaNoValida("The grade must be between 0 and 10");
		}
	}
	
	private void checkMencionHonor(Double valor,Boolean mencionHonor){
		if(valor<9 && mencionHonor==true){
			throw new ExcepcionNotaNoValida("The grade cannot have an honor mention if the value is below 9");
		}
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public Integer getCursoAcademico() {
		return cursoAcademico;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public Double getValor() {
		return valor;
	}

	public Boolean getMencionHonor() {
		return mencionHonor;
	}

	public Calificacion getCalificacion() {
		Calificacion t= Calificacion.SUSPENSO;
		if(getValor()>=5 && getValor()<7){ t= Calificacion.APROBADO;};
		if(getValor()>=7 && getValor()<9){ t= Calificacion.NOTABLE;};
		if(getValor()>=9 && getMencionHonor()==false){ t= Calificacion.SOBRESALIENTE;};
		if(getValor()>=9 && getMencionHonor()==true){ t= Calificacion.MATRICULA_DE_HONOR;};
		return t;
	}
	
	
	public String toString(){
		return getAsignatura()+", "+getCursoAcademico()+"-"+Integer.toString((getCursoAcademico()+1)).substring(2, 4) +", "+getConvocatoria()+", "+getValor()+", "+getCalificacion();
				
	}
	
	//equals
	public boolean equals(Object o){
		boolean res=false;
		if(o instanceof Nota){
			Nota n = (Nota)o;
			res = this.getCursoAcademico().equals(n.getCursoAcademico()) && this.getAsignatura().equals(n.getAsignatura()) && this.getConvocatoria().equals(n.getConvocatoria());
		}
	return res;
	}
	
	//hashcode
	public int hashCode(){
		return this.getCursoAcademico().hashCode() + (this.getAsignatura().hashCode()*31 + this.getConvocatoria().hashCode())*31;
	}
	
	//compareTo
	public int compareTo(Nota n){
		int res= this.getCursoAcademico().compareTo(n.getCursoAcademico());
		if(res==0){
			res= this.getAsignatura().compareTo(n.getAsignatura());
			if(res==0){
				res= this.getConvocatoria().compareTo(n.getConvocatoria());
			}
		}
		return res;
	}

}
