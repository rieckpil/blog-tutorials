package de.rieckpil.blog;

import org.jetbrains.annotations.Nullable;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.constructs.Construct;

public class ApplicationStack extends Stack {

  public ApplicationStack(@Nullable Construct scope, @Nullable String id, @Nullable StackProps props) {
    super(scope, id, props);
  }

}
