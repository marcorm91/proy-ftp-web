package controlador;

import java.io.File;

import modelo.Conexion;
import modelo.MUsuarios;

public class CRegistro {
	
	private MUsuarios modeloUsuarios;
	private Conexion conexionBD;
	
	public CRegistro(){
		conexionBD = new Conexion();
		modeloUsuarios = new MUsuarios(conexionBD.getConexion());
	}
	
	/**
	 * Método que nos registrará a un usuario en la BD para que éste pueda tener su directorio
	 * personal y poder acceder a uno de acceso común.
	 * @param nombre	 Nombre del usuario.
	 * @param apellidos	 Apellidos del usuario.
	 * @param email		 Correo del usuario.
	 * @param password	 Contraseña del usuario.
	 * @param usuario	 Nombre de usuario.
	 * @return Devuelve 1 si la insercción tuvo éxito, de lo contrario, devolverá 0 si hubo algún
	 * problema en la insercción.
	 */
	public int registraUsuario(String nombre, String apellidos, String email, String password, String usuario){
		int registrosInsertados;
		return registrosInsertados = modeloUsuarios.registrarUsuario(nombre, apellidos, email, password, usuario);		
	}
	
	/**
	 * Como clave principal tendremos al nombre de usuario, es decir, no habrá en la BD 2 usuarios con
	 * el mismo nombre, por lo que comprobamos que cuando vaya a registrarse no exista ya un usuario
	 * con ese nombre.
	 * @param usuario Nombre de usuario.
	 * @return Devuelve true si existe, y si no existe devolverá false.
	 */
	public boolean compruebaExistencia(String usuario){
		boolean existe;
		return existe = modeloUsuarios.compruebaUser(usuario);
	}

	/**
	 * Cuando registramos a un usuario, le crearemos un directorio personal con su nombre de usuario.
	 * Con esto podremos identificar fácilmente su directorio y poder acceder al mismo.
	 * @param usuario Nombre de usuario.
	 */
	public void creaDirectorio(String usuario){
		File dir = new File("WebContent/dir_ftp/"+usuario);
		dir.mkdir();
	}
	
}
