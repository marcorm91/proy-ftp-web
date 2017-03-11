package modelo;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MFicheros {

	private final Connection conexion;

    /**
     * Constructor
     * @param conexion conexión a la BBDD
     * @param controlador
     */
    public MFicheros(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Registra el fichero subido a la BD, ya sea público o privado, por el usuario.
     * @param usuario	Usuario que sube el fichero.
     * @param fichero	El fichero en sí.
     * @param tipo		Tipo de fichero (PÚBLICO ó PRIVADO)
     * @param ruta		Ruta completa del fichero.
     */
	public void registrarFichero(String usuario, byte [] fichero, String tipo, String ruta){
	    	
		String insertFich = "INSERT INTO bd_ftp_jsp.ficheros VALUES (?,?,?,?);";
			
			 try{
				 
				 PreparedStatement sentencia = conexion.prepareStatement(insertFich);	
					 sentencia.setString(1, usuario);
					 sentencia.setBytes(2, fichero);
					 sentencia.setString(3, tipo);
					 sentencia.setString(4, ruta);
					 
					 sentencia.executeUpdate();
					 
			 }catch(Exception e){
		    	 System.out.println(e);
		     }
	}

	
	/**
	 * Elimina el fichero de la BD cuando el usuario pulsa sobre el enlace eliminar. 
	 * @param usuario	Usuario que va a eliminar el fichero.
	 * @param ruta		Fichero que va a eliminar.
	 */
	public void eliminarFichero(String usuario, String ruta) {
		
		String delFich = "DELETE FROM bd_ftp_jsp.ficheros WHERE usuario = ? and ruta = ?";
		
		 try{
			 
			 PreparedStatement sentencia = conexion.prepareStatement(delFich);	
				 sentencia.setString(1, usuario);
				 sentencia.setString(2, ruta);
				 
				 sentencia.executeUpdate();
				 
		 }catch(Exception e){
	    	 System.out.println(e);
	     }
		
	}
	
}
