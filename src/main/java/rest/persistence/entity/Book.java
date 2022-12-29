package rest.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table (name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer people;
    private Date date_start;
    private Date date_end;
    private String phone;

    @ManyToOne
    @JoinColumn (name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn (name="record_id", nullable=false)
    private Record record;
}