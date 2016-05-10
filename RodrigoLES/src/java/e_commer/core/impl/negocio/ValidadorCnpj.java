package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Fornecedor;

public class ValidadorCnpj implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Fornecedor){
			Fornecedor fornecedor = (Fornecedor)entidade;
			
			if(fornecedor.getCnpj().length() < 14){
				return "CNPJ deve conter 14 digitos!";
			}
			
		}else{
			return "CNPJ n�o pode ser v�lidado, pois entidade n�o � um fornecedor!";
		}
		
		
		return null;
	}

}
