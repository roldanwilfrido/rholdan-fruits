package com.rholdan.fruits.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FruitDTO {

    private Long id;
    @NotEmpty
    private String product;
    @NotNull
    private Double price;

}
