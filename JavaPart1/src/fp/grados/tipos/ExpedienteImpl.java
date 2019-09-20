package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import java.util.Optional;


import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;

public class ExpedienteImpl implements Expediente{
private List<Nota> notas;
	
	public ExpedienteImpl(){
		notas= new ArrayList<Nota>();
	}
	
	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}

	
	
	private void checkNotasMasDeDosConvocatorias(List<Nota> notas,Nota n){
		Integer cont=0;
		for(Nota e : notas){
			if(e.getCursoAcademico().equals(n.getCursoAcademico()) && e.getAsignatura().equals(n.getAsignatura()) 
					&& !e.getConvocatoria().equals(n.getConvocatoria())){
				cont++;	
			}
		}
		if(cont>=2){
					throw new ExcepcionExpedienteOperacionNoPermitida("Un expediente no puede contener notas para más de dos convocatorias de la misma asignatura y curso");
				}
		
	}

	public void nuevaNota(Nota n){
		checkNotasMasDeDosConvocatorias(notas,n);
		if(notas.contains(n)){
			notas.remove(n);
			notas.add(n);
		}
		else{
			notas.add(n);
		}
	}
	
	
	public Double getNotaMedia(){
		Double res = 0.0;
		Integer cont=0;
		for(Nota n: getNotas()){
			if(n.getValor() >= 5){
				res= res+ n.getValor();
				cont++;
			}
		}
		 if(cont.equals(0)){
			 return 0.0;
		 }
		 
		 else{
			return res/cont;
		}
	}
	
	
	
	public List<Nota> getNotasOrdenadasPorAsignatura(){
		Comparator<Nota> cmp= Comparator.comparing(x-> x.getAsignatura());
		Comparator<Nota> cmp2= cmp.thenComparing(Comparator.naturalOrder());
		List<Nota> res= new ArrayList<Nota>();
		res.addAll(notas);
		Collections.sort(res, cmp2);
		return res;
		
	}
	
	//TODO revisar getMejorNota Comparator
	public Nota getMejorNota(){
		Comparator<Nota> cmp= Comparator.comparing(x->x.getMencionHonor());
		Comparator<Nota> cmp2= cmp.thenComparing(x->x.getValor());
		Comparator<Nota> cmp23= (x,y)-> x.getConvocatoria().compareTo(y.getConvocatoria());
		Comparator<Nota> cmp3= cmp2.thenComparing(cmp23.reversed());
		Comparator<Nota> cmp34= (x,y)-> x.getCursoAcademico().compareTo(y.getCursoAcademico());
		Comparator<Nota> cmp4= cmp3.thenComparing(cmp34.reversed());
		
		
		Optional<Nota> opt= notas.stream().max(cmp4);
		return opt.get();
		
	}
	
	public boolean equals(Object o){
		boolean res=false;
		if(o instanceof Expediente){
			Expediente e= (Expediente) o;
			res= this.getNotas().equals(e.getNotas());		
		}
	return res;
	}
	
	public int hashCode(){
		return this.getNotas().hashCode();
	}
	
	
	public String toString(){
		return getNotas().toString();
	}
		
		
}
