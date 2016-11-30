/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.core.impl.dao.ArtesanatoDAO;
import e_commer.core.impl.dao.ClienteDAO;
import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.dominio.Artesanato;
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Produto;

/**
 *
 * @author Jorge
 */
public class VerificaSeCadastrado implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        
        if(entidade.getClass().getName().equals(Produto.class.getName())){
        ProdutoDAO prod = new ProdutoDAO();
        if(prod.consultar(entidade) != null){
            return "Produto ja cadastrado!";
            }
        }else if(entidade.getClass().getName().equals(Artesanato.class.getName())){
            ArtesanatoDAO art = new ArtesanatoDAO();
            if(art.consultar(entidade)!= null){
                return "Artesanato ja cadastrado";
            }
        }else if(entidade.getClass().getName().equals(Cliente.class.getName())){
            ClienteDAO cli = new ClienteDAO();
            if(cli.consultar(entidade) != null){
                return "Cliente ja cadastrado";
            }
        }
        
        return null;    
    }
    
}
