package stc.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessment.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);
}
