package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.MatchDifficulty;
import ar.edu.utn.frc.tup.lc.iii.models.MatchModel;
import ar.edu.utn.frc.tup.lc.iii.models.RoundMatch;
import ar.edu.utn.frc.tup.lc.iii.models.UserModel;

public interface MatchService {

    MatchModel createMatch(UserModel user, MatchDifficulty matchDifficulty);

    MatchModel getMatchById(Long matchId);
    RoundMatch playMatch(Integer number, MatchModel match);
}
