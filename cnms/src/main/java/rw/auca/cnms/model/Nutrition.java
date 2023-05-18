package rw.auca.cnms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Nutrition extends CommonSpecification {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Transient
    private MultipartFile nutritionFile;
    @Lob
    @Column
    private byte[] picture;

    @Column(name = "fileName")
    private String fileName;

}
