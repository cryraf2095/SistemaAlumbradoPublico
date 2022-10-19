package com.alumbradopublico.servlet;

import java.io.IOException;

import com.alumbradopublico.db.EmployeeDB;
import com.alumbradopublico.encrypt.EncryptUtil;
import com.alumbradopublico.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String password = req.getParameter("password");
		EmployeeDB employeeDB = new EmployeeDB();
		Employee employee = employeeDB.loginEmployee(user);
		String response = "";
		String pageToRedirect="";
		if (employee == null) {
			response = "Usuario no existe";
			pageToRedirect="home.jsp";
		} else if (!EncryptUtil.decode(employee.getPassword()).equals(password)) {
			response = "Contraseña incorrecta";
			pageToRedirect="home.jsp";
		} else if (!employee.isStatus()) {
			response = "Usuario no esta activo, activar desde email";
			pageToRedirect="home.jsp";
		} else {
			response = "Acceso Autorizado";
			HttpSession session = req.getSession();
			session.setAttribute("loggedEmployee", employee);
			pageToRedirect = "index.jsp";
		}
		req.setAttribute("response", response);
		req.getRequestDispatcher(pageToRedirect).forward(req, resp);
	}
	
}
