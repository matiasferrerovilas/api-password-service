package api.passwordService.repositories;

import api.passwordService.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<Password,Long> {
  List<Password> findByUserId(Long userId);
}
