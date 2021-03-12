package br.com.btwow.controller;

import br.com.btwow.dto.PlanetServiceDefaultMessage;
import br.com.btwow.service.RemovePlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}")
public class RemovePlanetController {

  @Autowired
  private RemovePlanetService removePlanetService;


  @DeleteMapping("/planet/{id}")
  public ResponseEntity<PlanetServiceDefaultMessage> removePlanet(@PathVariable String id) {

    boolean removed = removePlanetService.execute(id);

    return ResponseEntity.ok().body(new PlanetServiceDefaultMessage(String.valueOf(removed)));
  }

}
