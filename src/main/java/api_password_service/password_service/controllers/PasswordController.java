package api_password_service.password_service.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ps")
@Tag(name = "Password", description = "Request passwords information.")
@Slf4j
public class PasswordController {
}
