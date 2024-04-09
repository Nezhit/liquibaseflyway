package cosmonaut.service;

import cosmonaut.util.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Value("avatars/")
    private String imagePath;
    private final ResourceLoader resourceLoader;
    @Autowired
    private CurrentUserUtils currentUserUtils;


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

    public ResponseEntity<byte[]> getImage() {
        try {
            String avatarUrl = currentUserUtils.getCurrentLoggedUser().getAvatarUrl();
            System.out.println("Ava= " + avatarUrl);
            Path path = Paths.get(imagePath + avatarUrl);

            System.out.println("Пытаемся получить файл по пути: " + path.toString()); // Логируем путь

            if (Files.exists(path)) {
                byte[] imageData = Files.readAllBytes(path);
                String contentType = Files.probeContentType(path);

                MediaType mediaType = MediaType.parseMediaType(contentType);
                return ResponseEntity.ok().contentType(mediaType).body(imageData);
            } else {
                System.out.println("Файл не найден: " + path.toString()); // Логируем, если файл не найден
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<?> saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        // Проверка на наличие файла в запросе
        if (multipartFile != null && !multipartFile.isEmpty()) {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            try {
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ioe) {
                    throw new IOException("Could not save file: " + fileName, ioe);
                }
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload file: " + fileName);
            }
        }
        return ResponseEntity.ok("File uploaded successfully: " + fileName);

    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = Paths.get(imagePath + fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException | FileNotFoundException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }

    public ResponseEntity<Resource> checkResource(Resource resource, HttpServletRequest request) {
        if (resource == null) {
            return ResponseEntity.notFound().build();
        }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Исключение сработало");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

    }
}
