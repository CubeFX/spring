package pl.com.dawidm.spring.configuration.validator;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public interface Validator<T> {
    Map<String, String> validate(T t);

    static <T> void validate(Validator<T> validator, T t) {
        var errors = validator.validate(t);
        if (!errors.isEmpty()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(joining(", "));
            throw new ValidatorException("[VALIDATION ERRORS]: " + message);
        }
    }
}
