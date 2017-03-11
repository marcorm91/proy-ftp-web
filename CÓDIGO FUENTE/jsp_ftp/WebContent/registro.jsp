<%@page import="controlador.CRegistro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    	private CRegistro controladorRegistro;
    	private int registrosInsertados;
    	private boolean existe;
    
    	private void initControlador(){
    		controladorRegistro = new CRegistro();
    	}
    %>
    
    <% initControlador(); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de usuario</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>

<div class="container">
		<div class="row text-xl-center titulo">
			<div class="col-xl-12">
				<h2 class="text-primary"> <b>Registro de usuario </b></h2>
			</div>
		</div>

<form method="post">	
	
	<div class="col-xl-6 formularioRegistro">		
		
		<div class="form-group row">
		  <label class="col-xl-3 col-form-label">Nombre </label>
		  <div class="col-xl-9">
		    <input class="form-control" name="nombre" type="text" id="reg_nombre">
		  </div>
		</div>
		
		<div class="form-group row">
		  <label class="col-xl-3 col-form-label">Apellidos </label>
		  <div class="col-xl-9">
		    <input class="form-control" name="apellidos" type="text" id="reg_apellidos">
		  </div>
		</div>
		
		<div class="form-group row">
		  <label class="col-xl-3 col-form-label">E-mail </label>
		  <div class="col-xl-9">
		    <input class="form-control" name="email" type="email" id="reg_email">
		  </div>
		</div>
		<hr/>
		
		<div class="form-group row">
		  <label class="col-xl-3 col-form-label">Usuario * </label>
		  <div class="col-xl-9">
		    <input class="form-control" name="usuario" type="text" id="reg_usuario" required>
		  </div>
		</div>
		
		<div class="form-group row">
		  <label class="col-xl-3 col-form-label">Contraseña * </label>
		  <div class="col-xl-9">
		    <input class="form-control" name="password" type="password" id="reg_pass" required>
		  </div>
		</div>
		
		<div class="row">
			<div class="col-xl-6">
				<a href="index.jsp" class="btn btn-md btn-primary btn-block"> Atrás</a>
			</div>
			<div class="col-xl-6">
				<button class="btn btn-md btn-primary btn-block" name="enviar" type="submit">Registrar</button>
			</div>
		</div>
	</div>

</form>

</div>

<%

	//La primera vez que entramos a registro.jsp los parámetros que va a recoger el formulario
	//de cada campo serán nulos, por lo tanto, esta condición se ejecutará cuando enviemos dicho
	//formulario con los datos exigidos.
	if(request.getParameter("enviar") != null){

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		existe = controladorRegistro.compruebaExistencia(usuario);
		
		if(!existe){
			registrosInsertados = controladorRegistro.registraUsuario(nombre, apellidos, email, password, usuario); 
			controladorRegistro.creaDirectorio(usuario);
			%> <script type="text/javascript">alert("¡BIEN! Usuario registrado con éxito.");</script> <%
		}else{
			%> <script type="text/javascript">alert("¡ERROR! Ese usuario ya existe.");</script>  <%
		}
		
	}

%>
	
<script type="text/javascript" src="js/jquery-3.1.1.min" />
<script type="text/javascript" src="js/bootstrap.min.js" />
</body>
</html>
