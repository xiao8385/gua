package com.gua.homework.common.util;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringUtilTest {

	final static Logger logger = LoggerFactory.getLogger(StringUtilTest.class);
	@Test
	public void testTransStrToList() {
		String s = "1" + Constant.SEPERATOR + "2" + Constant.SEPERATOR + "3";
		List<String> l = StringUtil.transStrToList(s);

		List<String> l2 = new ArrayList<String>();
		l2.add("1");
		l2.add("2");
		l2.add("3");

		assertEquals(l, l2);
	}

	@Test
	public void testTransArrToStr() {
		String[] a = { "1", "2", "3" };
		String b = "1" + Constant.SEPERATOR + "2" + Constant.SEPERATOR + "3";
		String a1 = StringUtil.transArrToStr(a);
		assertEquals(b, a1);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testTransStrToArr() {
		String[] a = { "1", "2", "3" };
		String b = "1" + Constant.SEPERATOR + "2" + Constant.SEPERATOR + "3";

		assertEquals(StringUtil.transStrToArr(b), a);
	}

	@Test
	public void testCutValue_Normal() {
		
		String str = "·¿¹À×Ö(2014)µÚYPQD0006ºÅ";
		String a1 = StringUtil.cutValue(str, "µÚ", "ºÅ");
		assertEquals("YPQD0006", a1);
	}
	
	
	
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testTransListArrToArrArr() {
//		
//		List<String[]> l = new ArrayList<String[]>();
//		String[] a1  = {"1", "2", "3"};
//		String[] a2  = {"a", "b", "c"};
//		l.add(a1);l.add(a2);
//		
//		String[][] arr = {{"1", "2", "3"},{"a", "b", "c"}};
//		assertEquals(arr, StringUtil.transListArrToArrArr(l));
//	}
	
	
    /*
    [FORMAT]    ARRIVE: TERMINAL [{terminal}-{port}]
    [E.G.  ]    ARRIVE: TERMINAL [North Butterworth Container Terminal(NBCT)-MYPEN]
                LATEST_EVENT_TYPE_C : ARRIVE
                LATEST_EVENT_AREA_C : null
                LATEST_EVENT_PORT_C : MYPEN
                TERMINAL : North Butterworth Container Terminal(NBCT)
    
    [FORMAT]    DEPART: {area} [{port}]
    [E.G.  ]    DEPART: WAITING AREA [CNSHK]
                LATEST_EVENT_TYPE_C : DEPART
                LATEST_EVENT_AREA_C  :WAITING AREA
                LATEST_EVENT_PORT_C : CNSHK
                TERMINAL : null
    */
	public static void PnLastEvent(String latestEvent) {
		
		logger.debug("=== latestEvent:'{}'", latestEvent);
		
        if (latestEvent == null || latestEvent.trim().length() == 0) {
            return;
        }
        
        int typeCIndex = latestEvent.indexOf(":");
        if (typeCIndex >= 0) {
            String typeC = latestEvent.substring(0, typeCIndex).trim();
            logger.debug(" = typeC:'{}'", typeC);

            int areaCIndex = latestEvent.indexOf("[");
            if (areaCIndex >= 0 && areaCIndex > typeCIndex) {
                int terminalIndex = latestEvent.indexOf("-");
                if (terminalIndex >= 0 && terminalIndex > areaCIndex) {
                    String terminalC = latestEvent.substring(areaCIndex + 1, terminalIndex).trim();
                    logger.debug(" = terminalC:'{}'", terminalC);
                } else {
                    String areaC = latestEvent.substring(typeCIndex + 1, areaCIndex).trim();
                    logger.debug(" = areaC:'{}'", areaC);
                }

                int portCIndex = latestEvent.indexOf("]");
                String portC = null;
                if (portCIndex >= 0 && portCIndex > areaCIndex) {
                    if (terminalIndex >= 0 && terminalIndex > areaCIndex) {
                        portC = latestEvent.substring(terminalIndex + 1, portCIndex).trim();
                    } else {
                        portC = latestEvent.substring(areaCIndex + 1, portCIndex).trim();
                    }
                }
                logger.debug(" = portC:'{}'", portC);
            }
        }
    }
	
	
	@Test
	public void testPnFunction() {
		
		String[] latestEventArr = { "ARRIVE: TERMINAL [North Butterworth Container Terminal(NBCT)-MYPEN]",
				"DEPART: TERMINAL [Nansha Terminal Phase II (GOCT)-CNNSA]", "ARRIVE: TERMINAL [Patrick Terminal-AUFRE]",
				"DEPART: PORT AREA [CNSHK]", "ARRIVE: WAITING AREA [AEJEA]", "DEPART: WAITING AREA [EGSOK]", null, "" };


		for (String item: latestEventArr) {
			
			PnLastEvent(item);
		}
	}

	public static Integer formatWeight(String Q) {

		if (Q == null || Q.trim().length() == 0) {
			return null;
		}

		try {
			String lastChar = "" + Q.charAt(Q.length() - 1);
			double valueDouble = Double.parseDouble(Q.substring(0, Q.length() - 1));
			NumberFormat NF = NumberFormat.getInstance();
			DecimalFormat df = (DecimalFormat) NF;
			df.setMaximumIntegerDigits(7);
			String pattern = "#######";
			df.applyPattern(pattern);
			df.setDecimalSeparatorAlwaysShown(false);

			if (lastChar.equalsIgnoreCase("K")) {
				if (valueDouble > 9999999) {
					return null;// Constants.ERROR_INDICATOR;
				} else {
					return Integer.valueOf(df.format(valueDouble));
				}
			} else if (lastChar.equalsIgnoreCase("L")) {
				double convertL = valueDouble * (0.45);
				if (convertL > 9999999) {
					return null;// Constants.ERROR_INDICATOR;
				} else {
					return Integer.valueOf(df.format(convertL));
				}

			} else {
				return null;// Constants.ERROR_INDICATOR;
			}
		} catch (Exception e) {
			return null;// Constants.EXCEPTION_INDICATOR;
		}
	}
	

	@Test
	public void testFormatWeight() {
		
		String[] weightQArr = { "45k",
				"123456k", "1234567k", "12345678k",
				"0001234k", "0012345k","0123456k", "01234567k", "001234567k", "012345678k",
				"4500l", "4500", null, "", "123456", "1234567", "12345678", "123456789"};
		
		for (int i=0; i<weightQArr.length; i++) {
			logger.debug("formatWeight: fr:'{}' to:'{}'", weightQArr[i], formatWeight(weightQArr[i]));
		}
	}
}
