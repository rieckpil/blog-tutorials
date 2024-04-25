package de.rieckpil.repository;

import de.rieckpil.entity.HogwartsHouse;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HogwartsHouseRepository extends JpaRepository<HogwartsHouse, UUID> {}
