package pl.com.dawidm.spring.infrastructure.controller.advicecontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class AdviceController {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(HttpServletRequest request, Exception exception) throws Exception {
        return new ResponseEntity<String>(
                "INTERNAL SERVER ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
