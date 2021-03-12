package br.com.btwow.controller;

import br.com.btwow.model.Planet;
import br.com.btwow.service.ListPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
public final class ListPlanetController {

  @Autowired
  private ListPlanetService listPlanetService;

  @GetMapping("/planet")
  public ResponseEntity<List<Planet>> listPlanet() {
    return ResponseEntity.status(HttpStatus.CREATED).body(listPlanetService.execute());
  }
}
