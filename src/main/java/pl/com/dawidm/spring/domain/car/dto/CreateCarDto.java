package pl.com.dawidm.spring.domain.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.dawidm.spring.domain.car.Car;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDto {
    private String model;
    private String color;
    private String maxSpeed;
    private String engineType;
    private String enginePower;

    public Car toCar() {
        return Car.builder()
                .model(model)
                .color(color)
                .maxSpeed(maxSpeed)
                .engineType(engineType)
                .enginePower(enginePower)
                .build();
    }
}
