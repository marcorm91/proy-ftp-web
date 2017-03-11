<%@page import="controlador.CLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 	if(session.getAttribute("identificacion") != null) response.sendRedirect("principal.jsp"); %>
  
<%!
   	private CLogin controladorLogin;
   	private boolean correcto;
   	private Cookie[] cookies;
   	
   	private void initControlador(){
   		controladorLogin = new CLogin();
   	}
%>

<% initControlador(); %>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/estilos.css">
<title>Identificación servicio FTP</title>
</head>
<body>

	<div class="container">
		<div class="row text-xl-center titulo">
			<div class="col-xl-12">
				<h2 class="text-primary"> <b>Identificación de usuario </b></h2>
			</div>
		</div>

	<form method="post">			
		<div class="row text-xl-left formulario">
			<div class="col-xl-12">
			    <form class="form-signin text-left">
		        <label>Usuario:</label>
		        <input type="text"
		        	   id="inputUser" 
		        	   name="usuario"
		        	   value="<% 
		        	   				cookies = request.getCookies();
		        					for(int i = 0; i < cookies.length; i++){
		        						String id = cookies[i].getName();
		        						String valor = cookies[i].getValue();
		        						
		        						if(id.equals("usuario")){
		        							out.print(valor);
		        						}else{
		        							out.print("");
		        						}
		        					}
		        	   		  %>"
		        	   class="form-control" 
		        	   placeholder="Introduzca su usuario" 
		        	   required autofocus>
		        <br/>
		        <label>Contraseña:</label>
		        <input type="password" 
		        	   id="inputPassword" 
		        	   name="password"
		        	   class="form-control" 
		        	   placeholder="Introduzca su contraseña" required>
				<input type="checkbox" name="cookie"> Recordar usuario <br/>
				<a href="registro.jsp"><b>Registrar usuario</b></a>
		        <br class="saltoRegistro"/><br/>
		        <button class="btn btn-md btn-primary btn-block" name="enviar" type="submit">Acceder</button>
		      </form>
	     	</div>
	     </div>
	</form>
	</div>
	
	<% 
		if(request.getParameter("enviar") != null){

			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");
			
			if(request.getParameter("cookie") != null){
				Cookie cookieUsu = new Cookie("usuario", usuario);
				response.addCookie(cookieUsu);
				cookieUsu.setMaxAge(1000);
			}

			correcto = controladorLogin.checkLogin(password, usuario); 
			
			if(correcto){
				session.setAttribute("identificacion", usuario);
				response.sendRedirect("principal.jsp");
			}else{
				%> <script type="text/javascript">alert("¡ERROR! Error de autenticación.");</script> <%
			}
		}
	%>
	
<script type="text/javascript" src="js/jquery-3.1.1.min" />
<script type="text/javascript" src="js/bootstrap.min.js" />
</body>
</html>