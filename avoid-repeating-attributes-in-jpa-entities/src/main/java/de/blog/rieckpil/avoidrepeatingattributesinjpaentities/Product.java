package de.blog.rieckpil.avoidrepeatingattributesinjpaentities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Product extends BaseEnterpriseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    private int amount;

}
