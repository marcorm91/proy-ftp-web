package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CLogout
 */
@WebServlet("logout")
public class CLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession hs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Nos deslogueará de la aplicación cuando el usuario pulse sobre Desconectar.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenemos la sesión del usuario visitante a la página web.
		hs = request.getSession();
		
		// Modificamos el valor del parámetro identificación a null.
		hs.setAttribute("identificacion", null);
		
		// Invalidamos la session.
		hs.invalidate();
		
		// Redirigimos a index.jsp
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
