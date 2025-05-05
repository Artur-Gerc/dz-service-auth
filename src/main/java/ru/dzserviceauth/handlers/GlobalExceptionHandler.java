package ru.dzserviceauth.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dzserviceauth.exceptions.InvalidCredentials;
import ru.dzserviceauth.exceptions.UnauthorizedUser;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity <String> handleInvalidCredentials(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity <String> handleUnauthorizedUser(UnauthorizedUser e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
