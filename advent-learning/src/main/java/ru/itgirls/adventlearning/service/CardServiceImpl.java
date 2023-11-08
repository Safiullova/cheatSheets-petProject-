package ru.itgirls.adventlearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.adventlearning.dto.CardCreateDto;
import ru.itgirls.adventlearning.dto.CardDto;
import ru.itgirls.adventlearning.dto.ThemeDto;
import ru.itgirls.adventlearning.entity.Card;
import ru.itgirls.adventlearning.entity.Theme;
import ru.itgirls.adventlearning.repository.CardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final ThemeService themeService;

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow();
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
    public CardDto insertCard(CardDto dto) {
        if (cardRepository.findById(dto.getId()).isPresent()){
            throw new RuntimeException("card already exists");
        }
        Card card = toDomainObject(dto);
        Card savedCard = cardRepository.save(card);
        return convertToDto(savedCard);
    }

    @Override
    public CardDto insertCardToTheme(Long theme_id, CardCreateDto dto) {
        Theme theme = themeService.getThemeByID(theme_id);
        Card card = CardCreateDto.toDomainObject(dto);
        card.setTheme(theme);
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

    @Override
    public CardDto updateCardById(Long id, CardCreateDto dto) {
        Card card = cardRepository.findById(id).orElseThrow();
        card.setName(dto.getName());
        card.setDescription(dto.getDescription());
        Card savedCard = cardRepository.save(card);
        return convertToDto(savedCard);
    }

    @Override
    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
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
