package nl.han.danielvervloed.oose.spotitube.dto;

public class LoginResponseDTO {
    private String user;
    private String token;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
