package e_commer.dominio;



/**
 *
 * @author Henrique
 */
public class Categorias extends EntidadeDominio{

    private String nomeCategoria;
    private String descricao;
    private boolean flg_ativo;
    
    public Categorias() {
    }
    
    public Categorias(String nomeCategoria, String descricao, boolean flg_ativo) {
        
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
        this.flg_ativo = flg_ativo;
    }
   

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getFlg_ativo() {
        return flg_ativo;
    }

    public void setFlg_ativo(boolean flg_ativo) {
        this.flg_ativo = flg_ativo;
    }
}
