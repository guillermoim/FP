package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public class BecaImpl implements Beca {
	private static final Double CUANTIA_MINIMA = 1500.0;	
	private String codigo;	
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;
	
	public BecaImpl(String codigo,Double cuantiaTotal, Integer duracion, TipoBeca tipo){
		checkCodigo(codigo);
		checkDuracion(duracion);
		checkCuantiaTotal(cuantiaTotal);
		this.codigo=codigo;
		this.cuantiaTotal=cuantiaTotal;
		this.duracion= duracion;
		this.tipo= tipo;
	}
		
	public BecaImpl(String codigo,TipoBeca tipo){
		checkCodigo(codigo);
		this.codigo=codigo;
		this.tipo= tipo;
		cuantiaTotal= CUANTIA_MINIMA;
		duracion= 1;
	}
	
	
	//“ABC1234,6000.0,12,ORDINARIA”
	
	public BecaImpl(String s){
		String[] aux= s.split(",");
		if(aux.length!=4){
			throw new IllegalArgumentException("Invalid String format");
		}
		
		checkCodigo(aux[0].trim());
		checkCuantiaTotal(new Double(aux[1].trim()));
		checkDuracion(new Integer(aux[2].trim()));
		
		this.codigo=aux[0].trim();
		this.cuantiaTotal=new Double(aux[1].trim());
		this.duracion= new Integer(aux[2].trim());
		this.tipo= TipoBeca.valueOf(aux[3].trim());
		
		
	}
	
	private void checkCodigo(String codigo){
		if	(codigo.length()!=7 
			|| !Character.isLetter(codigo.charAt(0))
			|| !Character.isLetter(codigo.charAt(1)) 
			|| !Character.isLetter(codigo.charAt(2))
			|| !Character.isDigit(codigo.charAt(3)) 
			|| !Character.isDigit(codigo.charAt(4)) 
			|| !Character.isDigit(codigo.charAt(5)) 
			|| !Character.isDigit(codigo.charAt(6))
			){
			throw new ExcepcionBecaNoValida("The alphanumeric is not composed by 3 letters and 4 digits");
		}	
	}
	
	private void checkDuracion(Integer duracion){
		if(duracion<1){
			throw new ExcepcionBecaNoValida("The duration is not at least 1 month");
		}
	}
	
	private void checkCuantiaTotal(Double cuantiaTotal){
		if(cuantiaTotal< CUANTIA_MINIMA){
			throw new ExcepcionBecaNoValida("The total amount is not at least 1500.0");
		}
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public void setCuantiaTotal(Double cuantiaTotal) {
		checkCuantiaTotal(cuantiaTotal);
		this.cuantiaTotal = cuantiaTotal;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		checkDuracion(duracion);
		this.duracion = duracion;
	}

	public Double getCuantiaMensual() {
		Double cuantiaMensual= getCuantiaTotal()/getDuracion();
		return cuantiaMensual;
	}


	public TipoBeca getTipo() {
		return tipo;
	}
	
	public String toString(){
		return "["+getCodigo()+","+getTipo()+"]";
	}

	//equals
	public boolean equals(Object o){
		boolean res=false;
		if(o instanceof Beca){
			Beca b = (Beca)o;
			res = this.getCodigo().equals(b.getCodigo()) && this.getTipo().equals(b.getTipo());	
		}
	return res;
	}
	
	
	//hashcode
	public int hashCode(){
		return this.getCodigo().hashCode() + this.getCodigo().hashCode()*31 ;
	}
		
	//compareTo
	public int compareTo(Beca b){
		int res= this.getCodigo().compareTo(b.getCodigo());
		if(res==0){
			res= this.getTipo().compareTo(b.getTipo());
		}
			return res;
	}
}
