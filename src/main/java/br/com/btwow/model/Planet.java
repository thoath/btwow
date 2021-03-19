package br.com.btwow.model;

import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@EqualsAndHashCode
public class Planet {

  @Id private String id;

  @NotBlank(message = "Name must not be blank")
  private String name;

  @NotBlank(message = "Terrain must not be blank")
  private String terrain;

  @NotBlank(message = "Climate must not be blank")
  private String climate;
}
