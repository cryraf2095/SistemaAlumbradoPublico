package com.alumbradopublico.servlet;

import java.io.IOException;

import com.alumbradopublico.db.EmployeeDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/activateAccount")
public class ActivateAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailToActivate = req.getParameter("email");
		EmployeeDB employeeDb = new EmployeeDB();
		employeeDb.activateAccount(emailToActivate);
		req.setAttribute("response", "Cuenta activada de forma exitosa");
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	
}
