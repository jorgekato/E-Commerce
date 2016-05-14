package e_commer.dominio;

import java.util.Date;

/**
 *
 * @author Jorge
 */
public class Credito extends EntidadeDominio{
    private String codigo;
    private double saldo;
    private Date dtValidade;
    private boolean flgAtivo;
    private Cliente cliente;

    public Credito() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
        this.dtValidade = dtValidade;
    }

    public boolean getFlgAtivo() {
        return flgAtivo;
    }

    public void setFlgAtivo(boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
