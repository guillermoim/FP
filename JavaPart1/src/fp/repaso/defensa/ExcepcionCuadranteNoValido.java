package fp.repaso.defensa;

public class ExcepcionCuadranteNoValido extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExcepcionCuadranteNoValido(){
		super();
	}
	public ExcepcionCuadranteNoValido(String mensajeError){
		super(mensajeError);
		}
}
