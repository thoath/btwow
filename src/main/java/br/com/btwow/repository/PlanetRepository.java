package br.com.btwow.repository;

import br.com.btwow.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

  List<Planet> findByNameIgnoreCase(String name);
}
