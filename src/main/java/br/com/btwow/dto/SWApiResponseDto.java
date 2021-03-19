package br.com.btwow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SWApiResponseDto {

  @JsonProperty("results")
  private List<SWApiResponseResultDto> responseResultDto;
}
