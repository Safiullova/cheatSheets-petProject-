package ru.itgirls.adventlearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.adventlearning.dto.ThemeDto;
import ru.itgirls.adventlearning.entity.Theme;
import ru.itgirls.adventlearning.repository.ThemeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository repository;

    @Override
    public List<ThemeDto> getThemeAll() {
        return repository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private ThemeDto convertToDto(Theme theme) {
        return ThemeDto.builder()
                .id(theme.getId())
                .name(theme.getName())
                .build();
    }
}
