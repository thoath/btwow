package br.com.btwow.service.impl;

import br.com.btwow.api.SWPlanetsApiSearch;
import br.com.btwow.dto.PlanetDto;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.ListPlanetService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ListPlanetServiceImpl implements ListPlanetService {

  @Autowired private PlanetRepository repository;

  @Autowired private SWPlanetsApiSearch planetsApiSearch;

  @Override
  public List<PlanetDto> execute(int page, int size) {

    final Pageable pageable = PageRequest.of(page, size);

    return repository.findAll(pageable).stream()
        .map(planetsApiSearch::modelPlanetDto)
        .collect(Collectors.toList());
  }
}
