package se.lexicon.todoapi.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class TaskDTOForm {

    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;

}
