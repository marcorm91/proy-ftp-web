package controlador;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Conexion;
import modelo.MFicheros;

/**
 * Servlet implementation class CEliminar
 */
@WebServlet("eliminar")
public class CEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession hs;
	private Conexion conexionBD;
	private MFicheros modeloFicheros;
              
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CEliminar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Elimina el fichero del directorio del usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		conexionBD = new Conexion();
        	modeloFicheros = new MFicheros(conexionBD.getConexion());
		
		hs = request.getSession();
		File dir;
		String nombreFichero;
		String usuario;
		
		// Si la session es != null, quiere decir que el usuario está logueado, por lo tanto, nos aseguramos
		// de que lo esté para proceder con la eliminación del fichero.
		if(hs.getAttribute("identificacion") != null){
			
			// Recogemos el nombre del fichero, por ejemplo ?fichero=nota.txt  --> nota.txt
			if(request.getParameter("fichero") != null){
				nombreFichero = request.getParameter("fichero");
				
				usuario = hs.getAttribute("identificacion").toString();
				
				// Identificamos el directorio donde se encuentra ese fichero partiendo desde WebContent.
				dir = new File("WebContent/dir_ftp/"+hs.getAttribute("identificacion").toString()+"/"+nombreFichero);
				// Y una vez que lo tenemos identificado, le pasamos al método del modelo ficheros
				// para que se encargue de eliminar el fichero de la BD.  Para ello, le pasamos el nombre de usuario
				// que es el que se encuentra conectado en ese momento y la ruta completa del fichero.
				modeloFicheros.eliminarFichero(usuario, dir.getAbsolutePath());
			
				// Procedemos a eliminar el fichero y redirigimos a la página principal.
				dir.delete();
				response.sendRedirect("principal.jsp");
			}
			
		}else{
			// Si la session es null querrá decir que no se logueó el usuario, por lo tanto lo mandamos a
			// index.jsp
			response.sendRedirect("index.jsp");
		}
		
		
		try {
  			conexionBD.getConexion().close();
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
