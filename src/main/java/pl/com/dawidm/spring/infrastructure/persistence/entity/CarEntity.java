package pl.com.dawidm.spring.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.dawidm.spring.domain.car.Car;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String color;
    private String maxSpeed;
    private String engineType;
    private String enginePower;

    public Car toCar() {
        return Car.builder()
                .id(id.toString())
                .model(model)
                .color(color)
                .maxSpeed(maxSpeed)
                .engineType(engineType)
                .enginePower(enginePower)
                .build();
    }
}
