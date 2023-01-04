package acc.microservices.customer.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class ValidationErrorsException extends RuntimeException {
  private BindingResult validationResult;

  public ValidationErrorsException(String arg0, BindingResult validationResult) {
    super(arg0);
    this.validationResult = validationResult;
  }

  public List<Map<String, String>> buildErrorsList() {
    return validationResult.getFieldErrors().stream().map(e -> {
      Map<String, String> error = new HashMap<>();
      error.put(e.getField(), e.getDefaultMessage());
      return error;
    }).collect(Collectors.toList());
  }
}
