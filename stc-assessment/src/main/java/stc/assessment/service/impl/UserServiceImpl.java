package stc.assessment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import stc.assessment.entity.User;
import stc.assessment.repository.UsersRepository;
import stc.assessment.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Override
    public User getUserByEmail(String userEmail) {
        return usersRepository.findByUserEmail(userEmail);
    }
}
