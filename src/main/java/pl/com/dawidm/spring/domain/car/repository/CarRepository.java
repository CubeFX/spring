package pl.com.dawidm.spring.domain.car.repository;

import pl.com.dawidm.spring.domain.car.Car;

import java.util.List;

public interface CarRepository {
    public Car getCarById(Long id);

    public Boolean carExistsInDatabase(Long id);

    public List<Car> getAllCars();

    public boolean deleteCarById(Long id);

    public boolean deleteAllCars();

    public Car saveCar(Car car);
}
