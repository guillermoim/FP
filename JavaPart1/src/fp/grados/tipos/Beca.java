package fp.grados.tipos;

public interface Beca extends Comparable<Beca> {
	String getCodigo();
	Double getCuantiaTotal();
	void setCuantiaTotal(Double c);
	Integer getDuracion();
	void setDuracion(Integer d);
	TipoBeca getTipo();
	Double getCuantiaMensual();
	

}
