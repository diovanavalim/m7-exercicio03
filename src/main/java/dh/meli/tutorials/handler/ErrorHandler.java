package dh.meli.tutorials.handler;

import dh.meli.tutorials.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(GetException.class)
    public ResponseEntity<ExceptionDetails> getExceptionHandler(GetException exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Error on select query")
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(SaveException.class)
    public ResponseEntity<ExceptionDetails> saveExceptionHandler(SaveException exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Error on insert or update query")
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(TutorialAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetails> tutorialAlreadyExistsExceptionHandler(TutorialAlreadyExistsException
                                                                                              exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Tutorial already exists")
                .message(exception.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(TutorialNotFoundException.class)
    public ResponseEntity<ExceptionDetails> tutorialNotFoundExceptionHandler(TutorialNotFoundException exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Tutorial not found")
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );
    }
}
