package stc.assessment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stc.assessment.entity.Group;
import stc.assessment.entity.Permission;
import stc.assessment.entity.User;
import stc.assessment.repository.GroupsRepository;
import stc.assessment.repository.PermissionsRepository;
import stc.assessment.repository.UsersRepository;
import stc.assessment.service.GroupService;
import stc.assessment.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupsRepository groupsRepository;
    private final PermissionsRepository permissionsRepository;


    @Override
    public Group getGroupByName(String groupName) {
        return groupsRepository.findByGroupName(groupName);
    }

    @Override
    public List<Group> getGroupByUser(User user) {
        List<Group> groups = new ArrayList<>();
        for (Permission permission: permissionsRepository.findByUserEmail(user)){
            groups.add(permission.getGroupId());
        }
        return groups;
    }
}
