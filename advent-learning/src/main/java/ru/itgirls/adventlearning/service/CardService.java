package ru.itgirls.adventlearning.service;

import ru.itgirls.adventlearning.dto.CardCreateDto;
import ru.itgirls.adventlearning.dto.CardDto;
import ru.itgirls.adventlearning.entity.Card;

import java.util.List;

public interface CardService {
    Card getCardById(Long id);

    List<CardDto> getCardAll();

    CardDto insertCard(CardDto cardDto);

    CardDto insertCardToTheme(Long theme_id, CardCreateDto cardDto);

    List<CardDto> getCardByThemeId(Long id);

    CardDto updateCardById(Long id, CardCreateDto cardDto);
    void deleteCardById(Long id);
}
