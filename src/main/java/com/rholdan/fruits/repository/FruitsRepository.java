package com.rholdan.fruits.repository;

import com.rholdan.fruits.domain.Fruits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FruitsRepository extends JpaRepository<Fruits, Long> {

    List<Fruits> findAllByProductContaining(String criteria);
    Optional<Fruits> findByProduct(String product);

}
