
package e_commer.controle.web.command;

import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.EntidadeDominio;


public interface ICommand {

	public Resultado execute(EntidadeDominio entidade);
	
}
