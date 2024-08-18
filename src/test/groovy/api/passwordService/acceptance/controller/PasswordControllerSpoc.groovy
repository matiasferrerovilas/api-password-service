package api.passwordService.acceptance.controller

import api.passwordService.acceptance.config.ControllerSpecification
import api.passwordService.entities.Password
import api.passwordService.entities.Site
import org.springframework.http.HttpStatus

class PasswordControllerSpoc extends ControllerSpecification {

    def "Recuperar los sitios del usuario"() {
        given: ""
        createPassword("Git")

        when: "Un usuario quiere ver sus passwords"
        def response = get("/v1/password")

        then: "Retorna HttpStatus OK y y la lista de passwords"

        response.status == HttpStatus.OK.value()
        response.body.size() == 1

    }

    Password createPassword(String description) {
        Site site = siteRepository.save(Site.builder()
                .description(description)
                .build()
        )
        return passwordRepository.save(
                Password.builder()
                        .password("random1")
                        .site(site)
                        .userId(1L)
                        .build())
    }
}
