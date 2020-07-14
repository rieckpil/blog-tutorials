package de.rieckpil.blog;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsUpdate {

  public static void main(String[] args) {

    final IntStream intStream1 = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    System.out.println(
      intStream1.takeWhile(n -> n <= 5).mapToObj(Integer::toString).collect(Collectors.joining(", ")));

    final IntStream intStream2 = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    System.out.println(intStream2.dropWhile(n -> n < 5).sum());

    final List<String> names = List.of("Tom", "Paul", "Mike", "Duke", "", "Michelle", "Anna");

    names.stream().takeWhile(s -> !s.isEmpty()).forEach(System.out::println);
  }

}
