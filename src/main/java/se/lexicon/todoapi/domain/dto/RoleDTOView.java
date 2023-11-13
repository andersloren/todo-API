package se.lexicon.todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RoleDTOView {

    private Long id;
    private String name;
}
