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
  List<Password> findByUserId(Long userId);


  @Query("SELECT p FROM Password p WHERE p.password = :password AND p.site.description = :description AND p.userId = :userId")
  Optional<Password> getByPasswordAndSiteAndUserId(@Param("password") String password,
                                                      @Param("description") String description,
                                                      @Param("userId") Long userId);
}
