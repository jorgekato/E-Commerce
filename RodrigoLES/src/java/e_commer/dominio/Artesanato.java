package e_commer.dominio;

/**
 *
 * @author Henrique
 */
public class Artesanato extends EntidadeDominio {

    
    private String nome;
    private Categorias categoria;
    private double precoUnit;
    private String[] cores;
    private String descricao;
    private boolean flg_ativo;
    //ver como criar ou armazenar as imagens

    public Artesanato() {
    }

    public Artesanato(String nome, Categorias categoria, double precoUnit, String[] cores, String descricao, boolean flg_ativo) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnit = precoUnit;
        this.cores = cores;
        this.descricao = descricao;
        this.flg_ativo = flg_ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public String[] getCores() {
        return cores;
    }

    public void setCores(String[] cores) {
        this.cores = cores;
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
