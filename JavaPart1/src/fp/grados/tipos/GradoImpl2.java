package fp.grados.tipos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GradoImpl2 extends GradoImpl implements Grado{
	
	
	public GradoImpl2(String nombre,Set<Asignatura> asignaturasObligatorias,Set<Asignatura> asignaturasOptativas,Double numeroMinimoCreditosOptativas){
		super(nombre,new HashSet<>(asignaturasObligatorias),new HashSet<>(asignaturasOptativas),numeroMinimoCreditosOptativas);
	}
	
	public Double getNumeroTotalCreditos(){
		return getAsignaturasObligatorias().stream().mapToDouble(a->a.getCreditos()).sum() + getNumeroMinimoCreditosOptativas();
	}
	
	public Set<Asignatura> getAsignaturas(Integer curso){
		Stream<Asignatura> ob= getAsignaturasObligatorias().stream().filter(a-> a.getCurso().equals(curso));
		Stream<Asignatura> op= getAsignaturasOptativas().stream().filter(a-> a.getCurso().equals(curso));
		return Stream.concat(ob, op).collect(Collectors.toSet());
		
	}
	
	public Asignatura getAsignatura(String codigo){
		Asignatura res =null;
		Optional<Asignatura> opt= Stream.concat(getAsignaturasObligatorias().stream(),getAsignaturasOptativas().stream())
				.filter(x->x.getCodigo().equals(codigo)).findAny();
		if(opt.isPresent()){
			res= opt.get();
		}
		
		return res;
	}
	
	public Set<Departamento> getDepartamentos(){
	 return Stream.concat(getAsignaturasObligatorias().stream(),getAsignaturasOptativas().stream())
			 .map(x->x.getDepartamento()).collect(Collectors.toSet());
	}
	
	public Set<Profesor> getProfesores(){
		return getDepartamentos().stream().flatMap(x->x.getProfesores().stream()).collect(Collectors.toSet());
	}
	
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura(){
		return Stream.concat(getAsignaturasObligatorias().stream(),getAsignaturasOptativas().stream())
				.collect(Collectors.toMap(x->x, x->x.getCreditos(), (x1,x2)->x1, TreeMap::new));
		
	}
}
