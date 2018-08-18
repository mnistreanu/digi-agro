package com.arobs.weather;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DownloadGZipTest {

	@Autowired
	public LocationFileDownloader downloader;
	@Autowired
	public GZipFile zipFile; 
	
	@Test
	public void downloadFile() throws IOException {
		downloader.downloadFile(null, null);
	}
	
	@Test
	public void zipFile() throws IOException {
		zipFile.unzip(null, null);
	}
}
