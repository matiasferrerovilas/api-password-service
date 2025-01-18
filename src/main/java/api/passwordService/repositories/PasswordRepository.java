package api.passwordService.repositories;

import api.passwordService.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

  List<Password> findByOwner(String owner);

  @Query("SELECT p FROM Password p WHERE p.site.description = :description AND p.siteUser = :siteUser AND p.owner = :owner")
  Optional<Password> getByPasswordAndSiteAndUser(@Param("description") String description,
                                                      @Param("siteUser") String siteUser, @Param("owner") String owner);
}
