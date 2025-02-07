package api.passwordService.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordAddDTO {
  @Schema(example = "123456")
  private String password;
  @Schema(example = "Facebook")
  private String site;
  @Schema(example = "hola@gmail.com")
  private String siteUser;
}
