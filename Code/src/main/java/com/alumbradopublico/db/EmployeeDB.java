package com.alumbradopublico.db;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.alumbradopublico.connection.SQLServerConnection;
import com.alumbradopublico.encrypt.EncryptUtil;
import com.alumbradopublico.mail.EmailService;
import com.alumbradopublico.model.Employee;

public class EmployeeDB {
	
	private SQLServerConnection sqlServerConnection = new SQLServerConnection(); 
	private EmailService emailService = new EmailService();
	
	public String registerEmployee(Employee newEmployee) {
		String message = "";
		newEmployee.setPassword(generateSecurePassword());
		try {
			Connection connection = sqlServerConnection.getConnection();
			String sqlInsertEmployee = "INSERT INTO EMPLOYEE (DPI, name, dateOfBirth, phone, email, password, status) \n"
					+ "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sqlInsertEmployee);
			statement.setLong(1, newEmployee.getDPI());
			statement.setString(2, newEmployee.getName());
			statement.setDate(3, Date.valueOf(newEmployee.getDateOfBirth()));
			statement.setString(4, newEmployee.getPhone());
			statement.setString(5, newEmployee.getEmail());
			statement.setString(6, EncryptUtil.encode(newEmployee.getPassword()));
			statement.setBoolean(7, false);
			statement.executeUpdate();
			emailService.sendMailRegisterEmployee(newEmployee);
			message += "Empleado registrado, activar usuario desde email";
		} catch ( SQLException e) {
			message += e.getMessage();
			e.printStackTrace();
		}
		return message;
	}
	
	public void activateAccount(String emailToActive) {
		try {
			Connection connection = sqlServerConnection.getConnection();
			String sqlUpdate = "UPDATE EMPLOYEE SET status = 1 WHERE email = '" + emailToActive + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			
		}
	}
	
	public Employee loginEmployee(String userEmployee) {
		Employee employee = null;
		try {
			Connection connection = sqlServerConnection.getConnection();
			String sqlGetEmployee = "Select email, password, status FROM EMPLOYEE WHERE email='" + userEmployee + "';";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sqlGetEmployee);
			while (rs.next()) {
				employee = new Employee();
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setStatus(rs.getBoolean("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
	private static String generateSecurePassword() {  
		Stream<Character> demoPassword = Stream.concat(getRandomNumbers(2),   
                 Stream.concat(getRandomSpecialChars(2),   
                 Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(2, false))));    
        List<Character> listOfChar = demoPassword.collect(Collectors.toList());  
        Collections.shuffle(listOfChar);    
        String password = listOfChar.stream()  
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)  
                .toString();     
        return password;  
    }  
      
    private static Stream<Character> getRandomSpecialChars(int length) {        
        Stream<Character> specialCharsStream;  
        Random random = new SecureRandom();    
        IntStream specialChars = random.ints(length, 33, 45);  
        specialCharsStream =  specialChars.mapToObj(data -> (char) data);    
        return specialCharsStream;  
    }  
        
    private static Stream<Character> getRandomNumbers(int length) {   
        Stream<Character> numberStream;  
        Random random = new SecureRandom();  
        IntStream numberIntStream = random.ints(length, 48, 57);  
        numberStream = numberIntStream.mapToObj(data -> (char) data);  
        return numberStream;  
    }  
    
    private static Stream<Character> getRandomAlphabets(int length, boolean check) {  
        Stream<Character> lowerUpperStream;  
        if(check == true) {  
            Random random = new SecureRandom();  
            IntStream lCaseStream = random.ints(length, 'a', 'z');  
            lowerUpperStream =  lCaseStream.mapToObj(data -> (char) data);  
        }  
        else {  
            Random random = new SecureRandom();  
            IntStream uCaseStream = random.ints(length, 'A', 'Z');  
            lowerUpperStream =  uCaseStream.mapToObj(data -> (char) data);  
        }  
        return lowerUpperStream;        
    }  
}
