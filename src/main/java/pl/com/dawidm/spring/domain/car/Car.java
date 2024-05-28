package pl.com.dawidm.spring.domain.car;

import lombok.Builder;
import lombok.Data;
import pl.com.dawidm.spring.domain.car.dto.CarDto;
import pl.com.dawidm.spring.infrastructure.persistence.entity.CarEntity;

@Builder
@Data
public class Car {
    private String id;
    private String model;
    private String color;
    private String maxSpeed;
    private String engineType;
    private String enginePower;

    public CarEntity toEntity() {
        return CarEntity.builder()
                .id(id != null ? Long.parseLong(id) : null)
                .model(model)
                .color(color)
                .maxSpeed(maxSpeed)
                .engineType(engineType)
                .enginePower(enginePower)
                .build();
    }

    public CarDto toCarDto() {
        return CarDto.builder()
                .id(id)
                .model(model)
                .color(color)
                .maxSpeed(maxSpeed)
                .engineType(engineType)
                .enginePower(enginePower)
                .build();
    }
}
