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
@Table(name = "PERMISSIONS")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_EMAIL", nullable = false)
    private User userEmail;

    @Column(name = "PERMISSIONS_LEVEL", length = 64, nullable = false)
    private String permissionLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group groupId;

}
