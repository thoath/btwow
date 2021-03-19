package br.com.btwow.controller;

import br.com.btwow.dto.PlanetDto;
import br.com.btwow.service.CreatePlanetService;
import br.com.btwow.service.FindPlanetService;
import br.com.btwow.service.ListPlanetService;
import br.com.btwow.service.RemovePlanetService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controller to maintain planet domain */
@RestController
@RequestMapping("${api.prefix}/planet")
public class PlanetController {

  @Autowired private CreatePlanetService createPlanetService;

  @Autowired private FindPlanetService findPlanetService;

  @Autowired private ListPlanetService listPlanetService;

  @Autowired private RemovePlanetService removePlanetService;

  @PostMapping
  public ResponseEntity<PlanetDto> createPlanet(@Valid @RequestBody PlanetDto planet) {
    return ResponseEntity.status(HttpStatus.CREATED).body(createPlanetService.execute(planet));
  }

  @GetMapping("{id}")
  public ResponseEntity<PlanetDto> findById(@PathVariable String id) {
    return ResponseEntity.ok().body(findPlanetService.executeById(id));
  }

  @GetMapping("/search")
  public ResponseEntity<List<PlanetDto>> findByName(@RequestParam String name, int page, int size) {
    return ResponseEntity.ok().body(findPlanetService.executeByName(name, page, size));
  }

  @GetMapping
  public ResponseEntity<List<PlanetDto>> listPlanet(@RequestParam int page, int size) {
    return ResponseEntity.ok().body(listPlanetService.execute(page, size));
  }

  @DeleteMapping("{id}")
  public ResponseEntity removePlanet(@PathVariable String id) {
    removePlanetService.execute(id);
    return ResponseEntity.ok().build();
  }
}
