package pl.com.dawidm.spring.domain.car.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarDto {
    private String id;
    private String model;
    private String color;
    private String maxSpeed;
    private String engineType;
    private String enginePower;
}
