package de.rieckpil.blog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/validation")
public class ValidationController {

  @GetMapping("/{message}")
  public String validateParameters(
    @PathVariable("message") @Size(min = 5, max = 10) String message,
    @RequestParam("size") @Positive Long size) {

    return "Query parameters where valid";
  }

@PostMapping
public String validatePayload(@Valid @RequestBody Payload payload) {
  return "Payload is valid";
}

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
    return new ResponseEntity<>("Validation Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}

