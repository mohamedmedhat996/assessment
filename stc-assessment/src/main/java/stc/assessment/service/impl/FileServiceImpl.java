package stc.assessment.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import stc.assessment.entity.File;
import stc.assessment.entity.Item;
import stc.assessment.repository.FilesRepository;
import stc.assessment.service.FileService;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FilesRepository filesRepository;
    private final Path root = Paths.get("uploads");

    @Override
    public void save(MultipartFile file, Item fileItem) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            File FileDB = new File(1L, file.getBytes(), file.getContentType(), fileItem );
            filesRepository.save(FileDB);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("this file already exists before");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource download(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
