package fp.grados.tipos;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DepartamentoImpl2 extends DepartamentoImpl implements Departamento {
	
	
	public DepartamentoImpl2(String nombre){
		super(nombre);
	}
	
	public void borraTutorias() {
		getProfesores().stream().forEach(Profesor::borraTutorias);
	}
	
	public void borraTutorias(Categoria cat) {
		getProfesores().stream().filter(p -> p.getCategoria().equals(cat)).forEach(Profesor::borraTutorias);
	}
	
	public Boolean existeProfesorAsignado(Asignatura asig) {
		return getProfesores().stream().anyMatch(x -> x.getAsignaturas().contains(asig));

	}
	
	public Boolean estanTodasAsignaturasAsignadas() {
		return getAsignaturas().stream().allMatch(x->existeProfesorAsignado(x));
	}
	
	public void eliminaAsignacionProfesorado(Asignatura asig) {
		getProfesores().stream().filter(p -> p.getAsignaturas().contains(asig)).forEach(x -> x.eliminaAsignatura(asig));
	}
	
	public SortedMap<String, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		return getProfesores().stream().collect(Collectors.toMap(p -> p.toString(),  p -> p.getTutorias(), (p1, p2) -> p1, TreeMap::new));
	}
}
