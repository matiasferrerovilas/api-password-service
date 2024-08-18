package api.passwordService.services

import api.passwordService.dtos.PasswordDTO
import api.passwordService.entities.Password
import api.passwordService.entities.Site
import api.passwordService.mappers.PasswordMapper
import api.passwordService.repositories.PasswordRepository
import spock.lang.Specification

class PasswordServiceTest extends Specification {

    PasswordRepository passwordRepository = Mock()
    PasswordService passwordService
    PasswordMapper passwordMapper = Mock()

    def setup(){
        passwordService = new PasswordService(passwordRepository,passwordMapper)
    }

    def "Un usuario quiere listado de passwords que tiene"() {
        given: "Un Usuario"

        Long usuarioId  = 1L
        def password = createPassword()
        def passwordDTO  = createPasswordDTO()

        when: "Solicita todas sus passwords"
        def response = passwordService.getAllPasswords()

        then: "Retorna un listado de sus passwords y codigo OK"

        1 * passwordRepository.findByUserId(usuarioId) >> List.of(password)
        1 * passwordMapper.toDto(List.of(password)) >> List.of(passwordDTO)

        response.size() == 1
    }

    Password createPassword(){
       return Password.builder()
                .password("random1")
               .site(Site.builder()
                       .description("Git")
                       .build())
               .build()
    }

    PasswordDTO createPasswordDTO(){
        return PasswordDTO.builder()
                .password("random1")
                .site("Git")
                .build()
    }

}
