package de.rieckpil.blog;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

public class DisabledOnMidnightCondition implements ExecutionCondition {

  private static final ConditionEvaluationResult ENABLED_BY_DEFAULT =
    enabled("@DisabledOnMidnight is not present");

  private static final ConditionEvaluationResult ENABLED_DURING_DAYTIME =
    enabled("Test is enabled during daytime");

  private static final ConditionEvaluationResult DISABLED_ON_MIDNIGHT =
    disabled("Disabled as it is around midnight");

  @Documented
  @Target({ElementType.TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  @ExtendWith(DisabledOnMidnightCondition.class)
  public @interface DisabledOnMidnight {
  }

  @Override
  public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
    Optional<DisabledOnMidnight> optional = findAnnotation(context.getElement(), DisabledOnMidnight.class);
    if (optional.isPresent()) {
      LocalDateTime now = LocalDateTime.now();
      if (now.getHour() == 23 || now.getHour() <= 1) {
        return DISABLED_ON_MIDNIGHT;
      } else {
        return ENABLED_DURING_DAYTIME;
      }
    }
    return ENABLED_BY_DEFAULT;
  }
}
