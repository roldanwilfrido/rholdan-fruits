package com.rholdan.fruits.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class StoreErrorDTO {

    public Instant time;
    public Integer status;
    public String message;

}
