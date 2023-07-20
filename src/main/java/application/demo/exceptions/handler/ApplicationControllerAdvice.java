package application.demo.exceptions.handler;

import application.demo.exceptions.BusinessException;
import application.demo.exceptions.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import application.demo.exceptions.handler.ErrorHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardException> handleExceptions(BusinessException e, WebRequest request){
      StandardException ex = new StandardException
            (LocalDateTime.now(),
                  e.getMessage(),
                request.getDescription(false));

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorHandler>> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException e) {

        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();

        List<ErrorHandler> list = new ArrayList<>();

        fieldErrorList.forEach(error -> {
            list.add(new ErrorHandler(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }

}