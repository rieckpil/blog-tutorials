package de.rieckpil.repository;

import de.rieckpil.entity.HogwartsHouse;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HogwartsHouseRepository extends JpaRepository<HogwartsHouse, UUID> {

  @Query(value = "SELECT * FROM hogwarts_houses ORDER BY RAND() LIMIT 1", nativeQuery = true)
  Optional<HogwartsHouse> fetchRandom();
}
