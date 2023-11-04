package ru.itgirls.adventlearning.service;

import ru.itgirls.adventlearning.dto.ThemeDto;
import ru.itgirls.adventlearning.entity.Theme;

import java.util.List;

public interface ThemeService {
    List<ThemeDto> getThemeAll();
}
