package com.alumbradopublico.model;

import java.time.LocalDate;

public class Employee {
	
	private int code;
	private long DPI;
	private String name;
	private LocalDate dateOfBirth;
	private String phone;
	private String email;
	private String password;
	private boolean status;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getDPI() {
		return DPI;
	}

	public void setDPI(long dPI) {
		DPI = dPI;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [code=" + code + ", DPI=" + DPI + ", name=" + name + ", email=" + email + ", dateOfBirth="
				+ dateOfBirth + ", password=" + password + ", status=" + status + "]";
	}
	
}
