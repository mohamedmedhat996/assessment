package stc.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessment.entity.Group;
import stc.assessment.entity.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {

    Item findByName(String groupName);

    Item findByNameAndPermissionGroupId(String name, Group permissionGroupId);
}
