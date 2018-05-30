package com.gua.homework.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static final int SECONDS_PER_MINUTE = 60;
	private static final int MINUTES_PER_HOUR = 60;
	private static final int HOURS_PER_DAY = 24;
	private static final int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
	/**
	 * 一天的毫秒数
	 **/
	private static final long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;

	public static Date strToDate(String s) {

		if (s == null || s.trim().length() == 0) {
			return null;
		}

		try {
			return new SimpleDateFormat(Constant.DATE_FORMAT).parse(s);
		} catch (ParseException e) {

			Date d = toDate(s);
			if (d != null) {
				return d;
			}

			logger.debug("Format date value:" + s + " encounter error");
			return null;
		}
	}

	public static Date strToDate(String s, String format) {

		if (s == null || s.trim().length() == 0) {
			return null;
		}

		try {
			return new SimpleDateFormat(format).parse(s);
		} catch (ParseException e) {

			logger.debug("Format:{} date:{} encounter error", format, s);
			return null;
		}
	}

	public static String dateToStr(Date d) {
		return dateToStr(d, Constant.DATE_FORMAT);
	}

	public static String dateToStr(Date d, String format) {

		if (d == null) {
			return "";
		} else {
			return new SimpleDateFormat(format).format(d);
		}
	}

	public static Date toDate(String d) {

		if (StringUtil.isNullOrEmpty(d)) {
			return null;
		}

		double numberString = 0;
		try {
			numberString = new Double(d);
		} catch (NumberFormatException e) {
			logger.debug("Trans string:{} to date encounter error", d);
			return null;
		}
		int wholeDays = (int) Math.floor(numberString);
		int millisecondsInday = (int) ((numberString - wholeDays) * DAY_MILLISECONDS + 0.5);
		Calendar calendar = new GregorianCalendar();
		setCalendar(calendar, wholeDays, millisecondsInday, false);
		return calendar.getTime();
	}

	public static Date toDate(double numberString) {

		int wholeDays = (int) Math.floor(numberString);
		int millisecondsInday = (int) ((numberString - wholeDays) * DAY_MILLISECONDS + 0.5);
		Calendar calendar = new GregorianCalendar();
		setCalendar(calendar, wholeDays, millisecondsInday, false);
		return calendar.getTime();
	}

	private static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay, boolean use1904windowing) {
		int startYear = 1900;
		int dayAdjust = -1; // Excel thinks 2/29/1900 is a valid date, which it
							// isn't
		if (use1904windowing) {
			startYear = 1904;
			dayAdjust = 1; // 1904 date windowing uses 1/2/1904 as the first day
		} else if (wholeDays < 61) {
			// Date is prior to 3/1/1900, so adjust because Excel thinks
			// 2/29/1900 exists
			// If Excel date == 2/29/1900, will become 3/1/1900 in Java
			// representation
			dayAdjust = 0;
		}
		calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
		calendar.set(GregorianCalendar.MILLISECOND, millisecondsInDay);
	}

	public static Date addDay(Date d, int addValue) {

		if (d == null) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, addValue);
		Date addD = c.getTime();

		return addD;
	}

	public static Date getPreMonthFirstDay() {

		Calendar c = Calendar.getInstance();// 获取当前日期
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	public static Date getPreMonthLastDay() {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	public static Date getCurrMonthFirstDay() {

		Calendar c = Calendar.getInstance();// 获取当前日期
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	public static Date getCurrMonthLastDay() {

		Calendar c = Calendar.getInstance();// 获取当前日期
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}
}
