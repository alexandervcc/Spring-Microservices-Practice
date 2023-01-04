package acc.microservices.customer.dto;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {

  private String message;
  private List<Map<String, String>> errors;
}
