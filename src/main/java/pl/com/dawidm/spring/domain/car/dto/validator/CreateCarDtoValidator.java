package pl.com.dawidm.spring.domain.car.dto.validator;


import pl.com.dawidm.spring.configuration.validator.Validator;
import pl.com.dawidm.spring.domain.car.dto.CreateCarDto;

import java.util.HashMap;
import java.util.Map;

public class CreateCarDtoValidator implements Validator<CreateCarDto> {

    private final Map<String, String> validateErrors = new HashMap<String, String>();

    @Override
    public Map<String, String> validate(CreateCarDto createCarDto) {
        validateModel(createCarDto.getModel());
        validateColor(createCarDto.getColor());
        validateMaxSpeed(createCarDto.getMaxSpeed());
        validateEngineType(createCarDto.getEngineType());
        validateEnginePower(createCarDto.getEnginePower());
        return this.validateErrors;
    }

    private void validateEnginePower(String enginePower) {
        if (enginePower == null || enginePower.isEmpty()) {
            this.validateErrors.put("enginePower", "Engine power is required");
        } else if (!enginePower.matches("^[0-9]* KM?$")) {
            this.validateErrors.put("enginePower", "Engine power is invalid");
        }
    }

    private void validateEngineType(String engineType) {
        if (engineType == null || engineType.isEmpty()) {
            this.validateErrors.put("engineType", "Engine type is required");
        } else if (engineType.equals("GAZOLINE") || engineType.equals("ELECTRIC") || engineType.equals("LPG") || engineType.equals("DIESEL")) {
            this.validateErrors.put("engineType", "Engine type is invalid");
        }
    }

    private void validateMaxSpeed(String maxSpeed) {
        if (maxSpeed == null || maxSpeed.isEmpty()) {
            this.validateErrors.put("maxSpeed", "Max speed is required");
        } else if (!maxSpeed.matches("^[0-9]* km/h?$")) {
            this.validateErrors.put("maxSpeed", "Max speed should be a number and must have km/h");
        }
    }

    private void validateColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            this.validateErrors.put("color", "Color is required");
        } else if (!color.matches("^[a-zA-Z]*$")) {
            this.validateErrors.put("color", "Invalid color");
        }
    }

    private void validateModel(String model) {
        if (model == null || model.isEmpty()) {
            this.validateErrors.put("model", "Model is required");
        } else if (!model.matches("^[a-zA-Z0-9 ]*$")) {
            this.validateErrors.put("model", "Model contains invalid characters");
        }
    }
}
