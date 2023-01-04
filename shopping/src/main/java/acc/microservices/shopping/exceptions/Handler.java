package acc.microservices.shopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import acc.microservices.shopping.dto.ErrorDto;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
  private ErrorDto errorDto = ErrorDto.builder().build();

  @ExceptionHandler(value = NotFoundException.class)
  protected ResponseEntity<ErrorDto> handleNotFoundException(RuntimeException ex) {
    errorDto.setMessage(ex.getMessage());
    errorDto.setErrors(null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }

  @ExceptionHandler(value = ValidationErrorsException.class)
  protected ResponseEntity<ErrorDto> handleValidationErrorException(ValidationErrorsException ex) {
    errorDto.setMessage(ex.getMessage());
    errorDto.setErrors(ex.buildErrorsList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }
}
