package e_commer.dominio;

public class Pessoa extends EntidadeDominio {

    private String nome;

    //mudar para array de endereços
    private Endereco endereco;

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

}
