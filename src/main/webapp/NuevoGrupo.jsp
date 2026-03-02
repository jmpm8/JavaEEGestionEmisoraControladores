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

<title>Grupos Musicales - Nuevo grupo musical</title>
</head>
<body>
	<h1>Grupos Musicales</h1>

	<div class="marco">

		<h3>Nuevo grupo musical</h3>

		<form action="ServletEmisora" method="get">
		<input type="hidden" name="accion" value="alta">
		
			<table class="tabla"  align="center">
				<tr>
					<td class="cabecera"><label for="nombre">Nombre del grupo: </label></td>
					<td class="datos"><input type="text" name="nombre" id="nombre"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="creacion">Año creación: </label></td>
					<td class="datos"><input type="text" name="creacion" id="creacion"></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="origen">Origen: </label></td>
					<td class="datos"><select name="origen" id="origen">
							<option>Estados Unidos</option>
							<option>Reino Unido</option>
							<option>Australia</option>
							<option>España</option>
							<option>Alemania</option>
					</select></td>
				</tr>
				<tr>
					<td class="cabecera"><label for="genero">Género: </label></td>
					<td class="datos"><select name="genero" id="genero">
						<option>Heavy Metal</option>
						<option>Hard rock</option>
						<option>Rock</option>
						<option>Folk metal</option>
						<option>Rock andaluz</option>
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
					<input type="submit" name="guardar" id="guardar" value="Guardar">
					<input type="button" name="borrar" id="borrar" value="Cancelar" onclick="window.location.href='ServletEmisora?accion=cargar'" />
					</td>
				</tr>

			</table>
			<br><br>
		</form>

	</div>
</body>
</html>
