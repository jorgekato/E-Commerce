package e_commer.dominio;

import java.util.Date;

public class Cliente extends Pessoa {

    private String cpf;
    private String email;
    private String sexo;
    private boolean flg_ativo;
    public enum Nivel {CLIENTE, ADMINISTRADOR, COLABORADOR};
    private Nivel nivel;
    private Date dtNascimento;

    /**
     * M�todo de recupera��o do campo cpf
     *
     * @return valor do campo cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Valor de cpf atribu�do a cpf
     *
     * @param cpf Atributo da Classe
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getFlg_ativo() {
        return flg_ativo;
    }

    public void setFlg_ativo(boolean flg_ativo) {
        this.flg_ativo = flg_ativo;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    
    
}
