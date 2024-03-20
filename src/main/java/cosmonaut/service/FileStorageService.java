package cosmonaut.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Value("avatars/")
    private String imagePath;
    private final ResourceLoader resourceLoader;



    public FileStorageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String storeFile(MultipartFile file) throws IOException {
        // Генерация уникального имени файла для избежания конфликтов
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Путь к директории для сохранения файла
        Path path = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), path);

        return fileName;
    }

    public ResponseEntity<byte[]> getImage(String imageName) {
        try {


            Path path = Paths.get(imagePath + imageName);
            if (Files.exists(path)) {
                byte[] imageData = Files.readAllBytes(path);
                String contentType = Files.probeContentType(path);

                MediaType mediaType = MediaType.parseMediaType(contentType);
                return ResponseEntity.ok().contentType(mediaType).body(imageData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
