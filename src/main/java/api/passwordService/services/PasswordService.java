package api.passwordService.services;

import api.passwordService.dtos.PasswordDTO;
import api.passwordService.entities.Password;
import api.passwordService.exceptions.BusinessException;
import api.passwordService.mappers.PasswordMapper;
import api.passwordService.repositories.PasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PasswordService {

  private final PasswordRepository passwordRepository;
  private final PasswordMapper passwordMapper;
  private final SiteService siteService;

  public PasswordService(PasswordRepository passwordRepository, PasswordMapper passwordMapper, SiteService siteService) {
    this.passwordRepository = passwordRepository;
    this.passwordMapper = passwordMapper;
    this.siteService = siteService;
  }

  public List<PasswordDTO> getAllPasswords() {
    var list = passwordRepository.findByUserId(1L);
    return passwordMapper.toDto(list);
  }

  public void savePassword(PasswordDTO passwordDTO) {
    log.info("Intentando guardar password {} del sitio {} y usuario {}", passwordDTO.getPassword(), passwordDTO.getSite(), passwordDTO.getUserId());

    validateExistencePassword(passwordDTO);

    var site = siteService.saveSite(passwordDTO.getSite());

    passwordRepository.save(
        Password.builder()
        .password(passwordDTO.getPassword())
        .userId(passwordDTO.getUserId())
        .site(site)
        .build());
  }

  private void validateExistencePassword(PasswordDTO passwordDTO) {
    passwordRepository.getByPasswordAndSiteAndUserId(passwordDTO.getPassword(), passwordDTO.getSite(), passwordDTO.getUserId())
        .ifPresent(p -> {
          throw new BusinessException("Password existente en la base de datos.");
        });
  }
}
