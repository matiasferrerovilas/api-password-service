package api.passwordService.services;

import api.passwordService.dtos.GetAllPasswordsDTO;
import api.passwordService.dtos.PasswordAddDTO;
import api.passwordService.mappers.PasswordMapper;
import api.passwordService.repositories.PasswordRepository;
import jakarta.persistence.EntityExistsException;
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

  public List<GetAllPasswordsDTO> getAllPasswords() {
    return this.passwordMapper.toDto(passwordRepository.findByOwner(userService.getLoggedInUserEmail()));
  }

  public void savePassword(PasswordAddDTO passwordAddDTO) {
    log.info("Intentando guardar password {} del sitio {}", passwordAddDTO.getPassword(), passwordAddDTO.getSite());

    String usuario = userService.getLoggedInUserEmail();
    validateExistencePassword(passwordAddDTO, usuario);

    var site = siteService.saveSite(passwordAddDTO.getSite());

    var password = passwordMapper.toEntity(passwordAddDTO);
    password.setSite(site);
    password.setOwner(usuario);
    password.setPassword(passwordEncoder.encode(password.getPassword()));
    passwordRepository.save(password);
    log.info("Password guardada para el usuario {}", usuario);
  }

  private void validateExistencePassword(PasswordAddDTO passwordAddDTO, String logInUser) {
    passwordRepository.getByPasswordAndSiteAndUser(passwordAddDTO.getSite(), passwordAddDTO.getSiteUser(), logInUser)
        .ifPresent(p -> {
          throw new EntityExistsException("Password existente en la base de datos.");
        });
  }
}
