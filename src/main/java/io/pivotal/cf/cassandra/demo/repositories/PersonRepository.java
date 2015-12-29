package io.pivotal.cf.cassandra.demo.repositories;

import io.pivotal.cf.cassandra.demo.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {
}
