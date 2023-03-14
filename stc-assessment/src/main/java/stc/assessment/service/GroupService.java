package stc.assessment.service;

import stc.assessment.entity.Group;
import stc.assessment.entity.User;

import java.util.List;


public interface GroupService {
    Group getGroupByName(String groupName);

    List<Group> getGroupByUser(User user);
}
