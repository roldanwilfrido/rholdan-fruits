package com.rholdan.fruits.service;

import com.rholdan.fruits.domain.Fruits;
import com.rholdan.fruits.domain.dto.FruitDTO;
import com.rholdan.fruits.repository.FruitsRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FruitsService {

    private final FruitsRepository fruitsRepository;

    public List<FruitDTO> getAllByCriteria(String criteria) {

            return (
                    StringUtils.hasText(criteria)?
                    fruitsRepository.findAllByProductContaining(criteria):
                    fruitsRepository.findAll()
            )
                    .stream().map(this::dtoBuilder)
                    .toList();

    }

    public FruitDTO create(FruitDTO dto) {

        if (fruitsRepository.findByProduct(dto.getProduct()).isPresent()) {
            throw new EntityExistsException();
        }

        Fruits fruit = fruitsRepository.save(objectBuilder(dto));
        return dtoBuilder(fruit);

    }

    public FruitDTO updateById(Long id, FruitDTO dto) {

        Fruits fruit = getById(id)
                .orElseThrow(EntityNotFoundException::new);
        fruit.setProduct(dto.getProduct());
        fruit.setPrice(dto.getPrice());

        fruitsRepository.save(fruit);
        return dtoBuilder(fruit);

    }

    private Optional<Fruits> getById(Long id) {

        return fruitsRepository.findById(id);

    }

    private FruitDTO dtoBuilder(Fruits object) {

        return FruitDTO.builder()
                .id(object.getId())
                .product(object.getProduct())
                .price(object.getPrice())
                .build();

    }

    private Fruits objectBuilder(FruitDTO dto) {

        return Fruits.builder()
                .id(dto.getId())
                .product(dto.getProduct())
                .price(dto.getPrice())
                .build();

    }

}
