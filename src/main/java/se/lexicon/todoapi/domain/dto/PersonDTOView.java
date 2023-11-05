package se.lexicon.todoapi.domain.dto;

import lombok.*;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTOView {

    private String name;
    private List<Task> tasks = new ArrayList<>();

}
