package de.rieckpil.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.rieckpil.dto.WizardCreationRequestDto;
import de.rieckpil.helper.InitializeMysqlContainer;
import de.rieckpil.helper.InitializeRedisContainer;
import de.rieckpil.repository.WizardRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;

@SpringBootTest
@InitializeRedisContainer
@InitializeMysqlContainer
class WizardServiceIT {

  @Autowired private WizardService wizardService;

  @SpyBean private WizardRepository wizardRepository;

  @Autowired private CacheManager cacheManager;

  @BeforeEach
  void setup() {
    cacheManager.getCache("wizards").invalidate();
  }

  @Test
  void shouldRetrieveWizardRecordFromCacheAfterInitialDatabaseRetrieval() {
    // Invoke method under test for the first time
    var wizards = wizardService.retrieve();
    assertThat(wizards).isNotEmpty();

    // Verify database interaction
    verify(wizardRepository, times(1)).findAll();
    Mockito.clearInvocations(wizardRepository);

    // Verify subsequent reads are made from cache and database is not queried
    final var queryTimes = 100;
    for (int i = 1; i < queryTimes; i++) {
      wizards = wizardService.retrieve();
      assertThat(wizards).isNotEmpty();
    }
    verify(wizardRepository, times(0)).findAll();
  }

  @Test
  void shouldInvalidateCachePostWizardCreation() {
    // Populate cache by retrieving wizard records
    var wizards = wizardService.retrieve();
    assertThat(wizards).isNotEmpty();

    // Prepare wizard creation request
    final var name = RandomString.make();
    final var wandType = RandomString.make();
    final var wizardCreationRequest = new WizardCreationRequestDto();
    wizardCreationRequest.setName(name);
    wizardCreationRequest.setWandType(wandType);

    // Invoke method under test
    wizardService.create(wizardCreationRequest);

    // Retrieve wizards post creation and verify database interaction
    Mockito.clearInvocations(wizardRepository);
    wizardService.retrieve();
    verify(wizardRepository, times(1)).findAll();
  }
}
