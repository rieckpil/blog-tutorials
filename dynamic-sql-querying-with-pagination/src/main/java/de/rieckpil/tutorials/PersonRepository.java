package de.rieckpil.tutorials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersonRepository extends JpaRepository<Person, Long>, QueryByExampleExecutor<Person>,
		JpaSpecificationExecutor<Person>, QuerydslPredicateExecutor<Person> {

}
