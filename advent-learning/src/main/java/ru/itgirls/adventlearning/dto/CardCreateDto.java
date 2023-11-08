package ru.itgirls.adventlearning.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import ru.itgirls.adventlearning.entity.Card;

@Data
@Builder
public class CardCreateDto {
    private String name;
    private String description;
    public static Card toDomainObject(CardCreateDto dto) {
        return Card.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
