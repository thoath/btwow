package br.com.btwow.service;

import br.com.btwow.model.Planet;

import java.util.List;
import java.util.Optional;

public interface FindPlanetService {

    Optional<Planet> executeById(String planetId);
    List<Planet> executeByName(String planetName);
}
