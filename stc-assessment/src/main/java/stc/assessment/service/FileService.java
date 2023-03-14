package stc.assessment.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import stc.assessment.entity.File;
import stc.assessment.entity.Item;

public interface FileService {
    void save(MultipartFile file, Item fileItem);
    Resource download(String fileName);

}
