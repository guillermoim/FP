package fp.repaso.defensa;

import java.util.List;

import fp.grados.tipos.Alumno;

public interface Cuadrante extends Comparable<Cuadrante> {
	Integer getSemana();
	Alumno getAlumno();
	List<Actividad> getActividades();
	void nuevaActividad(Actividad a);
	void eliminaActividad(Actividad a);
	Boolean contieneActividad(Actividad a);
}
