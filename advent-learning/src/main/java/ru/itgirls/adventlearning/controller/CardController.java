package ru.itgirls.adventlearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.adventlearning.dto.CardDto;
import ru.itgirls.adventlearning.service.CardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/card/{id}")
    CardDto getCardById(@PathVariable("id") Long id) {
        return cardService.getCardById(id);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/cards")
    List<CardDto> getCardAll() {
        return cardService.getCardAll();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/getcardsbytheme")
    List<CardDto> getCardsByTheme(@RequestParam("themeId") Long id) {
        return cardService.getCardByThemeId(id);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/card")
    CardDto insertCard(@RequestBody CardDto dto) {
        return cardService.insertCard(dto);
    }
}
