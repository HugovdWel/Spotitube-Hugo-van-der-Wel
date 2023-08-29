package nl.han.danielvervloed.oose.spotitube.service;

import nl.han.danielvervloed.oose.spotitube.dao.UserDAO;
import nl.han.danielvervloed.oose.spotitube.dto.LoginRequestDTO;
import nl.han.danielvervloed.oose.spotitube.service.exception.LoginIncorrect;
import nl.han.danielvervloed.oose.spotitube.service.exception.TokenIncorrect;

import javax.inject.Inject;

public class LoginService {
    private UserDAO userDAO;

    public void checkLogin(LoginRequestDTO loginInfo){
        if (!(loginInfo.getPassword().equals(userDAO.getPassword(loginInfo.getUser())))) {
            throw new LoginIncorrect();
        }
    }

    public void checkToken(String token){
        if(!(userDAO.checkTokenNotUnique(token))){
            throw new TokenIncorrect();
        }
    }

    public void setUsersToken(String user, String token){
        userDAO.setUsersToken(user, token);
    }
    public void setUsersToken(String user, String token){
        userDAO.setUsersToken(user, token);
    }

    public boolean checkTokenNotUnique(String token){
        return userDAO.checkTokenNotUnique(token);
    }

    public String getUserFromToken(String token) {
        return userDAO.getUserFromToken(token);
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO(){
        return userDAO;
    }


}
