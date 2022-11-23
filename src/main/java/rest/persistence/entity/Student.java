package rest.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student {
    @Id
    private UUID id;
    private String name;
    @Column(name = "birthdate")
    private String birthDate;

    private String number;
}
