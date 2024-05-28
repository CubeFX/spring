package pl.com.dawidm.spring.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.com.dawidm.spring.domain.car.Car;
import pl.com.dawidm.spring.domain.car.repository.CarRepository;
import pl.com.dawidm.spring.infrastructure.persistence.dao.CarRepositoryDao;
import pl.com.dawidm.spring.infrastructure.persistence.entity.CarEntity;
import pl.com.dawidm.spring.infrastructure.persistence.exception.CarAppException;
import pl.com.dawidm.spring.infrastructure.persistence.mappers.CarsListMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {

    private final CarRepositoryDao carRepositoryDao;


    @Override
    public List<Car> getAllCars() {
        List<CarEntity> carsEntity = (List<CarEntity>) carRepositoryDao.findAll();
        return CarsListMapper.fromEntity(carsEntity);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepositoryDao.save(car.toEntity()).toCar();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepositoryDao.findById(id)
                .map(CarEntity::toCar)
                .orElseThrow(() -> new CarAppException("Cannot find car by Id: " + id));
    }

    @Override
    public Boolean carExistsInDatabase(Long id) {
        return carRepositoryDao.findById(id)
                .map(CarEntity::toCar).isPresent();
    }

    @Override
    public boolean deleteCarById(Long id) {
        carRepositoryDao.deleteById(id);
        return !carRepositoryDao.existsById(id);
    }

    @Override
    public boolean deleteAllCars() {
        carRepositoryDao.deleteAll();
        return carRepositoryDao.findAll().isEmpty();
    }

}
