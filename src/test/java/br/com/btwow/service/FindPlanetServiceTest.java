package br.com.btwow.service;

import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.impl.FindPlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class FindPlanetServiceTest {

  @Mock
  private PlanetRepository planetRepository;

  @InjectMocks
  private FindPlanetService findPlanetService = new FindPlanetServiceImpl();


  @Test
  public void testIfSearchByIdReturnPlanet() {

    mockPlanetResult(Optional.of(getPlanet()));

    Assertions.assertEquals(getPlanet(), findPlanetService.executeById("test0101010").get());

  }

  @Test
  public void testIfSearchByIdReturnNoPlanet() {

    mockPlanetResult(Optional.empty());

    Assertions.assertEquals(Optional.empty(), findPlanetService.executeById("test0101010"));
  }

  @Test
  public void testIfSearchByNameReturnPlanet() {

    mockPlanetResult(List.of(getPlanet()));

    Assertions.assertEquals(List.of(getPlanet()), findPlanetService.executeByName("Tatooine"));
  }

  @Test
  public void testIfSearchByNameReturnNoPlanet() {

    mockPlanetResult(Collections.emptyList());

    Assertions.assertEquals(Collections.emptyList(), findPlanetService.executeByName("Tatooine"));
  }

  private void mockPlanetResult(Optional<Planet> planet) {
    Mockito.when(planetRepository.findById(Mockito.anyString())).thenReturn(planet);
  }

  private void mockPlanetResult(List<Planet> planet) {
    Mockito.when(planetRepository.findByNameIgnoreCase(Mockito.anyString())).thenReturn(planet);
  }

  private Planet getPlanet() {
    Planet planet = new Planet();
    planet.setId("test0101010");
    planet.setName("Tatooine");
    planet.setClimate("test");
    planet.setTerrain("test");
    return planet;
  }

}
