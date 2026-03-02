<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Grupos Musicales - Detalle</title>

<style>
body {
	background-color: #D46A6A;
}

h1 {
	font-weight:bold;
	text-align:center;
}
h3 {
	font-weight:bold;
	text-align:center;
	margin-top: 30px;  
    margin-bottom: 10px;
}

.marco {
	border: 1pt solid #fbb; 
	width:100%;
	padding-bottom: 20px;
}

.tabla {
	border: 1pt solid;
	width:50%;
}

.cabecera {
	background-color:#801515;
	font-weight:bold;
}

.datos {
	background-color:#FFAAAA;
	padding: 5px;
}

.tabla-volver {
    margin-top: 60px;    
    margin-bottom: 30px;
    width: 100%;
}
</style>

</head>
<body>
<h1> Grupos Musicales</h1>

	<div class="marco">
		
		<h3>Detalle del grupo</h3>
		
		<table class="tabla" align="center">
			<tr>
				<td class="cabecera" width="50%">Id del grupo:</td>
				<td class="datos" width="50%">${detalleGrupo.id}</td>
			</tr>
			<tr>
				<td class="cabecera">Nombre del grupo:</td>
				<td class="datos">${detalleGrupo.nombre}</td>
			</tr>
			<tr>
				<td class="cabecera">Año de creación del grupo:</td>
				<td class="datos">${detalleGrupo.creacion}</td>
			</tr>
			<tr>
				<td class="cabecera">Lugar de origen del grupo:</td>
				<td class="datos">${detalleGrupo.origen}</td>
			</tr>
			<tr>
				<td class="cabecera">Género musical del grupo:</td>
				<td class="datos">${detalleGrupo.genero}</td>
			</tr>
		</table>
		
		<h3>Componentes del grupo</h3>
		
		<table class="tabla" align="center">
			<tr>
				<td class="cabecera" width="50%">Nombre</td>
				<td class="cabecera">Instrumento</td>
			</tr>
			<c:forEach var="tempComponente" items="${detalleGrupo.componentes}">
			<tr>
				<td class="datos" width="50%">${tempComponente.nombre}</td>
				<td class="datos">${tempComponente.instrumento}</td>	
			</tr>
			</c:forEach>
		</table>
		<br>
		<table class="tabla-volver" align="center">
			<tr> 
				<td align="center"><a href="ServletEmisora?accion=cargar">Volver al listado</a></td>
			</tr>
		</table>
		<br>
	</div>

</body>
</html>