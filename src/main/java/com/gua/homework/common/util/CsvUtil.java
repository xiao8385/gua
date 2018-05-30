package com.gua.homework.common.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvUtil {

	final static Logger logger = LoggerFactory.getLogger(CsvUtil.class);

	public static List<String[]> readFromFile(String fileName) {
		List<String[]> l = new ArrayList<String[]>();

		logger.debug("readFromFile:{}", fileName);

		try {

			CsvReader csv = new CsvReader(
					FileUtil.readMessageFromFile(fileName, Constant.ENCODING_CSV));

			while (csv.readRecord()) {

				String line = csv.getRawRecord();
				l.add(StringUtil.transStrToArr(line));
				logger.debug(line);
			}
			
			csv.close();

		} catch (IOException e) {
			logger.error("Read CSV file:" + fileName + " encounter error", e);
		}

		return l;
	}

	public static void writeToFile(List<String[]> line, String fileName) {

		logger.debug("writeToFile:{}", fileName);

		if (line == null || line.isEmpty()) {
			logger.debug("No file created!");
			return;
		}

		CsvWriter csv = null;

		try {
			csv = new CsvWriter(fileName, Constant.COMMA,
					Charset.forName(Constant.ENCODING_CSV));
			//csv.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
			for (String[] l : line) {
				csv.writeRecord(l);
				logger.debug(StringUtil.transArrToStr(l));
			}
		} catch (IOException e) {
			logger.error("Write CSV file:" + fileName + " encounter error", e);
		} finally {
			csv.close();
		}
	}
}
