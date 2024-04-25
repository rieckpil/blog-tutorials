package de.rieckpil.repository;

import de.rieckpil.entity.Wizard;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends JpaRepository<Wizard, UUID> {}
