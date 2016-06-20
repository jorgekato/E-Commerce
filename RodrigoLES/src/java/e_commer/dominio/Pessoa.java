package e_commer.dominio;

import java.util.ArrayList;
import java.util.List;

public class Pessoa extends EntidadeDominio {

    private String nome;

    //mudar para array de endereços
    private List<EntidadeDominio> endereco;
    //private Endereco endereco;

    private Login login;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<EntidadeDominio> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<EntidadeDominio> endereco) {
        this.endereco = endereco;
    }

    //verificar se realmente necessário
    public void addEndereco(Endereco endereco) {
        if (this.endereco != null) {
            this.endereco.add(endereco);
        } else {
            this.endereco = new ArrayList<>();
            this.endereco.add(endereco);
        }

    }
    

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
