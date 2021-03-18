package br.com.btwow.service;

import static org.mockito.Mockito.*;

import br.com.btwow.exception.PlanetNotFoundException;
import br.com.btwow.service.impl.FindPlanetServiceImpl;
import br.com.btwow.service.impl.RemovePlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class RemovePlanetServiceTest extends CommonPlanetTest<RemovePlanetService>{

  @BeforeEach
  public void setup(){
    this.service = new RemovePlanetServiceImpl(repository, new FindPlanetServiceImpl(repository, planetsApiSearch));
  }

  @Test
  public void testRemoveWithExistentId(){

    modelMapperMock(getPlanet(), getPlanetDto());
    when(repository.findById(anyString())).thenReturn(Optional.of(getPlanet()));

    service.execute("test");
  }

  @Test
  public void testRemoveWithNonExistentId(){

    when(repository.findById(anyString())).thenReturn(Optional.empty());

    Assertions.assertThrows(PlanetNotFoundException.class, () -> {
      service.execute("test");
    });
  }
}
