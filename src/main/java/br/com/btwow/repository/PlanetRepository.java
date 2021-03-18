package br.com.btwow.repository;

import br.com.btwow.model.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

  Page<Planet> findByNameIgnoreCase(String name, Pageable pageable);
}
