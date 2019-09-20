package fp.grados.tipos;


import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl2 extends CentroImpl implements Centro{
	
	public CentroImpl2(String nombre,String direccion, Integer plantas,Integer sotanos){
		super(nombre,direccion,plantas,sotanos);
	}
	
	public Espacio getEspacioMayorCapacidad(){
		Espacio res= null;
		Comparator<Espacio> cmp= Comparator.comparing(x->x.getCapacidad());
		Stream<Espacio> streamEspacios= this.getEspacios().stream();
		Optional<Espacio> opt = streamEspacios.max(cmp);
		if(opt.isPresent()){
			res= opt.get();
		}
		
		else{
			throw new ExcepcionCentroOperacionNoPermitida();
		}
		
		return res;
	}
	

	public Integer[] getConteosEspacios(){
		Integer [] res = {0,0,0,0,0};
		res[0]= (int) getEspacios().stream().filter(x-> x.getTipo().equals(TipoEspacio.TEORIA)).count();
		res[1]= (int) getEspacios().stream().filter(x-> x.getTipo().equals(TipoEspacio.LABORATORIO)).count();
		res[2]= (int) getEspacios().stream().filter(x-> x.getTipo().equals(TipoEspacio.SEMINARIO)).count();
		res[3]= (int) getEspacios().stream().filter(x-> x.getTipo().equals(TipoEspacio.EXAMEN)).count();
		res[4]= (int) getEspacios().stream().filter(x-> x.getTipo().equals(TipoEspacio.OTRO)).count();
		return res;
	}
	

	public Set<Despacho> getDespachos(){
		Stream<Despacho> aux = getEspacios().stream().filter(x->x instanceof Despacho).map(x->(Despacho) x);
		return aux.collect(Collectors.toSet());
	}
	
	public Set<Despacho> getDespachos(Departamento d){
		Stream<Despacho> aux = getDespachos().stream().filter(x->despachoTieneProfDpto(d, x));
		return aux.collect(Collectors.toSet());
		
	}
	
	private Boolean despachoTieneProfDpto(Departamento d, Despacho e){
		return e.getProfesores().stream().anyMatch(p-> p.getDepartamento().equals(d));
	}
	
	
	public Set<Profesor> getProfesores(){
		Stream<Profesor> aux= getDespachos().stream().flatMap(x->x.getProfesores().stream());
		return aux.collect(Collectors.toSet());
	}

	
	public Set<Profesor> getProfesores(Departamento d){
		return getProfesores().stream().filter(x-> x.getDepartamento().equals(d)).collect(Collectors.toSet());
	}
	
	public SortedMap<String, Despacho> getDespachosPorProfesor(){
		
		/*Map<Profesor,Despacho> m = getProfesores().stream().filter(p->hasOffice(p)).collect(Collectors.toMap(x->x, x->searchOffice(x)));
		return new TreeMap<Profesor,Despacho>(m);*/
		
		SortedMap<String,Despacho> m = getProfesores().stream().filter(p->tieneDespacho(p)).collect(Collectors.toMap(x->x.toString(), x->buscaDespacho(x),(x1,x2)->x1,TreeMap::new));
		return m;
		
	}
	
	private Despacho buscaDespacho(Profesor p){
		return getDespachos().stream().filter(x->x.getProfesores().contains(p)).findAny().get();
	}
	
	private Boolean tieneDespacho(Profesor p){
		return getDespachos().stream().anyMatch(d-> d.getProfesores().contains(p));
	}

}
	
