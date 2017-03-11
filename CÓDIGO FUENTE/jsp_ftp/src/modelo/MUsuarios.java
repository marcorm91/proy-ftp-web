package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MUsuarios {

	private final Connection conexion;

    /**
     * Constructor
     * @param conexion conexión a la BBDD
     * @param controlador
     */
    public MUsuarios(Connection conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Registra un usuario en la BD.
     * @param nombre	Nombre de usuario.
     * @param apellidos	Apellidos del usuario.
     * @param email		Correo del usuario.
     * @param password	Contraseña del usuario.
     * @param usuario	Nombre de usuario.
     * @return	Devuelve 1 si hubo insercción, de lo contrario devolverá 0.
     */
    public int registrarUsuario(String nombre, String apellidos, String email, String password, String usuario){
    	
		String insertUser = "INSERT INTO bd_ftp_jsp.usuarios VALUES (?,?,?,?,?);";
		int registrosInsertados = 0;
		
		 try{
			 
			 PreparedStatement sentencia = conexion.prepareStatement(insertUser);	
				 sentencia.setString(1, nombre);
				 sentencia.setString(2, apellidos);
				 sentencia.setString(3, email);
				 sentencia.setString(4, password);
				 sentencia.setString(5, usuario);
			 
			registrosInsertados =  sentencia.executeUpdate();
			   
		 }catch(Exception e){
	    	 System.out.println(e);
	     }
		 
		 return registrosInsertados;
		         
    }
    
    /**
     * Verifica en la BD que existe igualdad entre contraseña y usuario para la identificación
     * del mismo.
     * @param password	Contraseña del usuario.
     * @param usuario	Nombre de usuario.
     * @return	Devuelve true si la identificación es correcta, de lo contrario devolverá false.
     */
    public boolean checkLogin(String password, String usuario){
    	
    	// La consulta a realizar consiste en que nos devuelva el número de filas coincidentes de usuario y contraseña.
		String consultaUserPass = "SELECT COUNT(*) AS contador FROM bd_ftp_jsp.usuarios WHERE usuario = ? AND password = ?;";
		boolean correcto = false;
		int contador = 0;
    			        
        try{
        	
            PreparedStatement sentencia = conexion.prepareStatement(consultaUserPass);
            sentencia.setString(1, usuario);
            sentencia.setString(2, password);
            
            ResultSet rs = sentencia.executeQuery();
            
            while(rs.next()){
            	contador = rs.getInt("contador");
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        	// Si el número de filas devueltas en la consulta es 0, es que no hay coincidencia entre usuario y contraseña.
	        if(contador == 0){
	        	correcto = false;
	        // de lo contrario, es que hubo una coincidencia (1) entre usuario y contraseña.
	        }else{
	        	correcto = true;
	        }
	               
        return correcto;
		         
    }

    /**
     * Comprueba la existencia de usuario en la BD para no duplicar nombres de usuarios ya que
     * en nuestro caso nuestro nombre de usuario actua como clave primaria.  Es único.
     * @param usuario Nombre de usuario.
     * @return	Devuelve true si exsite, de lo contrario devolverá false.
     */
	public boolean compruebaUser(String usuario) {
		
		String consultaUser = "SELECT COUNT(*) AS contador FROM bd_ftp_jsp.usuarios WHERE usuario = ?;";
		
		boolean correcto = false;
		int contador = 0;
    			        
        try{
        	
            PreparedStatement sentencia = conexion.prepareStatement(consultaUser);
            sentencia.setString(1, usuario);
            
            ResultSet rs = sentencia.executeQuery();
            
            while(rs.next()){
            	contador = rs.getInt("contador");
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        	// Si el número de filas devueltas en la consulta es 0, es que no hay coincidencia entre usuario y contraseña.
	        if(contador == 0){
	        	correcto = false;
	        // de lo contrario, es que hubo una coincidencia (1) entre usuario y contraseña.
	        }else{
	        	correcto = true;
	        }
	               
        return correcto;
	}
	
}
