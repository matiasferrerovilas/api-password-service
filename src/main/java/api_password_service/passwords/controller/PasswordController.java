package api_password_service.passwords.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ps")
@Api(tags = "PS Service", description = "Password Service M1")
@Slf4j
public class PasswordController {

  @GetMapping
  public ResponseEntity<?> getPasswords()  {
    try{
      //var response = psServiceI.getPasswords();
      return new ResponseEntity<>(true, HttpStatus.OK);
    }
    catch (Exception e){
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
