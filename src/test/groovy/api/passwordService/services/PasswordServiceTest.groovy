package api.passwordService.services

import api.passwordService.dtos.PasswordDTO
import api.passwordService.entities.Password
import api.passwordService.entities.Site
import api.passwordService.exceptions.BusinessException
import api.passwordService.mappers.PasswordMapper
import api.passwordService.repositories.PasswordRepository
import spock.lang.Specification

class PasswordServiceTest extends Specification {

    PasswordRepository passwordRepository = Mock()
    PasswordService passwordService
    PasswordMapper passwordMapper = Mock()
    SiteService siteService = Mock()
    def setup(){
        passwordService = new PasswordService(passwordRepository,passwordMapper,siteService)
    }

    def "Un usuario quiere listado de passwords que tiene"() {
        given: "Un Usuario"

        Long usuarioId  = 1L
        def password = createPassword()
        def passwordDTO  = createPasswordDTO()

        when: "Solicita todas sus passwords"
        def response = passwordService.getAllPasswords()

        then: "Retorna un listado de sus passwords"

        1 * passwordRepository.findByUserId(usuarioId) >> List.of(password)
        1 * passwordMapper.toDto(List.of(password)) >> List.of(passwordDTO)

        response.size() == 1
    }

    def "Un usuario quiere su listado de passwords pero no tiene ninguna"() {
        given: "Un Usuario"

        Long usuarioId  = 1L

        when: "Solicita todas sus passwords"
        def response = passwordService.getAllPasswords()

        then: "Retorna un listado vacio"

        1 * passwordRepository.findByUserId(usuarioId) >> List.of()
        1 * passwordMapper.toDto(List.of()) >> List.of()

        response.size() == 0
    }

    def "Un usuario guarda su passwords correctamente"() {
        given: "Una password"

        def dto = createPasswordDTO()

        when: "Solicita guardar su password"
        passwordService.savePassword(dto)

        then: "Guarda correctamente la password en la base de datos"

        1 * passwordRepository.getByPasswordAndSiteAndUserId(dto.getPassword(), dto.getSite(), dto.getUserId()) >> Optional.empty()
        1 * siteService.saveSite(dto.getSite()) >> Site.builder().build()

        1 * passwordRepository.save({p ->
            p.password == dto.getPassword()
            p.userId == dto.getUserId()
        })
    }

    def "Un usuario intenta guardar una password que ya tenia"() {
        given: "Una password"

        def dto = createPasswordDTO()
        def password = createPassword()
        when: "Solicita guardar su password"
        passwordService.savePassword(dto)

        then: "No guarda su password dado que ya estaba"

        1 * passwordRepository.getByPasswordAndSiteAndUserId(dto.getPassword(), dto.getSite(), dto.getUserId()) >> Optional.of(password)

        0 * siteService.saveSite(_)
        0 * passwordRepository.save(_)
        thrown(BusinessException)
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
                .userId(1L)
                .build()
    }

}
