package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.dominio.AbstractItem;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Pedido;
import e_commer.dominio.Produto;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class ValidadorQtdeVendas implements IStrategy{
    
      @Override
    public String processar(EntidadeDominio entidade) {
            if (entidade != null) {
            
            Pedido pedido = (Pedido)entidade;
            List<AbstractItem> entidades = pedido.getItens();

            for (int i = 0; i < entidades.size(); i++) {

                if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                   
                    ItemProduto itpPro = (ItemProduto)entidades.get(i);
                    ProdutoDAO proDAO = new ProdutoDAO();
                    Produto p = proDAO.consultar(itpPro.getProduto().getId());
                    if(p.getQtdeMaxVenda() < itpPro.getQuantidade())
                        return "Desculpe, Permitido apenas " + p.getQtdeMaxVenda()+ " itens de "+ p.getNome() +" por pedido!";
                } 
            }
        } else {
            return "Entidade: " + entidade.getClass().getCanonicalName() + " nula!";
        }

        return null;
    }
}
