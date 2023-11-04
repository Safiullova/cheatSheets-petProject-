package ru.itgirls.adventlearning.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThemeDto {
    Long id;
    String name;
}
