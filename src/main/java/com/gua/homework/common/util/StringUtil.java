package com.gua.homework.common.util;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {

	final static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	public static List<String> transStrToList(String s) {
		return Arrays.asList(transStrToArr(s));
	}

	public static String transArrToStr(String[] a) {

		if (a == null)
			return "";

		StringBuffer b = new StringBuffer();
		for (int i = 0; i < a.length; i++) {
			b.append(a[i]);
			if (i < a.length - 1)
				b.append(Constant.SEPERATOR);
		}
		return b.toString();
	}

	public static String[] transStrToArr(String s) {
		return s.split("" + Constant.SEPERATOR);
	}

	public static String cutValue(String s, String beginS, String endS) {

		return (s == null || s.trim().length()==0 || s.indexOf(beginS) < 0) ? ""
				: s.substring(s.indexOf(beginS) + beginS.length(), s.indexOf(endS));
	}

	public static boolean hasValue(String s) {
		return s != null && s.trim().length()>0;
	}
	
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.trim().length()==0;
	}
}
