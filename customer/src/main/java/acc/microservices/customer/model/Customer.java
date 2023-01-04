package acc.microservices.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "El número de documento no puede ser vacío.")
  @Size(min = 10, max = 10, message = "Tamano de CI debe ser 10.")
  @Column(unique = true, length = 10, nullable = false)
  private String CI;

  @NotEmpty(message = "El nombre no puede ser vacío.")
  private String firstName;

  @NotEmpty(message = "El apellido no puede ser vacío.")
  private String lastName;

  @NotEmpty(message = "El email no puede estar vacío.")
  @Email(message = "Email invalido.")
  private String email;

  @NotNull(message = "La región no puede ser vacia.")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_region")
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  private Region region;

  private String state;
}
