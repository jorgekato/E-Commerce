/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.dominio;

import e_commer.core.util.FormatDouble;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class CarrinhoCompra extends EntidadeDominio{
    
    private List<AbstractItem> itens = new ArrayList<AbstractItem>();
    private double total = 0.0;
    private Endereco endereco;

    public double getTotal() {
        total = 0.00;
        for(AbstractItem a : itens){
            total += (a.getQuantidade()*a.getValorUnit());
        }
        
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public void adiciona(AbstractItem item)
    {
        itens.add(item);
    }
    
    public void remover(int indice)
    {
        itens.remove(indice);
    }
    
    public void atualizar(AbstractItem item, int id)
    {        
        itens.get(id).setQuantidade(item.getQuantidade());
    }

    public List<AbstractItem> getItens() {
        return itens;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
    
}
