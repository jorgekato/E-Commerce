package e_commer.dominio;



public class Imagem extends EntidadeDominio {

    //Salvar no banco como bytea se postgreSql
    private byte[] imagem;
    private int posicao;
    private String url;
   
    
    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
