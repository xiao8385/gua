package com.gua.homework.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoiExcelObj {

	private List<String> sheetList = new ArrayList<String>();
	private Map<String, List<String[]>> sheetMap = new HashMap<String, List<String[]>>();
	private String fileName = "";
	private int fontSize;
	

	public PoiExcelObj(String file) {
		this.fileName = file;
	}

	public boolean add(String sheet, List<String[]> content) {
		
		if (sheetList.contains(sheet)) {
			return false;
		}
		
		this.sheetList.add(sheet);
		this.sheetMap.put(sheet, content);
		
		return true;
	}
	
	public List<String> getSheetList() {
		return sheetList;
	}
	
	public List<String[]> getSheetContent(String sheet) {
		return this.sheetMap.get(sheet);
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public boolean isEmpty() {
		return this.sheetList.isEmpty();
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
}
