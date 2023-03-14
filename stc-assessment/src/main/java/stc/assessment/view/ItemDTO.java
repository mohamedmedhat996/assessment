package stc.assessment.view;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDTO {
    private String userEmail;
    private String name;
    private String parentName;
}
