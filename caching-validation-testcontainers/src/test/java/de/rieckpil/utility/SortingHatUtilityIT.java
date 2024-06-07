package de.rieckpil.utility;

import static org.assertj.core.api.Assertions.assertThat;

import de.rieckpil.entity.HogwartsHouse;
import de.rieckpil.helper.InitializeMysqlContainer;
import de.rieckpil.repository.HogwartsHouseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@InitializeMysqlContainer
class SortingHatUtilityIT {

  @Autowired private HogwartsHouseRepository hogwartsHouseRepository;

  private SortingHatUtility sortingHatUtility;

  @BeforeEach
  void setup() {
    sortingHatUtility = new SortingHatUtility(hogwartsHouseRepository);
  }

  @Test
  void shouldFetchRandomHouseRecordFromDataSource() {
    // Invoke method under test
    HogwartsHouse hogwartsHouse = sortingHatUtility.sort();

    // assert response attributes
    assertThat(hogwartsHouse)
        .isNotNull()
        .satisfies(
            house -> {
              assertThat(house.getId()).isNotNull();
              assertThat(house.getName()).isNotBlank();
            });
  }

  @Test
  void shouldNotFetchSameHouseRecordForEachInvocation() {
    // Retrieve IDs of all houses present in the data source
    final List<UUID> houseIds =
        hogwartsHouseRepository.findAll().stream().map(HogwartsHouse::getId).toList();
    assertThat(houseIds).isNotEmpty().hasSizeGreaterThan(1);

    // Invoke method under test multiple times
    final var batchSize = 100;
    final var sortedHouses = new ArrayList<HogwartsHouse>();
    for (int i = 0; i < batchSize; i++) {
      final var house = sortingHatUtility.sort();
      sortedHouses.add(house);
    }

    // Ensure sorted houses contains all Ids present in datasource
    final var totalHouses = houseIds.size();
    List<UUID> uniqueHouseIds = sortedHouses.stream().map(HogwartsHouse::getId).distinct().toList();
    assertThat(uniqueHouseIds).hasSize(totalHouses).containsAll(houseIds);
  }
}
