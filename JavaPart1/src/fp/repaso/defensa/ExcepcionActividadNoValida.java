package fp.repaso.defensa;

public class ExcepcionActividadNoValida extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExcepcionActividadNoValida(){
		super();
	}
	public ExcepcionActividadNoValida(String mensajeError){
		super(mensajeError);
		}
}
