package rw.auca.cnms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ChildGrowth extends CommonSpecification {

    @JoinColumn
    @ManyToOne
    private Child child;

    @Column
    private Double weight;

    @Column
    private Double height;

    @Column
    private Date dateOfRecord;

    @Column
    private Double bmi;

    @Column
    private String comment;

    @Column
    @Enumerated(EnumType.STRING)
    private EBMIStatus ebmiStatus;

}
