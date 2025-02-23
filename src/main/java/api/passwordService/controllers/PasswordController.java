package api.passwordService.controllers;

import api.passwordService.dtos.GetAllPasswordsDTO;
import api.passwordService.dtos.PasswordAddDTO;
import api.passwordService.services.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/password")
@Tag(name = "Password", description = "Manejo de passwords.")
@Slf4j
public class PasswordController {

  private final PasswordService passwordService;

  public PasswordController(PasswordService passwordService) {
    this.passwordService = passwordService;
  }

  @Operation(description = "Retornar Todas las Passwords del usuario logueado")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public List<GetAllPasswordsDTO> getPasswords()  {
      return passwordService.getAllPasswords();
  }

  @Operation(description = "Guardar password para usuario logueado")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public void savePassword(@RequestBody PasswordAddDTO passwordAddDTO)  {
    passwordService.savePassword(passwordAddDTO);
  }
}
