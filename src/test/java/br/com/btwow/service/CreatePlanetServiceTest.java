package br.com.btwow.service;

import br.com.btwow.api.SWPlanetsApi;
import br.com.btwow.dto.SWApiResponseDto;
import br.com.btwow.dto.SWApiResponseResultDto;
import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.impl.CreatePlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class CreatePlanetServiceTest {

  @Mock
  private PlanetRepository planetRepository;

  @Mock
  private SWPlanetsApi planetsApi;

  @InjectMocks
  private CreatePlanetService createPlanetService = new CreatePlanetServiceImpl();

  @Test
  public void testPlanetCreationWithSuccess() {

    Planet planet = getPlanet();
    SWApiResponseDto response = getApiResponse(Collections.singletonList("filmtest"));

    Mockito.when(planetsApi.getPlanet(Mockito.any())).thenReturn(Optional.of(response));
    Mockito.when(planetRepository.save(planet)).thenReturn(planet);

    Assertions.assertEquals(1L, createPlanetService.execute(planet).getFilms());
  }

  @Test
  public void testPlanetCreationMissingFields() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<Planet>> violations = validator.validate(new Planet());

    Assertions.assertFalse(violations.isEmpty());
  }

  private Planet getPlanet() {
    Planet planet = new Planet();
    planet.setId("test0101010");
    planet.setName("Tatooine");
    planet.setClimate("test");
    planet.setTerrain("test");
    return planet;
  }

  private SWApiResponseDto getApiResponse(List<String> films) {
    SWApiResponseDto response = new SWApiResponseDto();
    response.setResponseResultDto(
            Collections.singletonList(new SWApiResponseResultDto(films)));

    return response;
  }

}
