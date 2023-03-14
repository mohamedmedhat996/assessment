package stc.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessment.entity.Permission;
import stc.assessment.entity.User;

import java.util.List;


@Repository
public interface PermissionsRepository extends JpaRepository<Permission, Long> {

    Permission findByUserEmailAndPermissionLevel(User user, String permissionLevel);

    List<Permission> findByUserEmail(User user);

}
