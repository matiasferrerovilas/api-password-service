package api.passwordService.services

import api.passwordService.dtos.SiteDTO
import api.passwordService.entities.Site
import api.passwordService.exceptions.BusinessException
import api.passwordService.repositories.SiteRepository
import spock.lang.Specification

class ServiceTest extends Specification {

    SiteRepository siteRepository = Mock()
    SiteService siteService

    def setup() {
        siteService = new SiteService(siteRepository)
    }

    def "Intento guardar un Site que ya existe"() {
        given: "Una password"

        def dto = createSiteDTO()
        def site = createSite()
        when: "Solicita guardar su password"
        siteService.saveSite(dto.getDescription())

        then: "No guarda el site dado que ya estaba"

        1 * siteRepository.getByDescription(dto.getDescription()) >> Optional.of(site)
        0 * siteService.saveSite(_)
    }

    def "Guardo un Site dado que no existe"() {
        given: "Una password"

        def dto = createSiteDTO()

        when: "Solicita guardar su password"
        siteService.saveSite(dto.getDescription())

        then: "No guarda el site dado que ya estaba"

        1 * siteRepository.getByDescription(dto.getDescription()) >> Optional.empty()
        1 * siteRepository.save({r -> r.description == dto.getDescription()})
    }

    SiteDTO createSiteDTO(){
        return SiteDTO.builder()
                .description("Site")
                .build()
    }
    Site createSite(){
        return Site.builder()
                .description("Site")
                .build()
    }
}
