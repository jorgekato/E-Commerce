
package e_commer.controle.web.command.impl;

import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.EntidadeDominio;


public class VisualizarCommand extends AbstractCommand{

	
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.visualizar(entidade);
	}

}
