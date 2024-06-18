package ar.edu.utn.frc.tup.lc.iii.controllers;

import ar.edu.utn.frc.tup.lc.iii.dtos.*;
import ar.edu.utn.frc.tup.lc.iii.models.MatchModel;
import ar.edu.utn.frc.tup.lc.iii.models.RoundMatch;
import ar.edu.utn.frc.tup.lc.iii.models.UserModel;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/guess-number/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserModel user = userService.createUser(userDto.getUserName(), userDto.getEmail());
        UserDto userCreated = modelMapper.map(user, UserDto.class);

        return ResponseEntity.ok(userCreated);
    }

    @PostMapping("/{userId}/matches")
    public ResponseEntity<MatchDto> createUserMatch(@PathVariable Long userId, @RequestBody CreateUserMatchDto userMatchDto) {

        MatchModel match = userService.createUserMatch(userId, userMatchDto.getDifficulty());
        MatchDto matchDto = modelMapper.map(match, MatchDto.class);
        return ResponseEntity.ok(matchDto);
    }

    @PostMapping("/{userId}/matches/{matchId}")
    public ResponseEntity<RoundMatchDto> playMatch(@PathVariable Long userId, @PathVariable Long matchId, @RequestBody PlayUserMatchDto playUserMatchDto) {
        RoundMatch roundMatch = userService.playUserMatch(userId, matchId, playUserMatchDto.getNumber());

        return ResponseEntity.ok(modelMapper.map(roundMatch, RoundMatchDto.class));
    }

}
