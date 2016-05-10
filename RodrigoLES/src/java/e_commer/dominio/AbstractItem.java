/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.dominio;

/**
 *
 * @author Henrique
 */
public abstract class AbstractItem extends EntidadeDominio{
    
    private int quantidade;
    private String tipo_item; //tem que ser tipo enum
    private String item_Status;
    public enum Tipo_Item {ARTESANATO, PRODUTO};

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo_item() {
        return tipo_item;
    }

    public void setTipo_item(String tipo_item) {
        this.tipo_item = tipo_item;
    }

    public String getItem_Status() {
        return item_Status;
    }

    public void setItem_Status(String item_Status) {
        this.item_Status = item_Status;
    }
    
    
    
    
    
}
