package stc.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false, unique = true)
    private Long id;

    @Id
    @Column(name = "USER_EMAIL", length = 64, nullable = false, unique = true)
    private String userEmail;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

}
