package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.SortedSet;
import fp.grados.tipos.Categoria;

public interface Profesor extends Persona {
	
	Categoria getCategoria();
	void setCategoria(Categoria c);
	SortedSet<Tutoria> getTutorias();
	void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia);
	void borraTutoria(LocalTime horaComienzo, DayOfWeek dia);
	void borraTutorias();
	Departamento getDepartamento();
	void setDepartamento(Departamento d);
	List<Asignatura> getAsignaturas();
	List<Double> getCreditos();
	Double getDedicacionTotal();
	void imparteAsignatura(Asignatura a, Double dedicacion);
	Double dedicacionAsignatura(Asignatura a);
	void eliminaAsignatura(Asignatura a);
	
}
