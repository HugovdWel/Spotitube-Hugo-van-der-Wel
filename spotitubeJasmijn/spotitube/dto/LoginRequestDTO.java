package nl.han.danielvervloed.oose.spotitube.dto;

public class LoginRequestDTO {
    private String user;
    private String password;
    private int token;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(int token) {
        this.token = token;
    }

}