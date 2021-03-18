package br.com.btwow.service.impl;

import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.FindPlanetService;
import br.com.btwow.service.RemovePlanetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RemovePlanetServiceImpl implements RemovePlanetService {

  @Autowired
  private PlanetRepository repository;

  @Autowired
  private FindPlanetService findPlanetService;

  @Override
  public void execute(String planetId) {
    findPlanetService.executeById(planetId);
    repository.deleteById(planetId);
  }
}
