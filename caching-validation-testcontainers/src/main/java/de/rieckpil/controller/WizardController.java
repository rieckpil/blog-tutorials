package de.rieckpil.controller;

import de.rieckpil.dto.WizardCreationRequestDto;
import de.rieckpil.dto.WizardDto;
import de.rieckpil.service.WizardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wizards")
public class WizardController {

  private final WizardService wizardService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<WizardDto>> retrieve() {
    final var response = wizardService.retrieve();
    return ResponseEntity.ok(response);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> createWizard(
      @RequestBody final WizardCreationRequestDto wizardCreationRequest) {
    wizardService.create(wizardCreationRequest);
    return ResponseEntity.ok().build();
  }
}
