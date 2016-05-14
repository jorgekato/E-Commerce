package e_commer.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class TrocaDevolucao extends EntidadeDominio{
    private Pedido pedido;
    private int proId;
    private String motivo;
    private String status;
    private int quantidade;
    private String anotacao;
    private String acao;
    private List<Relatorio> relatorio = new ArrayList<Relatorio>();

    
    public Pedido getPedido(){
        return pedido;
    }
    
    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }
    
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public List<Relatorio> getRelatorio() {
        return relatorio;
    }

    public void addRelatorio(Relatorio relatorio) {
        this.relatorio.add(relatorio);
    }
    
    
    
}
