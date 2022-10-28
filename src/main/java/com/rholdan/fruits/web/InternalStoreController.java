package com.rholdan.fruits.web;

import com.rholdan.fruits.domain.dto.FruitDTO;
import com.rholdan.fruits.service.FruitsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/internal/fruits")
public class InternalStoreController {

    private final FruitsService fruitsService;

    @PostMapping
    public ResponseEntity<FruitDTO> createFruit(@RequestBody @Valid FruitDTO dto) {

        return ResponseEntity.ok(fruitsService.create(dto));

    }

    @PutMapping("/{id}")
    public ResponseEntity<FruitDTO> updateFruit(
            @PathVariable Long id,
            @RequestBody @Valid FruitDTO dto) {

        return ResponseEntity.ok(fruitsService.updateById(id, dto));

    }
}
