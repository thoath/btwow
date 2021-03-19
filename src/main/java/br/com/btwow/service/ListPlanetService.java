package br.com.btwow.service;

import br.com.btwow.dto.PlanetDto;
import java.util.List;

public interface ListPlanetService {

  List<PlanetDto> execute(int pageBegin, int pageEnd);
}
