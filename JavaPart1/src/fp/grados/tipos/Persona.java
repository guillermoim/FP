package fp.grados.tipos;

import java.time.LocalDate;

public interface Persona  extends Comparable<Persona> {
	String getDNI();
	void setDNI(String dni);
	String getNombre();
	void setNombre(String nombre);
	String getApellidos();
	void setApellidos(String apellidos);
	String getEmail();
	void setEmail(String email);
	LocalDate getFechaNacimiento();
	void setFechaNacimiento(LocalDate fecha);
	Integer getEdad();

}
