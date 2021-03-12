package br.com.btwow.service;

import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.impl.FindPlanetServiceImpl;
import br.com.btwow.service.impl.RemovePlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class RemovePlanetServiceTest {

  @Mock
  private PlanetRepository planetRepository;

  @Mock
  private FindPlanetServiceImpl findPlanetService;

  @InjectMocks
  private RemovePlanetService removePlanetService =  new RemovePlanetServiceImpl();

  @Test
  public void testIfDeletesWithSuccess() {

    getMockedService(Optional.of(new Planet()));

    Assertions.assertTrue(removePlanetService.execute("teste0101010"));
  }

  @Test
  public void testIfNoPlanetFound() {

    getMockedService(Optional.empty());
    Assertions.assertFalse(removePlanetService.execute("teste0101010"));
  }

  private void getMockedService(Optional<Planet> planet) {
    Mockito.when(findPlanetService.executeById(Mockito.anyString())).thenReturn(planet);
  }
}
