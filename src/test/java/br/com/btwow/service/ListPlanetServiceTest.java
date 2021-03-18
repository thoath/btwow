package br.com.btwow.service;

import static org.mockito.Mockito.*;

import br.com.btwow.dto.PlanetDto;
import br.com.btwow.model.Planet;
import br.com.btwow.service.impl.ListPlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;


@SpringBootTest
public class ListPlanetServiceTest extends CommonPlanetTest<ListPlanetService> {

  @BeforeEach
  public void setup(){
    this.service = new ListPlanetServiceImpl(repository, planetsApiSearch);
  }

  @Test
  public void testReturnedList() {
    Planet planet = getPlanet();
    PlanetDto planetDto = getPlanetDto();
    modelMapperMock(planet, planetDto);

    Page<Planet> page = new PageImpl<>(List.of(planet, planet));

    when(repository.findAll(any(Pageable.class))).thenReturn(page);

    Assertions.assertEquals(
            List.of(getPlanetDtoWithFilms(), getPlanetDtoWithFilms()), service.execute(0,2));
  }

  @Test
  public void testReturnedEmptyList() {

    Page<Planet> page = new PageImpl<>(Collections.emptyList());

    when(repository.findAll(any(Pageable.class))).thenReturn(page);

    Assertions.assertEquals(
            Collections.emptyList(), service.execute(0,1));
  }

}
