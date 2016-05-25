/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.filtroAnalise;

import e_commer.dominio.EntidadeDominio;
import java.util.Date;

/**
 *
 * @author Henrique
 */
public class FiltroClienteVendaPeriodo extends EntidadeDominio {

    private String nome;
    private double valor;
    private Date dt_inicial;
    private Date dt_final;

    public FiltroClienteVendaPeriodo() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDt_inicial() {
        return dt_inicial;
    }

    public void setDt_inicial(Date dt_inicial) {
        this.dt_inicial = dt_inicial;
    }

    public Date getDt_final() {
        return dt_final;
    }

    public void setDt_final(Date dt_final) {
        this.dt_final = dt_final;
    }
}
