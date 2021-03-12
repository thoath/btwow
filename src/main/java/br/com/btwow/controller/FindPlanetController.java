package br.com.btwow.controller;

import br.com.btwow.dto.PlanetServiceDefaultMessage;
import br.com.btwow.model.Planet;
import br.com.btwow.service.FindPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.prefix}")
public final class FindPlanetController {

  public static final String NO_PLANET_FOUND = "No planet found";

  @Autowired
  private FindPlanetService findPlanetService;

  @GetMapping("/planet/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {

    Optional<Planet> planet = findPlanetService.executeById(id);

    if (planet.isPresent()) {
      return ResponseEntity.ok().body(findPlanetService.executeById(id));
    }

    return ResponseEntity.ok().body(new PlanetServiceDefaultMessage(NO_PLANET_FOUND));
  }


  @GetMapping("/planet/name/{name}")
  public ResponseEntity<?> findByName(@PathVariable String name) {

    List<Planet> planet = findPlanetService.executeByName(name);

    if (!CollectionUtils.isEmpty(planet)) {
      return ResponseEntity.ok().body(findPlanetService.executeByName(name));
    }

    return ResponseEntity.ok().body(new PlanetServiceDefaultMessage(NO_PLANET_FOUND));
  }

}
