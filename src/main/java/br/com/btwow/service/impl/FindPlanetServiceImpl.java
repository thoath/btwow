package br.com.btwow.service.impl;

import br.com.btwow.api.SWPlanetsApiSearch;
import br.com.btwow.dto.PlanetDto;
import br.com.btwow.exception.PlanetNotFoundException;
import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.FindPlanetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FindPlanetServiceImpl implements FindPlanetService {

  @Autowired
  private PlanetRepository repository;

  @Autowired
  private SWPlanetsApiSearch planetsApiSearch;

  @Override
  public PlanetDto executeById(String planetId) {

    Optional<Planet> planet = repository.findById(planetId);

    if (planet.isPresent()) {
      return planetsApiSearch.modelPlanetDto(planet.get());
    }

    throw new PlanetNotFoundException();
  }

  @Override
  public List<PlanetDto> executeByName(String planetName, int page, int size) {

    final Pageable pageable = PageRequest.of(page,size);
    List<Planet> planets = repository
            .findByNameIgnoreCase(planetName, pageable).stream().collect(Collectors.toList());

    if (CollectionUtils.isEmpty(planets)) {
      throw new PlanetNotFoundException();
    }

    return planets.stream()
            .map(planetsApiSearch::modelPlanetDto)
            .collect(Collectors.toList());
  }
}
