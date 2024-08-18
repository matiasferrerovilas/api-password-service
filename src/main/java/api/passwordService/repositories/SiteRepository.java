package api.passwordService.repositories;

import api.passwordService.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

  Optional<Site> getByDescription(String description);

  Optional<Site> findByDescription(String description);
}
