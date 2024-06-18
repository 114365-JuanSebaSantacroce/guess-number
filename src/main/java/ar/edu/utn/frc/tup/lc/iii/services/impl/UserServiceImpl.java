package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.*;
import ar.edu.utn.frc.tup.lc.iii.repositories.MatchRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lc.iii.services.MatchService;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatchService matchService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserModel createUser(String userName, String email) {

        Optional<UserEntity> userEntityOptional = userRepository.getByEmail(email);

        if (userEntityOptional.isPresent()) {
            // TODO: Enviar error al usuario. "El usuario ya existe"
            return null;
        }else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userName);
            userEntity.setEmail(email);
            userEntity = userRepository.save(userEntity);
            return modelMapper.map(userEntity, UserModel.class);
        }
    }

    @Override
    public MatchModel createUserMatch(Long userId, MatchDifficulty difficulty) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()){
            UserModel user = modelMapper.map(userEntityOptional.get(), UserModel.class);
            return matchService.createMatch(user, difficulty);
        }else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public RoundMatch playUserMatch(Long userId, Long matchId, Integer numberToPlay) {
        MatchModel matchModel = matchService.getMatchById(matchId);
        if(matchModel.getUser().getId().equals(userId)){
            return matchService.playMatch(numberToPlay, matchModel);
        }else {
            //TODO: ERROR
            return null;
        }
    }

}
