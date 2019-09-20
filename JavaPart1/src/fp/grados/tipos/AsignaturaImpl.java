package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura {
	//Atributos
	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;
		
	//Método constructor
	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso){
		checkCodigo(codigo);
		checkCurso(curso);
		checkCreditos(creditos);
			
			
		this.nombre=nombre;
		this.codigo=codigo;
		this.creditos=creditos;
		this.tipo=tipo;
		this.curso=curso;
		this.departamento=null;
	}
	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso, Departamento d){
		checkCodigo(codigo);
		checkCurso(curso);
		checkCreditos(creditos);
			
			
		this.nombre=nombre;
		this.codigo=codigo;
		this.creditos=creditos;
		this.tipo=tipo;
		this.curso=curso;
		setDepartamento(d);
	}
	
	public AsignaturaImpl(String s){
		String[] aux = s.split("#");
		if(aux.length!=5){
			throw new IllegalArgumentException(
					"El formato de la cadena de entrada no es correcto.");
					}
		
		String codigo = aux[1].trim();
		Double creditos = new Double(aux[2].trim());
		Integer curso = new Integer(aux[4].trim());
		
		checkCodigo(codigo);
		checkCurso(curso);
		checkCreditos(creditos);
		
		this.nombre = aux[0].trim();
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = TipoAsignatura.valueOf(aux[3].trim());
		this.curso = curso;
		
		
		
	}
		
	//Métodos de la clase AsignaturaImpl
	public String getNombre(){
		return nombre;
	}
		
	public String getAcronimo() {
		String acronimo = "";
		for (char c: getNombre().toCharArray()) {
			if (Character.isUpperCase(c)) {
			acronimo += c;
			}
		}
		return acronimo;
	}

	public String getCodigo(){
		return codigo;
	}
		
	public Double getCreditos(){
		return creditos;
	}
		
	public TipoAsignatura getTipo(){
		return tipo;
	}
		
	public Integer getCurso(){
		return curso;
	}
		
	public Departamento getDepartamento(){
		return departamento;
	}
		
	public void setDepartamento(Departamento nuevoDepartamento){	
		if (nuevoDepartamento != this.departamento) {
			Departamento antiguoDepartamento = this.departamento;
			this.departamento = nuevoDepartamento;
			if (antiguoDepartamento != null) {
				antiguoDepartamento.eliminaAsignatura(this);
			}
			if (nuevoDepartamento != null) {
				nuevoDepartamento.nuevaAsignatura(this);
			}
		}
		
	}
		
		
	//Método toString, equals, hashCode y compareTo
	public String toString(){
		return "("+ getCodigo() +") " + getNombre();
	}
		
	public boolean equals(Object o){
		boolean r= false;
		if(o instanceof Asignatura){
			Asignatura a=(Asignatura) o;
			r= getCodigo().equals(a.getCodigo());
			}
		return r;
	}
		
	public int hashCode(){
		return getCodigo().hashCode();
	}
		
	public int compareTo(Asignatura a){
		
		return getCodigo().compareTo(a.getCodigo());
	}
		
	//Métodos checkers
		
	private void checkCodigo(String codigo){
		if(!(codigo.length()==7 &&
				Character.isDigit(codigo.charAt(0))&&
				Character.isDigit(codigo.charAt(1))&&
				Character.isDigit(codigo.charAt(2))&&
				Character.isDigit(codigo.charAt(3))&&
				Character.isDigit(codigo.charAt(4))&&
				Character.isDigit(codigo.charAt(5))&&
				Character.isDigit(codigo.charAt(6)))
			){
				throw new ExcepcionAsignaturaNoValida("El código debe estar formado por 7 números.");
			}
		}
		
	private void checkCurso(Integer curso){
			if(curso<1 || curso>4){
				throw new ExcepcionAsignaturaNoValida("El curso debe estar entre 1 y 4.");
			}
	}
	private void checkCreditos(Double creditos){
			if(creditos<=0){
				throw new ExcepcionAsignaturaNoValida("El numero de créditos debe seer mayor que 0.");
			}
	}
}
