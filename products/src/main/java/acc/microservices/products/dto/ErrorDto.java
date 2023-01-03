package acc.microservices.products.dto;

import java.util.Map;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
  private String message;
  private List<Map<String, String>> errors;
}
