package com.arobs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class FileService {

    @Value("${files_directory}")
    private String filesDirectory;

    public String upload(Long userId, MultipartFile file) throws IOException {

        String userFolderId = "/users/" + userId;
        String directoryPath = filesDirectory + userFolderId;

        Date date = new Date();
        String fileId = userFolderId + "/" + date.getTime() + "_" + file.getOriginalFilename();
        String filePath = filesDirectory + fileId;

        byte[] bytes = file.getBytes();
        Files.createDirectories(Paths.get(directoryPath));
        Files.write(Paths.get(filePath), bytes);

        return fileId;
    }

    public File getFile(String path) {
        return new File(filesDirectory + "/" + path);
    }
}
