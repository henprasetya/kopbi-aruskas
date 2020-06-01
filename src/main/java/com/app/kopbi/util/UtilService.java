package com.app.kopbi.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class UtilService {
	
	public static String ddMMMyyyy_HHmmss = "dd MMM yyyy HH:mm:ss";
	
	public LocalDateTime toLocalDateTime(String timestamp) {
		//TODO: ada cara yg lebih elegant gak daripada bikin formatter sendiri?
		final DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
		LocalDateTime result = LocalDateTime.from(formatter.parse(timestamp));
		return result;
	}
	
	public LocalDateTime toLocalDateTime(LocalDateTime ldt) {
		LocalDateTime LocalDateTime = ldt.atZone(ZoneId.systemDefault()).toLocalDateTime();
		return LocalDateTime;
	}
	
	public Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}

	public String toStringFormat(LocalDateTime date, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return date.format(formatter);
	}
}
