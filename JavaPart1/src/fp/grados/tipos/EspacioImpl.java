package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;

public class EspacioImpl implements Espacio {
	//Atributos
	private TipoEspacio espacio;
	private String nombre;
	private Integer capacidad;
	private Integer planta;
		
		//Constructores
	public EspacioImpl(TipoEspacio espacio, String nombre, Integer capacidad, Integer planta){
		checkCapacidad(capacidad);
			
		this.espacio=espacio;
		this.nombre=nombre;
		this.capacidad=capacidad;
		this.planta=planta;
	}
		
	
	public EspacioImpl(String s){
		String[] aux = s.split(",");
		if(aux.length!=4){
			throw new IllegalArgumentException(
					"El formato de la cadena de entrada no es correcto.");
					}
		Integer capacidad = new Integer(aux[2].trim());
		checkCapacidad(capacidad);
		
		this.nombre = aux[0].trim();
		this.planta = new Integer(aux[1].trim());
		this.capacidad = capacidad;
		this.espacio= TipoEspacio.valueOf(aux[3].trim());
		
	}
		
		//Métodos
		public TipoEspacio getTipo(){
			return espacio;
		}
		public void setTipo(TipoEspacio espacio){
			this.espacio=espacio;
		}
		
		public String getNombre(){
			return nombre;
		}
		public void setNombre(String nombre){
			this.nombre=nombre;
		}
		
		public Integer getCapacidad(){
			return capacidad;
		}
		public void setCapacidad(Integer capacidad){
			checkCapacidad(capacidad);
			this.capacidad=capacidad;
		}
		
		public Integer getPlanta(){
			return planta;
		}
		//Metodos toString, equals, hashCode, y compareTo
		public String toString(){
			return getNombre() +"(planta "+getPlanta()+")";
		}
		
		public boolean equals(Object o){
			boolean r=false;
			if(o instanceof Espacio){
				Espacio e=(Espacio) o;
				r= getNombre().equals(e.getNombre()) && getPlanta().equals(e.getPlanta());
			}
			return r;
		}
		
		public int hashCode(){
			return getPlanta().hashCode()+ 31*getNombre().hashCode();
		}
		
		public int compareTo(Espacio e){
			int r=getPlanta().compareTo(e.getPlanta());
			if(r==0){
				r= getNombre().compareTo(e.getNombre());
			}
			return r;
		}
		
		
		//Métodos checkers
		private void checkCapacidad(Integer capacidad){
			if(capacidad<=0){
				throw new ExcepcionEspacioNoValido("La capacidad tiene que ser mayor que 0");
			}
		}
}
