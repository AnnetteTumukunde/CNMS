package rw.auca.cnms.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class CommonSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;

    @CreationTimestamp
    @Column
    private Date creationDate;

    @UpdateTimestamp
    @Column
    private Date updateDate;

    @Column
    private int ver = 0;

}
