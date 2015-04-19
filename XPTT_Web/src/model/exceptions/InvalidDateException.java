package model.exceptions;

import java.util.GregorianCalendar;

public class InvalidDateException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDateException(GregorianCalendar invalidDate) {
		super("" + invalidDate.get(GregorianCalendar.DATE) + "/"
				+ (invalidDate.get(GregorianCalendar.MONTH) + 1) + "/"
				+  invalidDate.get(GregorianCalendar.YEAR)+ " "
				+ invalidDate.get(GregorianCalendar.HOUR_OF_DAY) + ":"
				+ invalidDate.get(GregorianCalendar.MINUTE)
				+ " is not a valid date.");
	}

}
