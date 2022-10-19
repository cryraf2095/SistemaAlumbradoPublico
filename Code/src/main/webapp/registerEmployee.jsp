<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Empleado</title>
</head>
<body>
<h1>Registrar Empleado</h1>
<label> ${response}</label>
<form action="registerEmployee" method="post">
	<table>
		<tr>
			<td><label>DPI</label></td>
			<td><input type="text" name="DPI"></td>
		</tr>
		<tr>
			<td><label>Nombre</label></td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td><label>Fecha de Nacimiento</label></td>
			<td><input type="date" name="dateOfBirth">
		</tr>
		<tr>
			<td><label>Teléfono</label></td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td><label>Email</label></td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td></td>
			<td><button type="submit">Registrar Empleado</button></td>
		</tr>
	</table>
</form>
</body>
</html>