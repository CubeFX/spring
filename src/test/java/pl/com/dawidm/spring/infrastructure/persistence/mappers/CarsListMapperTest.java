package pl.com.dawidm.spring.infrastructure.persistence.mappers;

import org.junit.jupiter.api.Test;
import pl.com.dawidm.spring.domain.car.Car;
import pl.com.dawidm.spring.infrastructure.persistence.entity.CarEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarsListMapperTest {


    @Test
    void shouldReturnCorrectCarsListAfterUsingMapper() {
        List<CarEntity> carsEntity = carsEntityList();
        List<Car> cars = CarsListMapper.fromEntity(carsEntity);
        assertTrue(cars.stream()
                .allMatch(car -> carsEntity.stream()
                        .anyMatch(carEntity -> car.getId().equals(carEntity.getId().toString()) &&
                                car.getModel().equals(carEntity.getModel()) &&
                                car.getColor().equals(carEntity.getColor()) &&
                                car.getEnginePower().equals(carEntity.getEnginePower()) &&
                                car.getEngineType().equals(carEntity.getEngineType()) &&
                                car.getMaxSpeed().equals(carEntity.getMaxSpeed()))));
        assertEquals(carsEntity.size(), cars.size());
    }

    @Test
    void shouldReturnCorrectCarsEntityListAfterUsingMapper() {
        List<Car> cars = carsList();
        List<CarEntity> carsEntity = CarsListMapper.fromDomain(cars);
        assertTrue(carsEntity.stream()
                .allMatch(carEntity -> cars.stream()
                        .anyMatch(car -> carEntity.getId().toString().equals(car.getId()) &&
                                carEntity.getModel().equals(car.getModel()) &&
                                carEntity.getColor().equals(car.getColor()) &&
                                carEntity.getEnginePower().equals(car.getEnginePower()) &&
                                carEntity.getEngineType().equals(car.getEngineType()) &&
                                carEntity.getMaxSpeed().equals(car.getMaxSpeed()))));
        assertEquals(carsEntity.size(), cars.size());
    }

    private List<CarEntity> carsEntityList() {
        CarEntity carEntity1 = CarEntity.builder()
                .id(1L)
                .color("ORANGE")
                .model("AUDI A8 D3")
                .enginePower("300 KM")
                .engineType("GASOLINE")
                .maxSpeed("380 km/h")
                .build();
        CarEntity carEntity2 = CarEntity.builder()
                .id(2L)
                .color("ORANGE")
                .model("AUDI A3")
                .enginePower("200 KM")
                .engineType("LPG")
                .maxSpeed("330 km/h")
                .build();
        CarEntity carEntity3 = CarEntity.builder()
                .id(3L)
                .color("BLACK")
                .model("MERCEDES CLK AMG")
                .enginePower("200 KM")
                .engineType("GASOLINE")
                .enginePower("800 KM")
                .maxSpeed("330 km/h")
                .build();
        return List.of(carEntity1, carEntity2, carEntity3);
    }

    private List<Car> carsList() {
        Car car1 = Car.builder()
                .id("1")
                .color("ORANGE")
                .model("AUDI A8 D3")
                .enginePower("300 KM")
                .engineType("GASOLINE")
                .maxSpeed("380 km/h")
                .build();
        Car car2 = Car.builder()
                .id("2")
                .color("ORANGE")
                .model("AUDI A3")
                .enginePower("200 KM")
                .engineType("LPG")
                .maxSpeed("330 km/h")
                .build();
        Car car3 = Car.builder()
                .id("3")
                .color("BLACK")
                .model("MERCEDES CLK AMG")
                .enginePower("200 KM")
                .engineType("GASOLINE")
                .enginePower("800 KM")
                .maxSpeed("330 km/h")
                .build();
        return List.of(car1, car2, car3);
    }

}