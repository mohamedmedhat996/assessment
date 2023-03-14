package stc.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Blob;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FILES")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Lob
    @Column(name = "BINARY_CONTENT", nullable = false, columnDefinition = "bytea")
    private byte[] binaryContent;

    @Column(name = "FILE_TYPE", nullable = false)
    private String fileType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item itemId;
}
