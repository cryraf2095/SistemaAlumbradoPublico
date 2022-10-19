package com.alumbradopublico.servlet;

import java.io.IOException;

import com.alumbradopublico.converter.DateConverter;
import com.alumbradopublico.db.EmployeeDB;
import com.alumbradopublico.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registerEmployee")
public class RegisterEmployeeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Employee loggedEmployee = (Employee) session.getAttribute("loggedEmployee");
		if (loggedEmployee==null) {
			req.setAttribute("response", "Debe iniciar sesión para realizar esta operación");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("registerEmployee.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateConverter dateConverter = new DateConverter();
		String response = "";
		String dpi = req.getParameter("DPI");
		String name = req.getParameter("name");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		Employee newEmployee = new Employee();
		newEmployee.setDPI(Long.parseLong(dpi));
		newEmployee.setName(name);
		newEmployee.setDateOfBirth(dateConverter.convertToDate(dateOfBirth));
		newEmployee.setPhone(phone);
		newEmployee.setEmail(email);
		EmployeeDB employeeDb = new EmployeeDB();
		response = employeeDb.registerEmployee(newEmployee);
		req.setAttribute("response", response);
		req.getRequestDispatcher("registerEmployee.jsp").forward(req, resp);	
	}
	
}
