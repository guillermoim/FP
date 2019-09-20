package fp.repaso.defensa;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.Espacio;

public interface Actividad  extends Comparable<Actividad>{
	String getCodigo();
	DayOfWeek getDiaSemana();
	LocalTime getHoraComienzo();
	LocalTime getHoraFinal();
	Espacio getEspacio();
	void setEspacio(Espacio e);
	Integer getDuracion();
	TipoActividad getTipoActividad();
	Asignatura getAsignatura();
}
