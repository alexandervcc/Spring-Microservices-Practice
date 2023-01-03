package acc.microservices.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import acc.microservices.products.dto.ErrorDto;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
  private ErrorDto errorDto = ErrorDto.builder().build();

  @ExceptionHandler(value = NotFoundException.class)
  protected ResponseEntity<ErrorDto> handleConflict(RuntimeException ex) {
    errorDto.setError(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }
}
