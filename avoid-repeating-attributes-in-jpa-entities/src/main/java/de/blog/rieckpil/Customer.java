package de.blog.rieckpil;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Customer extends BaseEnterpriseEntity {

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 40)
  private String customerId;

}
