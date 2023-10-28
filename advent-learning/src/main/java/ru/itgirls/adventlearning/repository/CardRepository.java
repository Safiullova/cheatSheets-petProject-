package ru.itgirls.adventlearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.adventlearning.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
