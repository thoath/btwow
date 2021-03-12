package br.com.btwow.service.impl;

import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.ListPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPlanetServiceImpl implements ListPlanetService {

  @Autowired
  private PlanetRepository repository;

  @Override
  public List<Planet> execute() {
    return repository.findAll();
  }
}
