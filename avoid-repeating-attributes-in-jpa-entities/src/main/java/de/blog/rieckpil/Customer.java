package de.blog.rieckpil;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Customer extends BaseEnterpriseEntity {

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 40)
  private String customerId;
}
