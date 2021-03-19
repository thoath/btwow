package br.com.btwow.dto;

import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PlanetDto {

  private String id;

  @NotBlank(message = "Name must not be blank")
  private String name;

  @NotBlank(message = "Terrain must not be blank")
  private String terrain;

  @NotBlank(message = "Climate must not be blank")
  private String climate;

  private long films;
}
