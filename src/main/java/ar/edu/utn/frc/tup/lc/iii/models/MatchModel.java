package ar.edu.utn.frc.tup.lc.iii.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchModel {
    private Long id;
    private UserModel user;
    private MatchDifficulty difficulty;
    private Integer numberToGuess;
    private Integer remainingTries;
    private MatchStatus status;
}
