package com.arobs.weather;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationFileDownloader {
	private static final Logger logger = LoggerFactory.getLogger(LocationFileDownloader.class);
	private static final String FILE_URL = "http://bulk.openweathermap.org/sample/city.list.json.gz";
	private static final String LOCAL_FILE = "r:/digi-agro/city.list.json.gz";

	/**
	 * Download ZIP Location de pe OpenWeather 
	 * @param fileUrl
	 * @param localFilePath
	 * @return - returneaza numele fisierului salvat
	 * @throws IOException
	 */
	public String downloadFile(String fileUrl, String localFilePath) throws IOException {
		if (StringUtils.isEmpty(fileUrl)) {
			fileUrl = FILE_URL;
		}
		if (StringUtils.isEmpty(localFilePath)) {
			localFilePath = LOCAL_FILE;
		}
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

	    HttpEntity<String> entity = new HttpEntity<String>(headers);

	    ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET, entity, byte[].class, "1");

	    if (response.getStatusCode() == HttpStatus.OK) {
	        Files.write(Paths.get(localFilePath), response.getBody());
	        logger.debug("Fisierul {} a fost salvat", localFilePath);
	        return localFilePath;
	    } else {
	        logger.error("Fisierul {} nu a putut fi citit", localFilePath);
	        return "Error";
	    }
	}

}
