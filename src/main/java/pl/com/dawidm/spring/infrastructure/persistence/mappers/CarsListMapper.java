package pl.com.dawidm.spring.infrastructure.persistence.mappers;

import pl.com.dawidm.spring.domain.car.Car;
import pl.com.dawidm.spring.infrastructure.persistence.entity.CarEntity;

import java.util.List;

public class CarsListMapper {

    public static List<Car> fromEntity(List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(CarEntity::toCar)
                .toList();
    }

    public static List<CarEntity> fromDomain(List<Car> cars) {
        return cars.stream()
                .map(Car::toEntity)
                .toList();
    }
}
