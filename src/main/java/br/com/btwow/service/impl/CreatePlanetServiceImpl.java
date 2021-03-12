package br.com.btwow.service.impl;

import br.com.btwow.api.SWPlanetsApi;
import br.com.btwow.model.Planet;
import br.com.btwow.repository.PlanetRepository;
import br.com.btwow.service.CreatePlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePlanetServiceImpl implements CreatePlanetService {

    @Autowired
    private PlanetRepository repository;

    @Autowired
    private SWPlanetsApi swPlanetsApi;

    @Override
    public Planet execute(Planet planet) {

      swPlanetsApi.getPlanet(planet.getName())
         .ifPresent(swPlanet -> {

           swPlanet.getResponseResultDto()
             .stream()
             .findFirst()
             .ifPresent(result -> {
               planet.setFilms(result
                 .getFilms()
                 .stream()
                 .count());
             });
         });

      return repository.save(planet);
    }
}
