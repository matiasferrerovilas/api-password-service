package api_password_service.passwords.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ps")
@Api(tags = "PS Service", description = "Password Service M1")
@Slf4j
public class PasswordController {
}
