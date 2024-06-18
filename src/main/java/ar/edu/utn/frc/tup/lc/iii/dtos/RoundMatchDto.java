package ar.edu.utn.frc.tup.lc.iii.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoundMatchDto {
    private MatchDto matchDto;
    private String response;
}
