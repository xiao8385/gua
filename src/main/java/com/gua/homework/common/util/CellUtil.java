package com.gua.homework.common.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellUtil {

	final static Logger logger = LoggerFactory.getLogger(CellUtil.class);

	public static BigDecimal setIntValue(String s) {
		BigDecimal b = new BigDecimal("0");
		if (s != null && s.trim().length() > 0) {
			try {
				b = new BigDecimal(s);
			} catch (NumberFormatException e) {
				logger.debug("Format int value:" + s + " encounter error");
			}
		}
		return b;
	}

	public static boolean setBooleanValue(String s) {
		if (s != null && s.trim().length() > 0) {
			return "YES".equalsIgnoreCase(s);
		}
		return false;
	}
}
