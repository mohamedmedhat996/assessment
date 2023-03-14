package stc.assessment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import stc.assessment.entity.Group;
import stc.assessment.entity.Permission;
import stc.assessment.entity.User;
import stc.assessment.repository.GroupsRepository;
import stc.assessment.repository.PermissionsRepository;
import stc.assessment.repository.UsersRepository;

@SpringBootApplication
public class AssessmentApplication {

	@Autowired
	private GroupsRepository groupsRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PermissionsRepository permissionsRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(){
		return (args) -> {
			// admin
			User admin = usersRepository.save(new User(1L,"admin@gmail.com","ADMIN"));
			Group adminGroup = groupsRepository.save(new Group(1L, "ADMIN"));
			permissionsRepository.save(new Permission(1L,admin,"EDIT",adminGroup));
			permissionsRepository.save(new Permission(2L,admin,"VIEW",adminGroup));

			// viewer
			User viewer = usersRepository.save(new User(2L,"viewer@gmail.com","VIEWER"));
			Group viewerGroup = groupsRepository.save(new Group(2L, "VIEWER"));
			permissionsRepository.save(new Permission(3L,viewer,"VIEW",viewerGroup));
		};
	}

}
