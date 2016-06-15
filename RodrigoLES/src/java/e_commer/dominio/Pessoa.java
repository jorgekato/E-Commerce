package e_commer.dominio;

import java.util.ArrayList;
import java.util.List;

public class Pessoa extends EntidadeDominio {

    private String nome;

    //mudar para array de endereços
    private Endereco endereco;
    //private List<Endereco> endereco = new ArrayList<Endereco>();

    private Login login;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

//    public List<Endereco> getEndereco() {
//        return endereco;
//    }
//
//    public void addEndereco(List<Endereco> endereco) {
//        this.endereco = endereco;
//    }
//    public void removeEndereco(int posicao){
//        endereco.remove(posicao);
//    }

    
}
