package nf.iteris.com.br.iterisapp.model;

/**
 * Created by re034850 on 12/12/2017.
 */

public class UserRegistration {
    private String cpf;
    private String name;
    private String password;

    public UserRegistration(String cpf, String name, String password) {
        this.cpf = cpf;
        this.name = name;
        this.password = password;
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
}
