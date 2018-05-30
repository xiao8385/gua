package com.gua.homework.math.oral;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gua.homework.common.util.FileUtil;
import com.gua.homework.common.util.PoiUtil;
import com.gua.homework.math.HomeWork;
import com.gua.homework.obj.PoiExcelObj;

public class OralCalculationGenerator {

	final static Logger logger = LoggerFactory.getLogger(OralCalculationGenerator.class);

	public final static int MAX_COLUMN = 2;

	// random to choose formula + / -
	private String random() {

		Random r = new Random();
		int i = r.nextInt(2);
		int k = i;
		if (i <= 0) {
			k = 1;
		}
		if (i >= 1) {
			k = 2;
		}

		// logger.debug("i={} k={}", i, k);

		if (k == 1)
			return HomeWork.generateAddFormula(HomeWork.DIGIT, HomeWork.DIGIT);
		if (k == 2)
			return HomeWork.generateSubFormula(HomeWork.DIGIT, HomeWork.DIGIT);
		return null;
	}

	// create a list of formula with specified numbers
	public List<String[]> creatFormulaList(int count) {

		List<String[]> rList = new ArrayList<String[]>();
		for (int i = 0; i < count; i++) {

			String line = "(" + (i + 1) + "). " + this.random();
			logger.info(line);
			String[] l = { line };
			rList.add(l);

			// logger.info("({}). {}", i+1, this.random());
		}

		return rList;
	}

	// Organise to fill column up to max, and leave separate blank column 
	public List<String[]> organizeColumnForPrint(List<String[]> list, int blankColumnCount) {

		if (list == null || list.isEmpty()) {
			return new ArrayList<String[]>();
		}

		List<String> ll = new ArrayList<String>();
		for (String[] sArr : list) {

			if (sArr == null) {
				continue;
			}
			// logger.debug("sArr:{}", StringUtil.transArrToStr(sArr));

			for (String s : sArr) {
				ll.add(s);
			}
		}

		// logger.debug("ll:{}", ll);

		List<String[]> rList = new ArrayList<String[]>();
		for (int j = 0; j < ll.size();) {
			String[] arr = new String[MAX_COLUMN + MAX_COLUMN * blankColumnCount];
			for (int i = 0, k = 0; i < MAX_COLUMN; i++) {

				if (j == ll.size()) {
					break;
				}
				arr[k++] = ll.get(j++);
				for (int g = 0; g < blankColumnCount; g++) {
					arr[k++] = "____";
				}
				// logger.debug("i:{}, j:{}, k:{}, arr:{}", i, j, k,
				// StringUtil.transArrToStr(arr));
			}
			rList.add(arr);
		}

		return rList;
	}

	// Fill separate blank row 
	public List<String[]> organizeRowForPrint(List<String[]> list, int blankRowCount) {

		if (list == null || list.isEmpty()) {
			return new ArrayList<String[]>();
		}

		List<String[]> ll = new ArrayList<String[]>();
		for (String[] sArr : list) {

			if (sArr == null) {
				continue;
			}
			// logger.debug("sArr:{}", StringUtil.transArrToStr(sArr));
			
			ll.add(sArr);
			
			for (int i=0; i<blankRowCount; i++) {
				ll.add(new String[1]);
			}

		}
		return ll;
	}

	// 5 vertical formula + keep 2 blank rows separate  
	// 40 oral formula
	// keep 2 formula each row
	public void createExcelFile(String fileName) {

		PoiExcelObj p = new PoiExcelObj(FileUtil.OnlyForTest_generateFileFullPath(fileName));
		//
		// p.add("竖式并验算", this.creatFormulaList(5));
		// p.add("口算", this.creatFormulaList(40));

		List<String[]> input = new ArrayList<String[]>();

		// 5道竖式提并且验算
		String[] title1 = { "竖式并验算" };
		input.add(title1);
		input.add(new String[1]);
		List<String[]> l1 = this.organizeColumnForPrint(this.creatFormulaList(5), 2);
		input.addAll(this.organizeRowForPrint(l1, 3));

		// 2新行分隔
		input.add(new String[1]);

		// 40道口算题
		String[] title2 = { "口算" };
		input.add(title2);
		input.add(new String[1]);
		input.addAll(this.organizeColumnForPrint(this.creatFormulaList(40), 2));

		p.add("test", input);
		p.setFontSize(16);
		PoiUtil.writeToExcelFile(p);
	}

	public static void main(String[] args) {
	}
}
