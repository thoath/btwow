package br.com.btwow.service;

import static org.mockito.Mockito.*;

import br.com.btwow.dto.PlanetDto;
import br.com.btwow.model.Planet;
import br.com.btwow.service.impl.CreatePlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class CreatePlanetServiceTest extends CommonPlanetTest<CreatePlanetService> {

  @Autowired
  private MockMvc mvc;

  @BeforeEach
  public void setup(){
    this.service = new CreatePlanetServiceImpl(repository, modelMapper, planetsApiSearch);
  }

  @Test
  public void testIfPlanetIsCreated() {

    Planet planet = getPlanet();
    PlanetDto planetDto = getPlanetDto();
    modelMapperMock(planet, planetDto);
    when(repository.save(planet)).thenReturn(planet);

    Assertions.assertEquals(getPlanetDtoWithFilms(), service.execute(planetDto));

  }

  @Test
  public void testIfPlanetIsCreatedWithZeroFilms() {
    Planet planet = getPlanet();
    PlanetDto planetDto = getPlanetDto();
    modelMapperMock(planet, planetDto);
    when(api.getPlanet(anyString())).thenReturn(Optional.empty());
    when(repository.save(planet)).thenReturn(planet);

    Assertions.assertEquals(getPlanetDtoWithNoFilms(), service.execute(planetDto));
  }

  @Test
  public void testIfRequest400ForPlanetValidation() throws Exception {

    mvc
      .perform(MockMvcRequestBuilders.post("/api/v1/planet", new Planet()))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}
