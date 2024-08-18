package api.passwordService.mappers;

import api.passwordService.dtos.PasswordDTO;
import api.passwordService.entities.Password;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PasswordMapper {


  @Mapping(source = "site.description", target = "site")
  PasswordDTO toDto(Password password);
  @Mapping(source = "site", target = "site.description")
  Password toEntity(PasswordDTO passwordDTO);

  List<PasswordDTO> toDto(List<Password> passwords);

}
