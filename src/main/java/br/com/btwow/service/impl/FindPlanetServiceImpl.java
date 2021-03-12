package br.com.btwow.service.impl;

import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.FindPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindPlanetServiceImpl implements FindPlanetService {

  @Autowired
  private PlanetRepository repository;

  @Override
  public Optional<Planet> executeById(String planetId) {
    return repository.findById(planetId);
  }

  @Override
  public List<Planet> executeByName(String planetName) {
    return repository.findByNameIgnoreCase(planetName);
  }
}
