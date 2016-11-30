/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.filtroAnalise;

import e_commer.dominio.EntidadeDominio;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class FiltroPerfilIdadeSexo extends EntidadeDominio{
    
    private Date dt_inicial;
    private Date dt_final;
    private List<Integer> masculino;
    private List<Integer> feminino;

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
    
    public List<Integer> getMasculino() {
        return masculino;
    }

    public void setMasculino(List<Integer> masculino) {
        this.masculino = masculino;
    }

    public List<Integer> getFeminino() {
        return feminino;
    }

    public void setFeminino(List<Integer> feminino) {
        this.feminino = feminino;
    }
    
    
    
}
