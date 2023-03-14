package stc.assessment.service;

import stc.assessment.entity.Group;
import stc.assessment.entity.Permission;
import stc.assessment.entity.User;

import java.util.Optional;

public interface PermissionService {
    Permission checkUserPermission(User user, String permissionlevel);
}
