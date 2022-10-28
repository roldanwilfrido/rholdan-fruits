package com.rholdan.fruits.web;

import com.rholdan.fruits.domain.dto.FruitDTO;
import com.rholdan.fruits.service.FruitsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/public/fruits")
public class PublicStoreController {

    private final FruitsService fruitsService;

    @GetMapping
    public ResponseEntity<List<FruitDTO>> getFruits(@RequestParam(required = false) String criteria) {

        return ResponseEntity.ok(fruitsService.getAllByCriteria(criteria));

    }
}
