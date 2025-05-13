package ru.dzserviceauth.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dzserviceauth.error.ErrorDto;
import ru.dzserviceauth.exceptions.InvalidCredentials;
import ru.dzserviceauth.exceptions.UnauthorizedUser;

import java.util.Optional;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <ErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ErrorDto errorDto = new ErrorDto();
        Optional <FieldError> username = e.getBindingResult().getFieldErrors().stream().filter(fieldError -> fieldError.getField().equals("username")).findFirst();
        username.ifPresent(fieldError -> errorDto.setUsername(fieldError.getDefaultMessage()));

        Optional<FieldError> password = e.getBindingResult().getFieldErrors().stream().filter(fieldError -> fieldError.getField().equals("password")).findFirst();
        password.ifPresent(fieldError -> errorDto.setPassword(password.get().getDefaultMessage()));

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
