package api.passwordService.services;

import api.passwordService.dtos.PasswordAddDTO;
import api.passwordService.exceptions.BusinessException;
import api.passwordService.mappers.PasswordMapper;
import api.passwordService.repositories.PasswordRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PasswordService {

  private final PasswordRepository passwordRepository;
  private final PasswordMapper passwordMapper;
  private final SiteService siteService;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public List<PasswordAddDTO> getAllPasswords() {
    var list = passwordRepository.findByUser(userService.getLoggedInUserEmail());
    return passwordMapper.toDto(list);
  }

  public void savePassword(PasswordAddDTO passwordAddDTO) {
    log.info("Intentando guardar password {} del sitio {}", passwordAddDTO.getPassword(), passwordAddDTO.getSite());

    String usuario = userService.getLoggedInUserEmail();
    passwordAddDTO.setUser(usuario);
    validateExistencePassword(passwordAddDTO);

    var site = siteService.saveSite(passwordAddDTO.getSite());

    var password = passwordMapper.toEntity(passwordAddDTO);
    password.setSite(site);
    password.setUser(usuario);
    password.setPassword(passwordEncoder.encode(password.getPassword()));
    passwordRepository.save(password);
    log.info("Password guardada para el usuario {}", usuario);
  }

  private void validateExistencePassword(PasswordAddDTO passwordAddDTO) {
    passwordRepository.getByPasswordAndSiteAndUser(passwordAddDTO.getSite(), passwordAddDTO.getUser())
        .ifPresent(p -> {
          throw new BusinessException("Password existente en la base de datos.");
        });
  }
}
