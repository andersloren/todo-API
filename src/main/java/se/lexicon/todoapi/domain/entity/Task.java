package se.lexicon.todoapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(exclude = "person")
@ToString(exclude = "person")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
