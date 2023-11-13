package se.lexicon.todoapi.domain.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTOView {

    private String name;
    private List<TaskDTOView> tasks = new ArrayList<>();

}
