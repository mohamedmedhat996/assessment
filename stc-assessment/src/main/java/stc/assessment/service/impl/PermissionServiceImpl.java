package stc.assessment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import stc.assessment.entity.Group;
import stc.assessment.entity.Permission;
import stc.assessment.entity.User;
import stc.assessment.repository.PermissionsRepository;
import stc.assessment.repository.UsersRepository;
import stc.assessment.service.PermissionService;
import stc.assessment.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionsRepository permissionsRepository;

    @Override
    public Permission checkUserPermission(User user, String permissionlevel) {
        return permissionsRepository.
                findByUserEmailAndPermissionLevel(user, permissionlevel);

    }
}
