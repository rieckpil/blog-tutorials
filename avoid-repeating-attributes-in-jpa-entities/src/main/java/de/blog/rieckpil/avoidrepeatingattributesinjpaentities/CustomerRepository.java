package de.blog.rieckpil.avoidrepeatingattributesinjpaentities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
