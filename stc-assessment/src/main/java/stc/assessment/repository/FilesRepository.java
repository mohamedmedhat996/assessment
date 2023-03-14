package stc.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessment.entity.File;
import stc.assessment.entity.Item;

@Repository
public interface FilesRepository extends JpaRepository<File, Long> {

    File getFileByItemId(Item item);

}
