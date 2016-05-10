
package e_commer.controle.web.command.impl;

import e_commer.controle.web.command.ICommand;
import e_commer.core.IFachada;
import e_commer.core.impl.controle.Fachada;



public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();

}
