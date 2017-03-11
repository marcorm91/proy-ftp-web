package controlador;

import modelo.Conexion;
import modelo.MUsuarios;

public class CLogin {
	
	private MUsuarios modeloUsuarios;
	private Conexion conexionBD;
	
	public CLogin(){
		conexionBD = new Conexion();
		modeloUsuarios = new MUsuarios(conexionBD.getConexion());
	}
	
	/**
	 * Método que nos checkea la identificación del usuario para que éste pueda
	 * acceder a principal.jsp
	 * @param password Contraseña del usuario.
	 * @param usuario Nombre de usuario.
	 * @return Devuelve true si la identificación es correcta sino devolverá false.
	 */
	public boolean checkLogin(String password, String usuario){
		boolean correcto;
		return correcto = modeloUsuarios.checkLogin(password, usuario);	
	}

}
