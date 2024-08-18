package api.passwordService.services;

import api.passwordService.entities.Site;
import api.passwordService.exceptions.BusinessException;
import api.passwordService.repositories.SiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SiteService {

  private final SiteRepository siteRepository;

  public SiteService(SiteRepository siteRepository) {
    this.siteRepository = siteRepository;
  }

  public Site saveSite(String description) {
      return siteRepository.getByDescription(description)
        .orElseGet(() ->siteRepository.save(Site.builder()
            .description(description)
            .build())
        );
  }

  public void delete(String description) {
    Optional<Site> optional = siteRepository.findByDescription(description);
    siteRepository.delete(optional.get());
  }
}
