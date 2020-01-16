package com.indianeagle.internal.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for date formatting
 * User: nageswaramma
 * Date: 1/17/13
 * Time: 6:37 PM
 */
public class DateFormatter {

	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();
	public static final String DD_MMM_YYYY = "dd MMM, yyyy";

	/**
	 * This method returns the date in the format provided
	 *
	 * @param date        the date to be formatted
	 * @param datePattern the pattern in which the date needs to be formatted
	 * @return the date in String representation
	 */
	public static String format(Date date, String datePattern) {
		if (date != null && (datePattern) != null && !"".equals(datePattern.trim())) {
			SimpleDateFormat dateFormat = (SimpleDateFormat) SIMPLE_DATE_FORMAT.clone();
			dateFormat.applyPattern(datePattern);
			return dateFormat.format(date);
		}
		return null;
	}

	public static String getMonth(Date date) {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SIMPLE_DATE_FORMAT.clone();
			simpleDateFormat.applyPattern("MMM");
			return simpleDateFormat.format(date);
		}
		return null;
	}

	public static String currentDate(String datePattern) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SIMPLE_DATE_FORMAT.clone();
		simpleDateFormat.applyPattern(datePattern);
		return simpleDateFormat.format(date);
	}
}
