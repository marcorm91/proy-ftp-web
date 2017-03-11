<%@page import="controlador.CLista"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% 
	if(session.getAttribute("identificacion") == null){
		response.sendRedirect("index.jsp"); 
	}else{
%>

<%!
	private CLista controladorLista;
	private String [] directorioPers;
	private String [] directorioPubl;
	
	private void initControladorLista(){
		controladorLista = new CLista();
	}
%>

<%  
	initControladorLista(); 
	directorioPers = controladorLista.listarDirectorioPersonal(session.getAttribute("identificacion").toString());
	directorioPubl = controladorLista.listarDirectorioPublico();
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página principal FTP</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>

<nav class="navbar navbar-light bg-faded navbar-fixed-top">
		<span class="navbar-text">
			Conectado como:	<b> <% out.print(session.getAttribute("identificacion")); %> </b>
			<a href="logout"> (Desconectar) </a>
		</span>
</nav>
	
<div class="container">
	<div class="row text-center">
		<div class="col-xl-12">
			<br/><br/><br/>
			<ins class="text-danger"><h1 class="text-danger"> <b> SERVIDOR WEB FTP </b> </h1></ins>
		</div>
	</div>
	<br/>
</div>
		
<div class="container text-xl-center">
	<div class="row">
		<div class="col-xl-12">
			<h4> <b> SUBIR FICHERO </b> <i>(A directorio personal)</i> </h4>  <hr/>
				<div class="row">
					<div class="col-xl-12 botonPrincipal">
						<form action="subidaPers" enctype="multipart/form-data" method="post"> 
							<b>Fichero:</b>
							<input type="file" name="fichero" class="btn btn-sm text-muted"/></br><br/>
							<input type="submit" value="Subir a Directorio Personal" class="btn btn-md btn-primary"/> 
						</form> 
					</div>
				</div>
		</div>
	</div>
			
	<div class="row">
		<div class="col-xl-12 contenidoFTP">
			<br/><br/><h4> <b> CONTENIDO FTP DE <% out.print(session.getAttribute("identificacion").toString().toUpperCase()); %> </b> <i>(Carpeta personal)</i></h4> <hr/>
			
			<div class="row">
				<div class="col-xl-12 ">
					<table>
						<th> NOMBRE FICHERO </th> 
						<th> ELIMINAR </th>
							<% 
							for(int i = 0; i < directorioPers.length; i++){ %>  
							<tr>
							<td> 
								<a href="WebContent/dir_ftp/<%= session.getAttribute("identificacion").toString() %>/<% out.print(directorioPers[i]); %>" download>
									<% out.print(directorioPers[i]); %>
								</a>
							</td> 
							<td class="eliminar"> 
								<a href="eliminar?fichero=<%= directorioPers[i] %>"> X </a> 
							</td> 
						</tr> <% } %>
					</table>
				</div>
			</div>
			
		</div>
	</div>
				
	<div style="margin-top: 3em; border-top: 1em solid black; padding-top: 2em;">
		<div class="row">
			<div class="col-xl-12">
				<h4> <b> CONTENIDO PÚBLICO FTP</b> </h4> <hr/>
			
					<div class="row">
						<div class="col-xl-12 ">
							<table>
								<th> NOMBRE FICHERO </th> 
								<% 
									for(int i = 0; i < directorioPubl.length; i++){
								%>  <tr>
									<td> 
										<a href="WebContent/dir_ftp/dir_public/<% out.print(directorioPubl[i]); %>" download>
											<% out.print(directorioPubl[i]); %>
										</a>
									</td> 
									</tr> 
								<% } %>
							</table>
							</br/>
						</div>
					</div>
			
					<div class="row">
						<div class="col-xl-12">
							<h4> <b> SUBIR FICHERO </b> <i>(A directorio público)</i> </h4>  <hr/>
								<div class="row">
									<div class="col-xl-12 botonPrincipal">
										<form action="subidaPubl" enctype="multipart/form-data" method="post"> 
											<b>Fichero:</b>
											<input type="file" name="fichero" class="btn btn-sm text-muted"/></br><br/>
											<input type="submit" value="Subir a Directorio Público" class="btn btn-md btn-primary"/> 
										</form> 
									</div>
								</div>
						</div>
					</div>
			
				</div>
		  </div>
	</div>
</div>

<script type="text/javascript" src="js/jquery-3.1.1.min" />
<script type="text/javascript" src="js/bootstrap.min.js" />			
</body>
</html>

<% } %>