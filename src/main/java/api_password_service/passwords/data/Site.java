package api_password_service.passwords.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Site {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String descripcion;
}
