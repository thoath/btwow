package br.com.btwow.service.impl;

import br.com.btwow.api.SWPlanetsApiSearch;
import br.com.btwow.dto.PlanetDto;
import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.CreatePlanetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlanetServiceImpl implements CreatePlanetService {

  @Autowired private PlanetRepository repository;

  @Autowired private ModelMapper modelMapper;

  @Autowired private SWPlanetsApiSearch planetsApiSearch;

  @Override
  public PlanetDto execute(PlanetDto planet) {

    Planet planetModel = modelMapper.map(planet, Planet.class);

    return planetsApiSearch.modelPlanetDto(repository.save(planetModel));
  }
}
