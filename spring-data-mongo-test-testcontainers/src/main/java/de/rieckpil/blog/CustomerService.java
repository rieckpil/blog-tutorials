package de.rieckpil.blog;

import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final MongoTemplate mongoTemplate;

  public CustomerService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public List<Customer> findAllVIPCustomers() {
    return mongoTemplate.find(Query.query(Criteria.where("rating").gte(90)), Customer.class);
  }
}
