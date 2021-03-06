package br.com.btwow.api;

import br.com.btwow.dto.SWApiResponseDto;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${swapi.url}/planets", name = "SWApi")
public interface SWPlanetsApiClient {

  @GetMapping("/?search={planet}")
  Optional<SWApiResponseDto> getPlanet(@PathVariable("planet") String planet);
}
