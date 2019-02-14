package com.arobs.weather;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class GZipFile {
	private static final Logger logger = LoggerFactory.getLogger(GZipFile.class);
	private static final String INPUT_GZIP_FILE = "r:/digi-agro/city.list.json.gz";
	private static final String OUTPUT_FILE = "r:/digi-agro/city.list.json";

	public String unzip(String gzFileName, String outputFileName) {
		if (StringUtils.isEmpty(gzFileName)) {
			gzFileName = INPUT_GZIP_FILE;
		}
		if (StringUtils.isEmpty(outputFileName)) {
			outputFileName = OUTPUT_FILE;
		}
		byte[] buffer = new byte[1024];
		try {
			GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream(gzFileName));
			FileOutputStream outputStream = new FileOutputStream(outputFileName);
			int len;
			while ((len = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			outputStream.close();
			logger.info("Done");
			return outputFileName;
		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		}
	}
}
