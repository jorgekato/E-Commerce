package e_commer.dominio;

/**
 *
 * @author Henrique
 */
public class Login extends EntidadeDominio {
    
    private String user;
    private String password;
    private boolean flg_ativo;

    public Login() {
    }

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

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
    
    public boolean getFlg_ativo() {
        return flg_ativo;
    }

    public void setFlg_ativo(boolean flg_ativo) {
        this.flg_ativo = flg_ativo;
    }
    
    
    
}
