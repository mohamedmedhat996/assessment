package stc.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "TYPE", length = 64, nullable = false)
    private String type;

    @Column(name = "NAME", length = 64, nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_group_id", nullable = false)
    private Group permissionGroupId;


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "ID")
    private Item parent;

    @OneToMany(cascade = {CascadeType.ALL},orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private List<Item> children;

}
