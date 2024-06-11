package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.UserModel;

public interface UserService {
    UserModel createUser(String userName, String email);
}
