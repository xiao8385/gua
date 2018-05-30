package com.gua.homework.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarningUtil {

	final static Logger logger = LoggerFactory.getLogger(WarningUtil.class);

	private static List<String> warningInfoList = new ArrayList<String>();
	private final static String LABEL = "&";
	
	public static String packingErrorMsg(String msg, String[] paraArr) {
		
		if (msg!=null && msg.trim().length()>0 && paraArr!=null) {
			for(int i=0; i<paraArr.length; i++) {
				msg = msg.replace(LABEL+(i+1), paraArr[i]);
			}
		}
		
		return msg;
	}

	public static void showMsg(String errorCode, String[] parameterArr) {

		String msg = "[WARNING MESSAGE]:" + ConfigUtil.getValue(errorCode) + "";
		
		msg = packingErrorMsg(msg, parameterArr);
		
		logger.warn(msg);
		warningInfoList.add(msg);
	}
	
	public static void showMsg(String errorCode) {
		
		showMsg(errorCode, null);
	}

	public static void printAllMsg() {
		if (!warningInfoList.isEmpty()) {
			
			int i=0;
			logger.warn("[WARNING MESSAGE] total:{}", warningInfoList.size());
			for (String msg : warningInfoList) {
				logger.warn("{}. {}", ++i, msg);
			}
		}
	}
}
