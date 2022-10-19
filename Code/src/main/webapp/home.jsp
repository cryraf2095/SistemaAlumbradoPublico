<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema Alumbrado Público</title>
</head>
<body>
<h1>Sistema Alumbrado Público</h1>
<label> ${response}</label>
<form action="login" method="post">
<table>
	<tr>
		<td><label>Usuario</label></td>
		<td><input type="text" name = "user"></td>
	</tr>
	<tr>
		<td><label>Contraseña</label></td>
		<td><input type="password" name = "password"></td>
	</tr>
	<tr>
		<td/>
		<td><button type="submit">Ingresar</button></td>
	</tr>
</table>
</form>
</body>
</html>