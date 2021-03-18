package br.com.btwow.service;

import static org.mockito.Mockito.*;

import br.com.btwow.api.SWPlanetsApiClient;
import br.com.btwow.api.SWPlanetsApiSearch;
import br.com.btwow.dto.PlanetDto;
import br.com.btwow.dto.SWApiResponseDto;
import br.com.btwow.dto.SWApiResponseResultDto;
import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class CommonPlanetTest<E> {

  @Mock
  protected ModelMapper modelMapper;

  @Mock
  protected SWPlanetsApiClient api;

  @Mock
  protected PlanetRepository repository;

  protected E service;

  protected SWPlanetsApiSearch planetsApiSearch;

  @BeforeEach
  public void setupCommon(){
    this.planetsApiSearch = new SWPlanetsApiSearch(api, modelMapper);
    when(api.getPlanet(anyString())).thenReturn(Optional.of(getApiResponse()));
  }

  protected void modelMapperMock(Planet planet, PlanetDto planetDto) {
    when(modelMapper.map(planetDto, Planet.class)).thenReturn(planet);
    when(modelMapper.map(planet, PlanetDto.class)).thenReturn(planetDto);
  }


  protected SWApiResponseDto getApiResponse() {

    SWApiResponseDto response = new SWApiResponseDto();
    response.setResponseResultDto(
            List.of(
                    new SWApiResponseResultDto("tes", Collections.emptyList()),
                    new SWApiResponseResultDto("test",List.of("test01", "test01"))));

    return response;
  }


  protected Planet getPlanet() {
    Planet planet = new Planet();
    planet.setTerrain("test");
    planet.setClimate("test");
    planet.setName("test");
    planet.setId("id");
    return planet;
  }

  protected PlanetDto getPlanetDto() {
    PlanetDto planet = new PlanetDto();
    planet.setTerrain("test");
    planet.setClimate("test");
    planet.setName("test");
    planet.setId("id");
    return planet;
  }

  protected PlanetDto getPlanetDtoWithFilms() {
    PlanetDto planet = new PlanetDto();
    planet.setTerrain("test");
    planet.setClimate("test");
    planet.setName("test");
    planet.setId("id");
    planet.setFilms(2L);
    return planet;
  }

  protected PlanetDto getPlanetDtoWithNoFilms() {
    PlanetDto planet = new PlanetDto();
    planet.setTerrain("test");
    planet.setClimate("test");
    planet.setName("test");
    planet.setId("id");
    planet.setFilms(0L);
    return planet;
  }
}
