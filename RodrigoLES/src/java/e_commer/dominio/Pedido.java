package e_commer.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido extends EntidadeDominio{
    private String pagamento;
    private String servico;
    private String numBoleto;
    private double total;
    private Cliente cliente;
    private String status;
//    private String tipo_item;
//    public enum Tipo_Item {ARTESANATO, PRODUTO};
    private Date dt_compra;
    private List<AbstractItem> itens = new ArrayList<AbstractItem>();

    public Pedido() {
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getNumBoleto() {
        return numBoleto;
    }

    public void setNumBoleto(String numBoleto) {
        this.numBoleto = numBoleto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public  void setStatus(String status){
        this.status = status;
    }
           
    public String getStatus(){
        return status;
    }

    public Date getDt_compra() {
        return dt_compra;
    }

    public void setDt_compra(Date dt_compra) {
        this.dt_compra = dt_compra;
    }

//    public String getTipo_item() {
//        return tipo_item;
//    }
//
//    public void setTipo_item(String tipo_item) {
//        this.tipo_item = tipo_item;
//    }
    
    public void adiciona(AbstractItem item)
    {
        itens.add(item);
    }
    
    public void remove(AbstractItem item){
        itens.remove(item);
    }
    
    public List<AbstractItem> getItens() {
        return itens;
    }
}
