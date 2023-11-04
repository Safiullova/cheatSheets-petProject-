package ru.itgirls.adventlearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirls.adventlearning.dto.ThemeDto;
import ru.itgirls.adventlearning.service.ThemeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService service;
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/themes")
    List<ThemeDto> getCardAll() {
        return service.getThemeAll();
    }
}
