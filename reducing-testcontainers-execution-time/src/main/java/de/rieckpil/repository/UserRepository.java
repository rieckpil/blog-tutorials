package de.rieckpil.repository;

import de.rieckpil.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Boolean existsByEmailId(final String emailId);
}
