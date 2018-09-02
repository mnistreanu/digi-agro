package com.arobs.weather.provider;

import com.arobs.interfaces.HasRepository;
import com.arobs.weather.GZipFile;
import com.arobs.weather.entity.WeatherLocation;
import com.arobs.weather.location.WeatherLocationJson;
import com.arobs.weather.location.WeatherLocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.jdto.DTOBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lucrari executate de serviciu:
 * <ol>
 * <li>Citeste de pe site-ul openweathe ZIP fisierul cu lista tuturor localitatilor</li>
 * <li>Extrage din arhiva fisieru JSON cu toate localitatile si il salvea pe disc</li>
 * <li>Transforma obiectele de tip JSON in obiecte Hibernate entitati de tip WeatherLocation</li>
 * <li>Inscrie entitatile in baza de date</li>
 * <li>Extrage informati din baza de date dupa criteriu de filtrare</li>
 * <li>Sterge informatii din baza de date</li>
 * </ol>
 * @see com.arobs.weather.entity.WeatherLocation
 */
@Service
public class WeatherLocationProvider implements HasRepository<WeatherLocationRepository> {
	private static final Logger logger = LoggerFactory.getLogger(WeatherLocationProvider.class);
	public static final String FILE_URL = "http://bulk.openweathermap.org/sample/city.list.json.gz";
	public static final String LOCAL_FILE = "r:/digi-agro/city.list.json.gz";

	
	@Value("${weather.location.url}")
	private String weatherLocationUrl;
	@Value("${location.zip.file}")
	private String locationZipFile;
	@Value("${location.json.file}")
	private String locationJsonFile;
	@Autowired
	private GZipFile zipFileExtractor;
    @Autowired
    private WeatherLocationRepository weatherLocationRepository;
    @Autowired
	private DTOBinder binder;


	private ObjectMapper objectMapper = new ObjectMapper();
	
    /**
     * <ol>
     * <li>Descarca fisierul ZIP cu localitatile de pe Open weather</li>
     * <li>Dezariveaza fisierul ZIP intr-un fisier JSON</li>
     * <li>Proceseaza fisierul JSON si formeaza o clectie de obiecte Java de tip JSON corespunzatoare localitatilor</li>
     * <li>Transforma obiectele de tip JSON in entitati Hibernate</li>
     * <li>Elimina toata informatia din tabelul "location" </li>
     * <li>Salveaza obiectele Hibernate in tabelul "location" din BD</li>
     * </ol>
     * @return Numarul de articole inserate. 
     * @throws IOException daca nu poate gasi fisierul JSON.
     */
	public int synchronizeLocations() throws IOException {
    	String downloadFile = downloadFile(weatherLocationUrl, locationZipFile);
    	logger.debug("Fisierul {} a fost descarcat", downloadFile);
    	String unzipedFile = zipFileExtractor.unzip(locationZipFile, locationJsonFile);
    	logger.debug("Fisierul {} a fost creat", unzipedFile);
    	
		Resource resource = new ClassPathResource(new File(locationJsonFile).getName());
		File file = resource.getFile(); 
		
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> jsonLocations = objectMapper.readValue(file,  listType);
		List<WeatherLocation> locationsEntities = binder.bindFromBusinessObjectList(WeatherLocation.class, jsonLocations
				.stream()
				.filter(v -> ((v.getCountry().equals("RO") || v.getCountry().equals("MD")) && 
									(v.getName().toLowerCase().startsWith("raio")  || v.getName().toLowerCase().startsWith("jude"))
						)
					)
				.collect(Collectors.toList()));
		weatherLocationRepository.deleteAll();
		weatherLocationRepository.save(locationsEntities);
    	logger.info("Au fost inserate {} localitati", locationsEntities.size());
		return locationsEntities.size();
    }

	/**
	 * Download ZIP Location de pe OpenWeather 
	 * @param fileUrl
	 * @param localFilePath
	 * @return - returneaza numele fisierului salvat
	 * @throws IOException
	 */
	String downloadFile(String fileUrl, String localFilePath) throws IOException {
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
	
    public List<WeatherLocation> find(String countyCode, String name) {
        return getRepository().find(countyCode, '%' + name + '%');
    }

    public List<WeatherLocation> findAllMdRo() {
        return getRepository().findAllMdRo();
    }

    @Override
    public WeatherLocationRepository getRepository() {
        return weatherLocationRepository;
    }
}
