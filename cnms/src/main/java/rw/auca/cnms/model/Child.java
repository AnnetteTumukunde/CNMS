package rw.auca.cnms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class Child extends CommonSpecification {

    @Column
    @NotNull
    private String name;

    @Column
    private String fatherName;

    @Column
    private String motherName;

    @Column
    private String fatherPhoneNumber;

    @Column
    private String motherPhoneNumber;

    @Email
    @Column
    private String fatherEmail;

    @Email
    @Column
    private String motherEmail;

    @Column
    private String guardianName;

    @Column
    private String guardianPhoneNumber;

    @Email
    @Column
    private String guardianEmail;

    @Column
    private Date dateOfBirth;

    @Column
    private String gender;

    @Column
    private String note;

}
