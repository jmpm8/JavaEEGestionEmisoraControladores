<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%--Importamos librerias JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Grupos Musicales</title>

<style>
body {
	background-color: #D46A6A;
}

h1 {
	font-weight:bold;
	text-align:center;
}

.marco {
	border: 1pt solid #fbb; 
	width:100%;
}

.tabla {
	border: 1pt solid;
	width:50%;
}

.cabecera {
	font-weight:bold;
	background-color:#801515;
}

.datos {
	background-color:#FFAAAA;
}

.contenedor-boton {
    display: flex;
    justify-content: center; 
    margin-bottom: 20px;    
}
	
</style>

</head>
<body>
<h1>Grupos Musicales</h1>

<div class="marco">
	<br><br>
	<table class="tabla" align="center">
		<tr class="cabecera">
			<td>Id</td>
 			<td>Grupo</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	<c:forEach var="grupo" items="${gruposMusicales}">	
	<tr class="datos">
	<td>${grupo.id}</td> 
	<td>${grupo.nombre}</td>
	<%-- Enlace Ver más (Acción: detalle) --%>
	<td><a href="ServletEmisora?accion=detalle&idGrupo=${grupo.id}">Ver más...</a></td>
	<%-- Enlace Modificar (Acción: modificar) --%>
	<td><a href="ServletEmisora?accion=modificar&idGrupo=${grupo.id}">Modificar</a></td>
	<%-- Enlace Eliminar (Acción: baja) --%>
	<td><a href="ServletEmisora?accion=baja&idGrupo=${grupo.id}" onclick="return confirm('¿Seguro que quieres eliminar este grupo?')">Eliminar</a></td>
	</tr>
	</c:forEach>
	</table>
	<br>
	<br>
	<div class="contenedor-boton">
		<input type="button" value="Añadir grupo" onclick="window.location.href='NuevoGrupo.jsp'"/>
	</div>
</div>

 
</body>
</html>