<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

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
	width:30%;
}

.datos {
	background-color:#FFAAAA;
	width:70%;
}

</style>

<title>Grupos Musicales - Actualizar grupo musical</title>
</head>
<body>
	<h1>Grupos Musicales</h1>

	<div class="marco">

		<h3>Actualizar grupo musical</h3>

		<form action="ServletEmisora" method="get" >
			<input type="hidden" name="accion" value="actualizar">

			<table class="tabla"  align="center">
				<tr>
					<td class="cabecera"><label for="id">Id del grupo: </label></td>
					<td class="datos">${grupoModificar.id}
					<input type="hidden" name="idGrupo" id="id" value="${grupoModificar.id}"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="nombre">Nombre del grupo: </label></td>
					<td class="datos"><input type="text" name="nombre" id="nombre" value="${grupoModificar.nombre}"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="creacion">Año creación: </label></td>
					<td class="datos"><input type="text" name="creacion" id="creacion" value="${grupoModificar.creacion}"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="origen">Origen: </label></td>
					<td class="datos"><input type="text" name="origen" id="origen" value="${grupoModificar.origen}"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="genero">Género: </label></td>
					<td class="datos"><input type="text" name="genero" id="genero" value="${grupoModificar.genero}"> </td>
				</tr>

				<tr>
					<td colspan="2" align="center">
					<input type="submit"	name="guardar" id="guardar" value="Guardar">
					<input type="reset" name="borrar" id="borrar" value="Cancelar" onclick="window.location.href='ServletEmisora?accion=cargar'"/>
					</td>
				</tr>

			</table>

		</form>

	</div>
</body>
</html>
