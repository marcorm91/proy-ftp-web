package controlador;

import java.io.File;

public class CLista {
		
	/**
	 * Método que nos devolverá un array de ficheros personales del directorio del usuario
	 * que se encuentre logueado en ese momento.
	 * @param usuario User logueado.
	 * @return Devuelve el array de ficheros.
	 */
	public String[] listarDirectorioPersonal(String usuario){
		// Localizamos la ruta del directorio del usuario para encontrar en ella todos
		// los ficheros disponibles.
		// Y cuando la localiza, los metemos todos en un array para imprimirlos por pantalla.
		File file = new File("WebContent/dir_ftp/"+usuario);
		String [] directorios = file.list();
		return directorios;
	}
	
	/**
	 * Este directorio es público, por lo que no es necesario pasarle ningun tipo
	 * de usuario.  Nos devolverá un array de ficheros de un directorio en concreto.
	 * @return Devuelve un array de ficheros.
	 */
	public String[] listarDirectorioPublico(){
		File file = new File("WebContent/dir_ftp/dir_public");
		String [] directorios = file.list();
		return directorios;
	}
}
