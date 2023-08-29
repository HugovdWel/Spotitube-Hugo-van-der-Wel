package HugoVanDerWel.DataTransferObjects;

public class LoginRequestDTO {

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username;
    public String password;
}
