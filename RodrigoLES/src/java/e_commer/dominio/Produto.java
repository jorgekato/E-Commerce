package e_commer.dominio;

public class Produto extends EntidadeDominio {

    private String nome;
    private String marca;
    private String modelo;
    Categorias categoria;
    private String fabricante;
    private int quantidade;
    private String descricao;
    private double precoUnit;
    private int estoqueMin;
    private boolean flg_ativo;
    private int qtdeMaxVenda;
    private Imagem foto;

    public Imagem getFoto() {
        return foto;
    }

    public void setFoto(Imagem foto) {
        this.foto = foto;
    }
    
    //-----fim foto

    public Produto() {
    }

    public Produto(String nome, String marca, String modelo, Categorias categoria, String fabricante, int quantidade, String descricao, double precoUnit, int estoqueMin) {
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.precoUnit = precoUnit;
        this.estoqueMin = estoqueMin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    //verificar como vai incluir a categoria, pois tem 2 atributos e vai usar aqui apenas o nome.
    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public boolean getFlg_ativo() {
        return flg_ativo;
    }

    public void setFlg_ativo(boolean flg_ativo) {
        this.flg_ativo = flg_ativo;
    }

    public int getQtdeMaxVenda() {
        return qtdeMaxVenda;
    }

    public void setQtdeMaxVenda(int qtdeMaxVenda) {
        this.qtdeMaxVenda = qtdeMaxVenda;
    }

}
