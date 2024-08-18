package api.passwordService.services;

import api.passwordService.dtos.PasswordDTO;
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

  public PasswordService(PasswordRepository passwordRepository, PasswordMapper passwordMapper) {
    this.passwordRepository = passwordRepository;
    this.passwordMapper = passwordMapper;
  }

  public List<PasswordDTO> getAllPasswords() {
    var list = passwordRepository.findByUserId(1L);
    return passwordMapper.toDto(list);
  }
}
