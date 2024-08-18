package api.passwordService.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SiteDTO {
  @Schema(example = "Facebook")
  private String description;
}
