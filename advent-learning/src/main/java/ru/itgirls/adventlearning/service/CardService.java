package ru.itgirls.adventlearning.service;

import ru.itgirls.adventlearning.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto getCardById(Long id);

    List<CardDto> getCardAll();

    CardDto insertCard(CardDto cardDto);

    List<CardDto> getCardByThemeId(Long id);

}
