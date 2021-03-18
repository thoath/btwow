package br.com.btwow.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Getter
@Setter
@EqualsAndHashCode
public class Planet {

    @Id
    private String id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Terrain must not be blank")
    private String terrain;

    @NotBlank(message = "Climate must not be blank")
    private String climate;
}
