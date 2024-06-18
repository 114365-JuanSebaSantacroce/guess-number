package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.*;
import ar.edu.utn.frc.tup.lc.iii.repositories.MatchRepository;
import ar.edu.utn.frc.tup.lc.iii.services.MatchService;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final Random random = new Random();


    @Override
    public MatchModel createMatch(UserModel user, MatchDifficulty matchDifficulty) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setUserEntity(modelMapper.map(user, UserEntity.class));
        matchEntity.setDifficulty(matchDifficulty);
        switch (matchDifficulty){
            case HARD -> matchEntity.setRemainingTries(5);
            case MEDIUM -> matchEntity.setRemainingTries(8);
            case EASY -> matchEntity.setRemainingTries(10);
        }
        matchEntity.setNumberToGuess(random.nextInt(101));
        matchEntity.setStatus(MatchStatus.PLAYING);
        matchEntity.setUpdatedAt(LocalDateTime.now());
        matchEntity.setCreatedAt(LocalDateTime.now());
        MatchEntity matchEntitySaved = matchRepository.save(matchEntity);
        return modelMapper.map(matchEntitySaved, MatchModel.class);
    }

    @Override
    public MatchModel getMatchById(Long matchId) {
        Optional<MatchEntity> optionalMatch = matchRepository.findById(matchId);
        if (optionalMatch.isPresent()){
            return modelMapper.map(optionalMatch, MatchModel.class);
        }else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public RoundMatch playMatch(Integer number, MatchModel match) {
        RoundMatch round = new RoundMatch();
        round.setMatchModel(match);
        if (match.getStatus().equals(MatchStatus.FINISHED)){
            // TODO: ERROR
            return null;
        }
        if (match.getNumberToGuess().equals(number)){
            //TODO: CALCULAR SCORE
            //TODO: DAR RESPUESTA
            round.setResponse("YOU WIN");
        }else {
            match.setRemainingTries(match.getRemainingTries()-1);
            if (match.getRemainingTries().equals(0)){
                match.setStatus(MatchStatus.FINISHED);
                round.setResponse("YOU LOSE");
            }else {
                if (number > match.getNumberToGuess()){
                    round.setResponse("MINOR");
                }else {
                    round.setResponse("MAYOR");
                }

            }
        }
        UserEntity userEntity = modelMapper.map(match.getUser(), UserEntity.class);
        MatchEntity matchEntity = modelMapper.map(match, MatchEntity.class);
        matchEntity.setUserEntity(userEntity);
        matchEntity.setUpdatedAt(LocalDateTime.now());
        matchRepository.save(matchEntity);

        return round;

    }
}
