package rest.service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
@Service
public class FileUploadService {
     
    public static void saveFile(String fileName, MultipartFile file) throws InterruptedException {
        try {
            Path uploadPath = Paths.get("src\\main\\resources\\static\\temp").resolve(fileName);
            FileOutputStream outputStream = new FileOutputStream(uploadPath.toFile());
            InputStream inputStream = file.getInputStream();
            IOUtils.copy(inputStream, outputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            Thread.sleep(500);
        } catch (IOException exception) {        
            exception.printStackTrace();
        }      
    }
}