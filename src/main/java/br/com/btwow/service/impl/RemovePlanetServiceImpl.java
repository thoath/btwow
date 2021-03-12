package br.com.btwow.service.impl;

import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.RemovePlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RemovePlanetServiceImpl implements RemovePlanetService {

  @Autowired
  private PlanetRepository repository;

  @Autowired
  private FindPlanetServiceImpl findPlanetService;

  @Override
  public boolean execute(String planetId) {

    Optional<Planet> planet = findPlanetService.executeById(planetId);

    if (planet.isPresent()) {
      repository.deleteById(planetId);
      return true;
    }

    return false;
  }
}
