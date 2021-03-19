package br.com.btwow.api;

import br.com.btwow.dto.PlanetDto;
import br.com.btwow.model.Planet;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class SWPlanetsApiSearch {

  @Autowired private SWPlanetsApiClient api;

  @Autowired private ModelMapper modelMapper;

  private long getFilmCount(String planetName) {

    return api.getPlanet(planetName).stream()
        .flatMap(result -> result.getResponseResultDto().stream())
        .filter(result -> result.getName().equals(planetName))
        .mapToLong(result -> result.getFilms().stream().count())
        .sum();
  }

  public PlanetDto modelPlanetDto(Planet planet) {

    PlanetDto planetDto = modelMapper.map(planet, PlanetDto.class);

    planetDto.setFilms(getFilmCount(planetDto.getName()));

    return planetDto;
  }
}
