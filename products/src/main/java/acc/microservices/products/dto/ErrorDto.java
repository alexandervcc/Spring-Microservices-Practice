package acc.microservices.products.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
  private String error;
}
