package de.rieckpil.blog;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  @Query(sort = "{ rating : 1 }")
  List<Customer> findByRatingBetween(int from, int to);
}
