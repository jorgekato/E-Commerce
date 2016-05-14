
package e_commer.dominio;

/**
 *
 * @author Jorge
 */
public class Relatorio extends EntidadeDominio{
    private String comentario;
    private String status;
    
    public void setComentario(String comentario){
        this.comentario = comentario;
    }
    
    public String getComentario(){
        return comentario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
