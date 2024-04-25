package de.rieckpil.service;

import de.rieckpil.dto.WizardCreationRequestDto;
import de.rieckpil.dto.WizardDto;
import de.rieckpil.entity.Wizard;
import de.rieckpil.repository.WizardRepository;
import de.rieckpil.utility.SortingHatUtility;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WizardService {

  private final WizardRepository wizardRepository;
  private final SortingHatUtility sortingHatUtility;

  public List<WizardDto> retrieve() {
    return wizardRepository.findAll().stream().map(this::convert).toList();
  }

  public void create(@NonNull final WizardCreationRequestDto wizardCreationRequest) {
    final var house = sortingHatUtility.sort();
    final var wizard = new Wizard();
    wizard.setName(wizardCreationRequest.getName());
    wizard.setWandType(wizardCreationRequest.getWandType());
    wizard.setHouseId(house.getId());
    wizardRepository.save(wizard);
  }

  private WizardDto convert(@NonNull final Wizard wizard) {
    final var dto = new WizardDto();
    dto.setId(wizard.getId());
    dto.setName(wizard.getName());
    dto.setHouse(wizard.getHouse().getName());
    dto.setWandType(wizard.getWandType());
    return dto;
  }
}
