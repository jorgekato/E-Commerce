/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.filtroAnalise;

import e_commer.dominio.EntidadeDominio;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class FiltroEstoqueMinimo extends EntidadeDominio {

    private String nome;
    private int quantidade;
    private double precoUnit;
    private int estoqueMin;
    private int qtdeVendida;
    private double total = 0.0;

    public FiltroEstoqueMinimo() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    public double getTotal(List<EntidadeDominio> entidades) {
        total = 0.0;
        for (EntidadeDominio e : entidades) {
            total += getDiferenca((FiltroEstoqueMinimo)e) ;
        }
        return total;
    }
    
    public double getDiferenca(FiltroEstoqueMinimo f)
    {
        return (f.getEstoqueMin() - f.getQuantidade()) * f.getPrecoUnit();
    }

    public int getQtdeVendida() {
        return qtdeVendida;
    }

    public void setQtdeVendida(int qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
    }
}
