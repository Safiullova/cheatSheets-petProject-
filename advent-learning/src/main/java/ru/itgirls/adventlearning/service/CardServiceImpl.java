package ru.itgirls.adventlearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.adventlearning.dto.CardDto;
import ru.itgirls.adventlearning.entity.Card;
import ru.itgirls.adventlearning.entity.Theme;
import ru.itgirls.adventlearning.repository.CardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public CardDto getCardById(Long id) {
        Card card = cardRepository.findById(id).orElseThrow();
        return convertToDto(card);
    }

    @Override
    public List<CardDto> getCardAll() {
        return cardRepository
                .findCardByThemeId(1L)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public CardDto insertCard(CardDto cardDto) {
        Card card = toDomainObject(cardDto);
        Card savedCard = cardRepository.save(card);
        return convertToDto(savedCard);
    }

    @Override
    public List<CardDto> getCardByThemeId(Long id) {

        return cardRepository
                .findCardByThemeId(id)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private CardDto convertToDto(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .name(card.getName())
                .description(card.getDescription())
                .build();
    }

    public static Card toDomainObject(CardDto dto) {
        return Card
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .theme(new Theme(1L, "123", null))
                .build();
    }
}
