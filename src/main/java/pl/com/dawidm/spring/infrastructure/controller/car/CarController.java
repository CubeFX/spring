package pl.com.dawidm.spring.infrastructure.controller.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.com.dawidm.spring.domain.car.dto.CarDto;
import pl.com.dawidm.spring.domain.car.dto.CreateCarDto;
import pl.com.dawidm.spring.infrastructure.service.car.CarService;

import java.util.List;

@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/car/create")
    public ResponseEntity<CarDto> createCar(@RequestBody CreateCarDto createCarDto) {
        return new ResponseEntity<>(carService.createCar(createCarDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody CreateCarDto updateCarDto) {
        if (carService.carExistsInDatabase(id)) {
            carService.updateCar(updateCarDto, id);
            return new ResponseEntity<>("Car updated", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carService.createCar(updateCarDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/car/{id}")
    public ResponseEntity<?> partialUpdateCar(@PathVariable Long id, @RequestBody CreateCarDto partialUpdateCarDto) {
        carService.partialUpdateCar(partialUpdateCarDto, id);
        return new ResponseEntity<>("Car partial updated", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/car/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.deleteCarById(id), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/cars")
    public ResponseEntity<Boolean> deleteCars() {
        return new ResponseEntity<>(carService.deleteAllCars(), HttpStatus.ACCEPTED);
    }

    //TODO TESTY JEDNOSTKOWE I INTEGRACYJNE
}
