package nf.iteris.com.br.iterisapp.model;

/**
 * Created by re034850 on 12/12/2017.
 */

public class UserRegistration {
    private String cpf;
    private String name;
    private String password;
    private String profile;

    public UserRegistration() {
    }

    public UserRegistration(String cpf, String name, String password, String profile) {
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.profile = profile;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
