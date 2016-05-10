
package e_commer.controle.web.command.impl;

import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.EntidadeDominio;


public class ExcluirCommand extends AbstractCommand{

	
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.excluir(entidade);
	}

}
