package pl.com.dawidm.spring.infrastructure.service.car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.com.dawidm.spring.configuration.validator.Validator;
import pl.com.dawidm.spring.domain.car.Car;
import pl.com.dawidm.spring.domain.car.dto.CarDto;
import pl.com.dawidm.spring.domain.car.dto.CreateCarDto;
import pl.com.dawidm.spring.domain.car.dto.validator.CreateCarDtoValidator;
import pl.com.dawidm.spring.domain.car.dto.validator.PartialUpdateCarDtoValidator;
import pl.com.dawidm.spring.domain.car.repository.CarRepository;
import pl.com.dawidm.spring.infrastructure.persistence.exception.CarAppException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public boolean carExistsInDatabase(Long id) {
        return carRepository.carExistsInDatabase(id);
    }

    public CarDto getCarById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Id can't be null or zero or greater than zero");
        }
        log.info("Get car by id: {}", id);
        return carRepository.getCarById(id).toCarDto();
    }

    public List<CarDto> getAllCars() {
        log.info("Get all cars from DB");
        return carRepository.getAllCars().stream()
                .map(Car::toCarDto)
                .toList();
    }

    public CarDto createCar(CreateCarDto createCarDto) {
        Validator.validate(new CreateCarDtoValidator(), createCarDto);
        log.info("Create new car: {}", createCarDto.toCar());
        return carRepository.saveCar(createCarDto.toCar()).toCarDto();
    }

    public CarDto updateCar(CreateCarDto updateCarDto, Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Id can't be null or zero or greater than zero");
        }
        Validator.validate(new CreateCarDtoValidator(), updateCarDto);
        Car car = Car.builder()
                .id(id.toString())
                .model(updateCarDto.getModel())
                .color(updateCarDto.getColor())
                .maxSpeed(updateCarDto.getMaxSpeed())
                .engineType(updateCarDto.getEngineType())
                .enginePower(updateCarDto.getEnginePower())
                .build();
        return carRepository.saveCar(car).toCarDto();
    }

    public CarDto partialUpdateCar(CreateCarDto partialUpdateCarDto, Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Id can't be null or zero or greater than zero");
        }
        Validator.validate(new PartialUpdateCarDtoValidator(), partialUpdateCarDto);
        Car car = carRepository.getCarById(id);
        return carRepository.saveCar(partialUpdate(car, partialUpdateCarDto)).toCarDto();
    }

    public boolean deleteCarById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Id can't be null or zero or greater than zero");
        }
        Car car = carRepository.getCarById(id);
        log.info("Delete car: {}", car);
        return carRepository.deleteCarById(id);
    }

    public boolean deleteAllCars() {
        if (carRepository.getAllCars().isEmpty()) {
            throw new CarAppException("No cars found in DB to delete");
        }
        ;
        log.info("Delete all cars in database");
        return carRepository.deleteAllCars();
    }

    private Car partialUpdate(Car car, CreateCarDto partialUpdateCarDto) {
        return Car.builder()
                .id(car.getId())
                .model(partialUpdateCarDto.getModel() != null ? partialUpdateCarDto.getModel() : car.getModel())
                .color(partialUpdateCarDto.getColor() != null ? partialUpdateCarDto.getColor() : car.getColor())
                .maxSpeed(partialUpdateCarDto.getMaxSpeed() != null ? partialUpdateCarDto.getMaxSpeed() : car.getMaxSpeed())
                .engineType(partialUpdateCarDto.getEngineType() != null ? partialUpdateCarDto.getEngineType() : car.getEngineType())
                .enginePower(partialUpdateCarDto.getEnginePower() != null ? partialUpdateCarDto.getEnginePower() : car.getEnginePower())
                .build();
    }
}
