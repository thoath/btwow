package br.com.btwow.service;

import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.impl.ListPlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ListPlanetServiceTest {

  @Mock
  private PlanetRepository planetRepository;

  @InjectMocks
  private ListPlanetService listPlanetService = new ListPlanetServiceImpl();


  @Test
  public void testIfReturnPopulatedList() {

    Mockito.when(planetRepository.findAll()).thenReturn(List.of(new Planet(), new Planet()));

    Assertions.assertEquals(2, listPlanetService.execute().size());
  }

  @Test
  public void testIfReturnEmptyList() {

    Mockito.when(planetRepository.findAll()).thenReturn(Collections.emptyList());

    Assertions.assertTrue(listPlanetService.execute().isEmpty());
  }
}
