package api.passwordService.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordDTO {
  private String password;
  private String site;
  private Long userId;
}
