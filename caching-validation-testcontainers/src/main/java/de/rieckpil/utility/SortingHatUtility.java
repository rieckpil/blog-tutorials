package de.rieckpil.utility;

import de.rieckpil.entity.HogwartsHouse;
import de.rieckpil.repository.HogwartsHouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SortingHatUtility {

  private final HogwartsHouseRepository hogwartsHouseRepository;

  public HogwartsHouse sort() {
    return hogwartsHouseRepository.fetchRandom().orElseThrow(IllegalStateException::new);
  }
}
