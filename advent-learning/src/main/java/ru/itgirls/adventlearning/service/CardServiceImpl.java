package ru.itgirls.adventlearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.adventlearning.dto.CardDto;
import ru.itgirls.adventlearning.entity.Card;
import ru.itgirls.adventlearning.repository.CardRepository;

import java.util.ArrayList;
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
        List<Card> cardList = cardRepository.findAll();

        List<CardDto> cardDTOList = new ArrayList<>();
        for (Card card:cardList
             ) {
            cardDTOList.add(convertToDto(card));
        }
        return cardDTOList;
    }

    @Override
    public CardDto insertCard(CardDto cardDto) {
        Card card = toDomainObject(cardDto);
        Card savedCard = cardRepository.save(card);
        return convertToDto(savedCard);
    }

    private CardDto convertToDto(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .name(card.getName())
                .description(card.getDescription())
                .build();
    }

    public static Card toDomainObject(CardDto dto) {
        return new Card(dto.getId(), dto.getName(), dto.getDescription());
    }

}
