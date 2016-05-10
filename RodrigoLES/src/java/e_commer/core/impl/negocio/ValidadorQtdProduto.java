package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Produto;

public class ValidadorQtdProduto implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Produto){
			Produto c = (Produto)entidade;
			
			if(c.getQuantidade() < 1){
				return "Quantidade deve ser no minimo 1!";
			}
			
		}else{
			return "Quantidade n�o pode ser v�lidado, pois entidade n�o � um produto!";
		}
		
		
		return null;
	}

}
