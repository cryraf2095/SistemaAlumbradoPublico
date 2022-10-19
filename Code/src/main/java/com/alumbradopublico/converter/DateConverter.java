package com.alumbradopublico.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

	private final String FORMAT_1 = "yyyy-MM-dd";
	
	public LocalDate convertToDate(String olddate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FORMAT_1);
		LocalDate dateFormatted = LocalDate.parse(olddate, dtf);
		return dateFormatted;
	}
}
