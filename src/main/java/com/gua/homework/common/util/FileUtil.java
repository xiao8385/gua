package com.gua.homework.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static boolean createFolder(String name) {

		File theDir = new File(name);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			logger.debug("Creating directory: {}...", name);

			try {
				theDir.mkdirs();
				logger.debug("Directory created!");
			} catch (Exception e) {
				logger.error("Failed create directory!", e);
				return false;
			}
		}
		return true;
	}

	public static Collection<File> listFiles(String path) {

		return null;
	}

	public static boolean copyFileToFile(String fileName, String dest) {

		logger.debug("Copying file:{}...", fileName, dest);
		try {
			// FileUtils.copyFileToDirectory(new File(fileName), new File(dest),
			// true);
			FileUtils.copyFile(new File(fileName), new File(dest), true);
			logger.debug("File copyed to : {}", dest);
			logger.debug("File copyed!");
		} catch (IOException e) {
			logger.error("Failed copy file!", e);
			return false;
		}
		return true;
	}

	public static boolean copyFileToDirectory(String source, String dest, String timeStamp) {

		return copyFileToFile(source, fileNameAppend(
				new File(dest).getPath() + Constant.PATH_SEPERATOR + new File(source).getName(), timeStamp));
	}

	public static boolean moveFileToFile(String fileName, String dest) {

		logger.debug("Moving file:{} to {}...", fileName, dest);
		try {
			// FileUtils.moveFileToDirectory(new File(fileName), new File(dest),
			// true);
			FileUtils.moveFile(new File(fileName), new File(dest));
			logger.debug("File moved to : {}", dest);
			logger.debug("File moved!");
		} catch (IOException e) {
			logger.error("Failed move file!", e);
			return false;
		}
		return true;
	}

	public static boolean moveFileToDirectory(String source, String dest, String timeStamp) {

		return moveFileToFile(source, fileNameAppend(
				new File(dest).getPath() + Constant.PATH_SEPERATOR + new File(source).getName(), timeStamp));
	}

	public static boolean moveFileToDirectory(List<String> sourceList, String dest, String timeStamp) {

		for (String source : sourceList) {

			if (!moveFileToDirectory(source, dest, timeStamp)) {
				return false;
			}
		}
		return true;
	}

	public static InputStreamReader readMessageFromFile(String name, String encoding) {
		try {
			File file = new File(name);
			if (!file.exists()) {
				logger.error("File: [{}] not exists!", name);
			}

			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encoding);// "GBK"

			return isr;
		} catch (Exception e) {
			logger.error("Failed to read xml", e);
			return null;
		}
	}

	public static boolean createFile(String name) {

		try {
			File file = new File(name);
			return file.createNewFile();
		} catch (Exception e) {
			logger.error("encounter exception=>", e);
			return false;
		}
	}

	public static boolean checkFile(String name) {
		try {
			File file = new File(name);
			if (file.exists()) {
				return true;
			}
		} catch (Exception e) {
			logger.error("encounter exception=>", e);
		}

		logger.debug("File:{} not exist", name);
		return false;
	}

	public static String OnlyForTest_generateFileFullPath(String fileName) {
		return Paths.get(".").toAbsolutePath().normalize().toString() + Constant.PATH_FULL + fileName;
	}

	public static String generateFileFullPath(String filePath, String fileName) {
		return Paths.get(".").toAbsolutePath().normalize().toString() + filePath + fileName;
	}

	public static String generateFullPath(String filePath) {
		return Paths.get(".").toAbsolutePath().normalize().toString() + filePath;
	}

	public static List<String> getFileList(String path, String orderFileNamePrefix) {

		List<String> fList = new ArrayList<String>();

		Collection<File> fileList = FileUtils.listFiles(new File(path),
				FileFilterUtils.prefixFileFilter(orderFileNamePrefix), null);
		if (fileList != null) {
			for (File f : fileList) {
				fList.add(f.getPath());
			}
		}
		return fList;
	}

	public static String fileNameAppend(String fileName, String addStr) {

		File file = null;
		try {
			file = new File(fileName);
			// if (!file.exists()) {
			// return fileName;
			// }
		} catch (Exception e) {
			logger.error("encounter exception=>", e);
			return fileName;
		}

		String path = file.getParent();

		String fullName = file.getName();
		if (fullName.indexOf(".") <= 0) {
			return fileName;
		}

		String preName = fullName.substring(0, fullName.indexOf("."));
		String subName = fullName.substring(fullName.indexOf("."), fullName.length());

		StringBuffer str = new StringBuffer("");
		str.append(path);
		str.append(Constant.PATH_SEPERATOR);
		str.append(preName);
		str.append("_");
		str.append(addStr);
		str.append(subName);

		logger.debug("File name append from: {} to {}", fileName, str.toString());

		return str.toString();
	}
}
