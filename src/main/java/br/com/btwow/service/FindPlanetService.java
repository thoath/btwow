package br.com.btwow.service;

import br.com.btwow.dto.PlanetDto;

import java.util.List;

public interface FindPlanetService {

    PlanetDto executeById(String planetId);
    List<PlanetDto> executeByName(String planetName, int page, int size);
}
