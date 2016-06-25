package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.core.impl.dao.ClienteDAO;
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import java.util.List;

public class ValidadorCpf implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Cliente){
			Cliente c = (Cliente)entidade;
			
			if(c.getCpf().length() < 9){
				return "CPF deve conter 14 digitos!";
			}
                        Cliente cliente = new Cliente();
                        ClienteDAO daocli = new ClienteDAO();
                        List<EntidadeDominio> clientes = daocli.consultar(cliente);
                        for(EntidadeDominio e : clientes){
                            Cliente cli = (Cliente)e;
                            if(cli.getCpf().equals(c.getCpf()))
                                return "Cpf ja cadastrado!";
                        }
			
		}else{
			return "CPF n�o pode ser v�lidado, pois entidade n�o � um cliente!";
		}
		
		
		return null;
	}

}
