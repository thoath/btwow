package br.com.btwow.service;

import static org.mockito.Mockito.*;

import br.com.btwow.dto.PlanetDto;
import br.com.btwow.exception.PlanetNotFoundException;
import br.com.btwow.model.Planet;
import br.com.btwow.service.impl.FindPlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class FindPlanetServiceTest extends CommonPlanetTest<FindPlanetService>{

  @BeforeEach
  public void setup(){
    this.service = new FindPlanetServiceImpl(repository, planetsApiSearch);
  }

  @Test
  public void testFindById() {

    Planet planet = getPlanet();
    PlanetDto planetDto = getPlanetDto();

    when(repository.findById(anyString())).thenReturn(Optional.of(planet));
    modelMapperMock(planet, planetDto);
    Assertions.assertEquals(getPlanetDtoWithFilms(), service.executeById("test"));
  }

  @Test
  public void testFindByIdNotFound() {

    Assertions.assertThrows(PlanetNotFoundException.class, () -> {
      when(repository.findById(anyString())).thenReturn(Optional.empty());
      service.executeById("test");
    });
  }

  @Test
  public void testFindByName() {

    Planet planet = getPlanet();
    PlanetDto planetDto = getPlanetDto();
    modelMapperMock(planet, planetDto);

    Page<Planet> page = new PageImpl<>(List.of(planet, planet));

    when(repository.findByNameIgnoreCase(anyString(), any(Pageable.class))).thenReturn(page);

    Assertions.assertEquals(
            List.of(getPlanetDtoWithFilms(), getPlanetDtoWithFilms()), service.executeByName("test", 0,2));

    page = new PageImpl<>(Collections.singletonList(planet));

    when(repository.findByNameIgnoreCase(anyString(), any(Pageable.class))).thenReturn(page);

    Assertions.assertEquals(Collections.singletonList(getPlanetDtoWithFilms()), service.executeByName("test",0,1));
  }

  @Test
  public void testFindByNameNotFound() {
    Assertions.assertThrows(PlanetNotFoundException.class, () -> {
      when(repository.findByNameIgnoreCase(anyString(), any(Pageable.class))).thenReturn(Page.empty());
      service.executeByName("test", 0, 1);
    });
  }

}
