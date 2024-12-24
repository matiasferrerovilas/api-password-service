package api.passwordService.services;

import api.passwordService.dtos.PasswordAddDTO;
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

  public List<PasswordAddDTO> getAllPasswords() {
    var list = passwordRepository.findByUserId(1L);
    return passwordMapper.toDto(list);
  }

  public void savePassword(PasswordAddDTO passwordAddDTO) {
    log.info("Intentando guardar password {} del sitio {} y usuario {}", passwordAddDTO.getPassword(), passwordAddDTO.getSite(), passwordAddDTO.getUserId());

    validateExistencePassword(passwordAddDTO);

    var site = siteService.saveSite(passwordAddDTO.getSite());

    var password = passwordMapper.toEntity(passwordAddDTO);
    password.setSite(site);

    passwordRepository.save(password);
  }

  private void validateExistencePassword(PasswordAddDTO passwordAddDTO) {
    passwordRepository.getByPasswordAndSiteAndUserId(passwordAddDTO.getPassword(), passwordAddDTO.getSite(), passwordAddDTO.getUserId())
        .ifPresent(p -> {
          throw new BusinessException("Password existente en la base de datos.");
        });
  }
}
