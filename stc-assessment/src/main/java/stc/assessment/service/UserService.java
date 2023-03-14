package stc.assessment.service;

import stc.assessment.entity.User;


public interface UserService {
    User getUserByEmail(String userEmail);
}
