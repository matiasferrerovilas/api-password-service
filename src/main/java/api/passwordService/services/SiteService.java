package api.passwordService.services;

import api.passwordService.entities.Site;
import api.passwordService.exceptions.BusinessException;
import api.passwordService.repositories.SiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SiteService {

  private final SiteRepository siteRepository;

  public SiteService(SiteRepository siteRepository) {
    this.siteRepository = siteRepository;
  }

  public Site saveSite(String description) {
    return siteRepository.findByDescription(description)
        .orElseGet(() -> siteRepository.save(Site.builder()
            .description(description)
            .build())
        );
  }

  public void deleteSite(String description) {
    siteRepository.findByDescription(description)
        .ifPresentOrElse(siteRepository::delete, () -> {
          throw new BusinessException("Sitio no existente.");
        });
  }
}
