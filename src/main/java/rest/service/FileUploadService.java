package rest.service;
 
 
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerSecurityMarker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
@Service
public class FileUploadService {
     
    public static void saveFile(String fileName, MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Path uploadPath = Paths.get("src\\main\\resources\\static\\temp").resolve(fileName);
            Files.copy(inputStream, uploadPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {        
            exception.printStackTrace();
        }      
    }
}