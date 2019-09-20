package fp.grados.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona {
	//Atributos
	private String nombre;
	private String apellidos;
	private String dni;
	private LocalDate fechaNacimiento;
	private String email;
	
	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
	String email){
		checkDNI(dni);
		checkFecha(fechaNacimiento);
		checkEmail(email);

		
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.dni=dni;
		this.fechaNacimiento=fechaNacimiento;
		this.email=email;			
	}
	
	public PersonaImpl(String s){
		String[] aux = s.split(",");
		if (aux.length != 5) {
		throw new IllegalArgumentException(
		"El formato de la cadena de entrada no es correcto.");
		}
		String dni = aux[0].trim();
		String email = aux[4].trim();
		LocalDate fechaNacimiento = LocalDate.parse(aux[3].trim(),
		DateTimeFormatter.ofPattern("d/M/y"));
		checkDNI(dni);
		checkFecha(fechaNacimiento);
		checkEmail(email);
		this.dni = dni;
		this.nombre = aux[1].trim();
		this.apellidos = aux[2].trim();
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;

	}
	
	public PersonaImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento){
		checkDNI(dni);
		checkFecha(fechaNacimiento);

		this.nombre=nombre;
		this.apellidos=apellidos;
		this.dni=dni;
		this.fechaNacimiento=fechaNacimiento;
		email="";
	}
	
	public String getDNI(){
		return dni;
	}
	public void setDNI(String dni){
		checkDNI(dni);
		this.dni=dni;
		
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getApellidos(){
		return apellidos;
	}
	public void setApellidos(String apellidos){
		this.apellidos=apellidos;
	}
	
	public LocalDate getFechaNacimiento(){
		
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento){
		checkFecha(fechaNacimiento);
		this.fechaNacimiento=fechaNacimiento;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		checkEmail(email);
		this.email=email;
	}
	
	public Integer getEdad(){
		return (int)getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);		
	}
	
	//Metodos toString, equals, hashCode, y compareTo
		public String toString(){
			 return getDNI() + " - " + getApellidos() + ", " + getNombre() + " - " +getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		public boolean equals(Object o){
			boolean r= false;
			if(o instanceof Persona){
				Persona p=(Persona) o;
				r=getDNI().equals(p.getDNI())&& getNombre().equals(p.getNombre())
						&& getApellidos().equals(p.getApellidos());
				
			}
			return r;
		}
		
		public int hashCode(){
			return getDNI().hashCode()+ 31*getApellidos().hashCode()+ 31*31*getNombre().hashCode();
		}
		public int compareTo(Persona p){
			int r=getNombre().compareTo(p.getNombre());
			if (r==0){
				r=getApellidos().compareTo(p.getApellidos());
				if(r==0){
					r=getDNI().compareTo(p.getDNI());
				}
			}
			return r;
		}
		

	
	//Métodos checkers
		private void checkDNI(String dni){
			
			if( !(dni.length()==9&&
			Character.isDigit(dni.charAt(0))&&
			Character.isDigit(dni.charAt(1))&&
			Character.isDigit(dni.charAt(2))&&
			Character.isDigit(dni.charAt(3))&&
			Character.isDigit(dni.charAt(4))&&
			Character.isDigit(dni.charAt(5))&&
			Character.isDigit(dni.charAt(6))&&
			Character.isDigit(dni.charAt(7))&&
			Character.isLetter(dni.charAt(8))&&
			letraDni(dni)==Character.toUpperCase(dni.charAt(8)))){
				throw new ExcepcionPersonaNoValida("El dni debe estar formado por 8 dígitos seguidos de una letra.");
			}
				
		}
			
			//METODO AUXILIAR PARA CHECK DNI
		private char letraDni(String dni){
			Integer a= new Integer(dni.substring(0, 8));
			String v = "TRWAGMYFPDXBNJZSQVHLCKE";
			Integer l=a%23;
			char letraDNI=v.charAt(l);
			return letraDNI;
			
		}
		
		private void checkEmail(String email){
			if(!(email.isEmpty()||email.contains("@"))){
				throw new ExcepcionPersonaNoValida("El email debe contener @ o ser una cadena vacía.");
				
			}
		}
		
		private void checkFecha(LocalDate fechaNacimiento){
			if(!fechaNacimiento.isBefore(LocalDate.now())){
				throw new ExcepcionPersonaNoValida("La fecha de nacimiento debe ser anterior a la fecha de hoy");
			}
		}
		
}
