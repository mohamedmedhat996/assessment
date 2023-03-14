package stc.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessment.entity.Group;

@Repository
public interface GroupsRepository extends JpaRepository<Group, Long> {

    Group findByGroupName(String name);

}
