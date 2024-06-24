package api_password_service.passwords.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table
public class Password {
  private Long id;
  private String password;
  private Long siteId;
}
