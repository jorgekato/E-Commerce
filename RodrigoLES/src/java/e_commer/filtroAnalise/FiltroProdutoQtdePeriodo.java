/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.filtroAnalise;

import e_commer.dominio.EntidadeDominio;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class FiltroProdutoQtdePeriodo extends EntidadeDominio{
    
    private String nome;
    private double qtde;
    private Date dt_inicial;
    private Date dt_final;
    
    //Modificado a partir daqui
    private HashMap<String, Integer > hmQtde;
    private boolean flgComparar;
    private List<HashMap> listHash;
    private FiltroProdutoQtdePeriodo fPQP;
    
    public HashMap<String, Integer> getHmQtde() {
        return hmQtde;
    }

    public void setHmQtde(HashMap<String, Integer> hmQtde) {
        this.hmQtde = hmQtde;
    }

    public boolean getFlgComparar() {
        return flgComparar;
    }

    public void setFlgComparar(boolean flgComparar) {
        this.flgComparar = flgComparar;
    }

    public List<HashMap> getListHash() {
        return listHash;
    }

    public void setListHash(List<HashMap> listHash) {
        this.listHash = listHash;
    }

    public FiltroProdutoQtdePeriodo getfPQP() {
        return fPQP;
    }

    public void setfPQP(FiltroProdutoQtdePeriodo fPQP) {
        this.fPQP = fPQP;
    }
    
    
    //@@@@@@@@@@@@@@@@@2
    

    public FiltroProdutoQtdePeriodo() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double valor) {
        this.qtde = valor;
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

