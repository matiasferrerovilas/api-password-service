package api.passwordService.services


import api.passwordService.dtos.PasswordAddDTO
import api.passwordService.entities.Password
import api.passwordService.entities.Site
import api.passwordService.mappers.PasswordMapper
import api.passwordService.repositories.PasswordRepository
import jakarta.persistence.EntityExistsException
import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class PasswordServiceTest extends Specification {

    PasswordRepository passwordRepository = Mock()
    PasswordService passwordService
    PasswordMapper passwordMapper = Mappers.getMapper(PasswordMapper)
    SiteService siteService = Mock()
    UserService userService = Mock()
    PasswordEncoder passwordEncoder = Mock()

    def setup() {
        passwordService = new PasswordService(passwordRepository, passwordMapper, siteService, userService, passwordEncoder)
    }

    def "Un usuario quiere listado de passwords que tiene"() {
        given: "Un Usuario"

        String usuarioId = "example"
        def password = createPassword()
        def passwordDTO = createPasswordDTO()

        when: "Solicita todas sus passwords"
        def response = passwordService.getAllPasswords()

        then: "Retorna un listado de sus passwords"
        1 * userService.getLoggedInUserEmail() >> usuarioId
        1 * passwordRepository.findByOwner(usuarioId) >> List.of(password)

        response.size() == 1
    }

    def "Un usuario quiere su listado de passwords pero no tiene ninguna"() {
        given: "Un Usuario"

        String usuarioId = "example"

        when: "Solicita todas sus passwords"
        def response = passwordService.getAllPasswords()

        then: "Retorna un listado vacio"
        1 * userService.getLoggedInUserEmail() >> usuarioId
        1 * passwordRepository.findByOwner(usuarioId) >> List.of()

        response.size() == 0
    }

    def "Un usuario guarda su passwords correctamente"() {
        given: "Una password"

        def dto = createPasswordDTO()

        when: "Solicita guardar su password"
        passwordService.savePassword(dto)

        then: "Guarda correctamente la password en la base de datos"
        1 * userService.getLoggedInUserEmail() >> "ejemplo"
        1 * passwordRepository.getByPasswordAndSiteAndUser(dto.getSite(), dto.getSiteUser(),"ejemplo") >> Optional.empty()
        1 * siteService.saveSite(dto.getSite()) >> Site.builder().description("GIT").id(1L).build()
        1 * passwordEncoder.encode(dto.getPassword()) >> "qwerty"
        1 * passwordRepository.save({ p ->
            p.password == "qwerty"
            p.owner == dto.getSiteUser()
            p.site.id == 1L
        })
    }

    def "Un usuario intenta guardar una password que ya tenia"() {
        given: "Una password"

        def dto = createPasswordDTO()
        def password = createPassword()
        when: "Solicita guardar su password"
        passwordService.savePassword(dto)

        then: "No guarda su password dado que ya estaba"
        1 * userService.getLoggedInUserEmail() >> "ejemplo"
        1 * passwordRepository.getByPasswordAndSiteAndUser( dto.getSite(), dto.getSiteUser(),"ejemplo") >> Optional.of(password)

        0 * siteService.saveSite(_)
        0 * passwordRepository.save(_)
        thrown(EntityExistsException)
    }

    Password createPassword() {
        return Password.builder()
                .password("random1")
                .siteUser("ejemplo")
                .site(Site.builder()
                        .description("Git")
                        .build())
                .build()
    }

    PasswordAddDTO createPasswordDTO() {
        return PasswordAddDTO.builder()
                .password("random1")
                .site("Git")
                .siteUser("ejemplo")
                .build()
    }

}
