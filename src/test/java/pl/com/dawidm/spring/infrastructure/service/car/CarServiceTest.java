package pl.com.dawidm.spring.infrastructure.service.car;

import org.junit.jupiter.api.Test;
import pl.com.dawidm.spring.configuration.validator.Validator;
import pl.com.dawidm.spring.configuration.validator.ValidatorException;
import pl.com.dawidm.spring.domain.car.dto.CreateCarDto;
import pl.com.dawidm.spring.domain.car.dto.validator.CreateCarDtoValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarServiceTest {

    @Test
    void whenCreateCarsDtoIsInvalidThenMustBeThrownException() {
        CreateCarDto createCarDto = CreateCarDto.builder().build();
        var thrown = assertThrows(ValidatorException.class, () -> {
            Validator.validate(new CreateCarDtoValidator(), createCarDto);
        });
        assertEquals("[VALIDATION ERRORS]: color: Color is required, engineType: Engine type is required, model: Model is required, maxSpeed: Max speed is required, enginePower: Engine power is required", thrown.getMessage());
    }
}