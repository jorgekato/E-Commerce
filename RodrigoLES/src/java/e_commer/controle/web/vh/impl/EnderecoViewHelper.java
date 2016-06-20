/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.controle.web.vh.impl;

import e_commer.controle.web.command.ICommand;
import e_commer.controle.web.command.impl.ConsultarCommand;
import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.Cidade;
import e_commer.dominio.Cliente;
import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Estado;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique
 */
public class EnderecoViewHelper implements IViewHelper {

    /**
     * TODO Descri��o do M�todo
     *
     * @param request
     * @param response
     * @return
     * @see
     * les12015.controle.web.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        Cliente cliente = null;
        Cliente clienteSessao = null;

        if (!operacao.equals("VISUALIZAR") && !operacao.equals("VISUALIZAR1") 
                && !operacao.equals("VISUALIZAR3") && !operacao.equals("VISUALIZAR4")) {
            
            String id = request.getParameter("txtId");
            String endId = request.getParameter("endId");
            String situacao = request.getParameter("situacao");
            String endereco = request.getParameter("txtEndereco");
            String numero = request.getParameter("txtEnderecoNumero");
            String cep = request.getParameter("txtEnderecoCEP");
            String complemento = request.getParameter("txtEnderecoComplemento");
            String bairro = request.getParameter("txtEnderecoBairro");
            String cidade = request.getParameter("txtEnderecoCidade");
            String estado = request.getParameter("txtEnderecoEstado");

            //clienteSessao = (Cliente)request.getSession().getAttribute("usuario");
            cliente = (Cliente)request.getSession().getAttribute("usuario");
//            cliente.setId(clienteSessao.getId());
//            cliente.setCpf(clienteSessao.getCpf());
            Endereco end = new Endereco();
            
            if (endereco != null && !endereco.trim().equals("")) {
                end.setLogradouro(endereco);
            }
            if (numero != null && !numero.trim().equals("")) {
                end.setNumero(numero);
            }
            
            if(endId != null && !endId.trim().equals("")){
                end.setId(Integer.parseInt(endId));
            }
            
            if (cep != null && !cep.trim().equals("")) {
                end.setCep(cep);
            }
            if (complemento != null && !complemento.trim().equals("")) {
                end.setComplemento(complemento);
            }
            else
            {
                end.setComplemento("");
            }
            if (bairro != null && !bairro.trim().equals("")) {
                end.setBairro(bairro);
            }
            
            if(situacao == null &&!situacao.trim().equals("")){
                end.setFlgAtivo(Boolean.parseBoolean(situacao));
            }
            
            Cidade cid = new Cidade();
            if (cidade != null && !cidade.trim().equals("")) {
                cid.setNome(cidade);
            }
            Estado est = new Estado();
            if (estado != null && !estado.trim().equals("")) {
                est.setNome(estado);
            }
            cid.setEstado(est);
            end.setCidade(cid);
            
            
            cliente.addEndereco(end);
            if(operacao.equals("ALTERAR")){
                return end;
            }
            
        } else {
            
            Resultado resultado = null;
            String txtId = request.getParameter("txtId");
            int id = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
            }
            cliente = new Cliente();
            ICommand command = new ConsultarCommand();
            resultado = command.execute((EntidadeDominio) cliente);

            for (EntidadeDominio e : resultado.getEntidades()) {
                if (e.getId() == id) {
                    cliente = (Cliente) e;
                }
            }
        }

        return cliente;

    }

    /**
     * TODO Descri��o do M�todo
     *
     * @param request
     * @param response
     * @return
     * @see
     * les12015.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    public void setView(Resultado resultado, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        // TODO Auto-generated method stub

        RequestDispatcher d = null;

        String operacao = request.getParameter("operacao");

        if (resultado.getMsg() != null) {
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("erro.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
//            resultado.setMsg("Bem vindo ");
//            request.setAttribute("bemvindo", resultado);
            d = request.getRequestDispatcher("cartconfirmar.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
            resultado.setMsg("Usuário alterado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("msggeral.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {

            request.setAttribute("clientes", resultado);
            d = request.getRequestDispatcher("LadoAdmin/cadcliente.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR1")) {

            request.setAttribute("artesanato", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("single.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
            resultado.setMsg("Usuário excluido com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("msggeral.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

            request.setAttribute("clientes", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqcliente.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {

            request.setAttribute("resultado", resultado);
            //d = request.getRequestDispatcher("pesqartesanato.jsp");
            d = request.getRequestDispatcher("index.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("VISUALIZAR3")) {

            request.setAttribute("clientes", resultado);
            d = request.getRequestDispatcher("meusdados.jsp");    
        }
        if (resultado.getMsg() == null && operacao.equals("VISUALIZAR4")) {

            request.setAttribute("clientes", resultado);
            d = request.getRequestDispatcher("cartconfirmar.jsp");    
        }
        d.forward(request, response);

    }

}
