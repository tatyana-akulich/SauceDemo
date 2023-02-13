package by.teachmeskills.utils;

public class Credentials {
    private String login;
    private String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static Credentials getValidCredentials (){
        return new Credentials("standard_user", "secret_sauce");
    }
}
