package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.MatchDifficulty;
import ar.edu.utn.frc.tup.lc.iii.models.MatchModel;
import ar.edu.utn.frc.tup.lc.iii.models.RoundMatch;
import ar.edu.utn.frc.tup.lc.iii.models.UserModel;

public interface UserService {
    UserModel createUser(String userName, String email);

    MatchModel createUserMatch(Long userId, MatchDifficulty difficulty);

    RoundMatch playUserMatch(Long userId, Long matchId, Integer numberToPlay);
}
