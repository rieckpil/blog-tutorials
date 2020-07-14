package de.rieckpil.blog;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethods {

  public static void main(String[] args) {
    final List<String> names = List.of("Mike", "Duke", "Paul");

    final Map<Integer, String> user = Map.of(1, "Mike", 2, "Duke");
    final Map<Integer, String> admins = Map.ofEntries(Map.entry(1, "Tom"), Map.entry(2, "Paul"));

    final Set<String> server = Set.of("WildFly", "Open Liberty", "Payara", "TomEE");

    // names.add("Tom"); throws java.lang.UnsupportedOperationException -> unmodifiable collection is created
  }

}
