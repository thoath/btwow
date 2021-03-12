package br.com.btwow.controller;

import br.com.btwow.model.Planet;
import br.com.btwow.service.CreatePlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.prefix}")
public final class CreatePlanetController {

    @Autowired
    private CreatePlanetService createPlanetService;

    @PostMapping("/planet")
    public ResponseEntity<Planet> createPlanet(@Valid @RequestBody Planet planet) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createPlanetService.execute(planet));
    }
}
