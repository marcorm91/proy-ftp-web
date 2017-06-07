package controlador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import modelo.Conexion;
import modelo.MFicheros;
import modelo.MUsuarios;

/**
 * Servlet implementation class CSubida
 */
@WebServlet("subidaPers")
@MultipartConfig
public class CSubidaPers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession hs;
	private Conexion conexionBD;
	private MFicheros modeloFicheros;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSubidaPers() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Se encargará de la subida de ficheros personales al directorio que le corresponda al usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		conexionBD = new Conexion();
		modeloFicheros = new MFicheros(conexionBD.getConexion());
		
		hs = request.getSession();
		String usuario;
		
		if(hs.getAttribute("identificacion") != null){

			try{
				
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
	
			for (Object item : items) {
			   FileItem uploaded = (FileItem) item;
			   		   
			   if (!uploaded.isFormField()) {
				   
				  usuario = hs.getAttribute("identificacion").toString();
	
				  // Identificamos la ruta completa del usuario para hacer la subida, y cuando la tengamos
				  // insertamos en la BD, en la tabla ficheros, un registro con el nombre de usuario, el tipo
				  // de fichero que es (público o privado) y la ruta absoluta del mismo.
			      File fichero = new File("WebContent/dir_ftp/"+hs.getAttribute("identificacion"), uploaded.getName());
			      modeloFicheros.registrarFichero(usuario, uploaded.get(), "PERSONAL", fichero.getAbsolutePath());
			      
			      if(!fichero.isDirectory()){
				      try {
						uploaded.write(fichero);
				      } catch (Exception e) {
					e.printStackTrace();
				      }
			      }
			      
			   }
			
			}
			
			}catch(FileUploadException e){
				System.out.println(e);
			}
			
			// Cuando haga la subida, redirigimos a principal.jsp
			response.sendRedirect("principal.jsp");
		
		}else{
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
