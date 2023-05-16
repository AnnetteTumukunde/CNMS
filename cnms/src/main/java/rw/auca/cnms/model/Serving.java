package rw.auca.cnms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Serving extends CommonSpecification {

    @JoinColumn
    @ManyToOne
    private ChildGrowth childGrowth;

    @JoinColumn
    @ManyToOne
    private Nutrition nutrition;

    @Column
    private Double quantity;

    @Column
    @Enumerated(EnumType.STRING)
    private EQuantityType quantityType;

    @Column
    @Enumerated(EnumType.STRING)
    private EDayServingTime preferredTime;

}
