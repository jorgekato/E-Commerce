
package e_commer.controle.web.vh.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.Cidade;
import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Estado;
import e_commer.dominio.Fornecedor;



public class FornecedorViewHelper implements IViewHelper {

	/** 
	 * TODO Descri��o do M�todo
	 * @param request
	 * @param response
	 * @return
	 * @see les12015.controle.web.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String id = request.getParameter("txtId");
		String nome = request.getParameter("txtRzSocial");
		String cnpj = request.getParameter("txtCnpj");
		String logradouro = request.getParameter("txtLogradouro");
		String cidade = request.getParameter("txtCidade");
		String estado = request.getParameter("txtEstado");
		String cep = request.getParameter("txtCep");		
		String numero = request.getParameter("txtNumero");
		
		Fornecedor f = new Fornecedor();
		
		if(id !=null && !id.trim().equals(""))
			f.setId(Integer.parseInt(id));
		
		f.setNome(nome);
//		f.setEndereco(new Endereco());
//		f.getEndereco().setCidade(new Cidade());
//		f.getEndereco().setLogradouro(logradouro);
//		f.getEndereco().getCidade().setNome(cidade);
//		f.getEndereco().getCidade().setEstado(new Estado());
//		f.getEndereco().getCidade().getEstado().setNome(estado);
//		
//		f.getEndereco().setNumero(numero);
//		f.getEndereco().setCep(cep);
		f.setCnpj(cnpj);
		return f;
	}

	/** 
	 * TODO Descri��o do M�todo
	 * @param request
	 * @param response
	 * @return
	 * @see les12015.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void setView(Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response) {
		
	}

}
