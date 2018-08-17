package com.arobs.weather.location;

import com.arobs.interfaces.HasRepository;
import com.arobs.weather.GZipFile;
import com.arobs.weather.LocationFileDownloader;
import com.arobs.weather.entity.WeatherLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.jdto.DTOBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
public class WeatherLocationService implements HasRepository<WeatherLocationRepository> {
	private static final Logger logger = LoggerFactory.getLogger(WeatherLocationService.class);
	
	@Value("${weather.location.url}")
	private String weatherLocationUrl;
	@Value("${location.zip.file}")
	private String locationZipFile;
	@Value("${location.json.file}")
	private String locationJsonFile;
	@Autowired
	private LocationFileDownloader fileDownloader;
	@Autowired
	private GZipFile zipFileExtractor;
    @Autowired
    private WeatherLocationRepository weatherLocationRepository;
    @Autowired
	private DTOBinder binder;


	private ObjectMapper objectMapper = new ObjectMapper();
	
    public int synchronizeLocations() throws IOException {
    	String downloadFile = fileDownloader.downloadFile(weatherLocationUrl, locationZipFile);
    	logger.debug("Fisierul {} a fost descarcat", downloadFile);
    	String unzipedFile = zipFileExtractor.unzip(locationZipFile, locationJsonFile);
    	logger.debug("Fisierul {} a fost creat", unzipedFile);
    	
		Resource resource = new ClassPathResource(new File(locationJsonFile).getName());
		File file = resource.getFile(); 
		
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> jsonLocations = objectMapper.readValue(file,  listType);
		List<WeatherLocation> locationsEntities = binder.bindFromBusinessObjectList(WeatherLocation.class, jsonLocations
				.stream()
				.filter(v -> 
						(
							(v.getCountry().equals("RO") || v.getCountry().equals("MD")) && 
							(v.getName().toLowerCase().startsWith("raio")  || v.getName().toLowerCase().startsWith("jude"))
						)
					)
				.collect(Collectors.toList()));
		weatherLocationRepository.deleteAll();
		weatherLocationRepository.save(locationsEntities);
    	logger.info("Au fost inserate {} localitati", locationsEntities.size());
		return locationsEntities.size();
    }

    public List<WeatherLocation> find(String countyCode, String name) {
        return getRepository().find(countyCode, '%' + name + '%');
    }

    public List<WeatherLocation> findAllMdRo() {
        return getRepository().findAllMdRo();
    }

    public List<WeatherLocation> Sace(List<WeatherLocation> weatherLocations) {
        return getRepository().findAllMdRo();
    }

    @Override
    public WeatherLocationRepository getRepository() {
        return weatherLocationRepository;
    }
}
