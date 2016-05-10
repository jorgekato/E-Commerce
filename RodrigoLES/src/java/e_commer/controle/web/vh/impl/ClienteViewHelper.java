package e_commer.controle.web.vh.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.controle.Fachada;
import e_commer.core.util.ConverteDate;
import e_commer.dominio.Cidade;
import e_commer.dominio.Cliente;
import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Estado;
import e_commer.dominio.Login;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

public class ClienteViewHelper implements IViewHelper {

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

        if (!operacao.equals("VISUALIZAR") && !operacao.equals("VISUALIZAR1")) {

            String nome = request.getParameter("txtNome");
            String email = request.getParameter("txtEmail");
            String sexo = request.getParameter("txtSexo");
            String password = request.getParameter("txtPassWord");
            String nivel = request.getParameter("txtNivel");
            String dtNasc = request.getParameter("txtDataNascimento");
            //colocar o endereço
            String endereco = request.getParameter("txtEndereco");
            String numero = request.getParameter("txtEnderecoNumero");
            String cep = request.getParameter("txtEnderecoCEP");
            String complemento = request.getParameter("txtEnderecoComplemento");
            String bairro = request.getParameter("txtEnderecoBairro");
            String cidade = request.getParameter("txtEnderecoCidade");
            String estado = request.getParameter("txtEnderecoEstado");

            String cpf = request.getParameter("txtCpf");
            String flg_ativo = request.getParameter("txtFlgAtivo");
            String id = request.getParameter("txtId");
            String dtCadastro = request.getParameter("txtDtCadastro");

            cliente = new Cliente();

            if (nome != null && !nome.trim().equals("")) {
                cliente.setNome(nome);
            }

            if (email != null && !email.trim().equals("")) {
                cliente.setEmail(email);
            }
            if(sexo != null && !sexo.trim().equals("")){
                cliente.setSexo(sexo);
            }
            
            if (nivel != null && !nivel.trim().equals("")) {
                
                if(nivel.equals(Cliente.Nivel.ADMINISTRADOR.toString()))                    
                    cliente.setNivel(Cliente.Nivel.ADMINISTRADOR);
                else if(nivel.equals(Cliente.Nivel.COLABORADOR.toString()))  
                    cliente.setNivel(Cliente.Nivel.COLABORADOR);
                else if(nivel.equals(Cliente.Nivel.CLIENTE.toString()))  
                    cliente.setNivel(Cliente.Nivel.CLIENTE);
                
            }
            
            if(dtNasc != null && !dtNasc.trim().equals("")){
                cliente.setDtNascimento(ConverteDate.converteStringDate(dtNasc));
            }

            Login loguin = new Login();
            if (password != null && !password.trim().equals("")) {
                loguin.setPassword(password);
            }

            if (id != null && !id.trim().equals("")) {
                loguin.setId(Integer.parseInt(id));
            }

            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                loguin.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
            }
//            if (email != null && !email.trim().equals("")) {
//                loguin.setUser(email);
//            }
            cliente.setLogin(loguin);
            
            Endereco end = new Endereco();
            
            if (endereco != null && !endereco.trim().equals("")) {
                end.setLogradouro(endereco);
            }
            if (numero != null && !numero.trim().equals("")) {
                end.setNumero(numero);
            }
            if (cep != null && !cep.trim().equals("")) {
                end.setCep(cep);
            }
            if (complemento != null && !complemento.trim().equals("")) {
                end.setComplento(complemento);
            }
            if (bairro != null && !bairro.trim().equals("")) {
                end.setBairro(bairro);
            }
            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                end.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
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
            cliente.setEndereco(end);
            

            if (cpf != null && !cpf.trim().equals("")) {
                cliente.setCpf(cpf);
            }

            if (flg_ativo
                    != null && !flg_ativo.trim()
                    .equals("")) {
                if (flg_ativo.equals("TRUE")) {
                    cliente.setFlg_ativo(true);
                } else {
                    cliente.setFlg_ativo(false);
                }
            }

            if (id != null && !id.trim().equals("")) {
                cliente.setId(Integer.parseInt(id));
            }

            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                cliente.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
            }
        } else {
            //HttpSession session = request.getSession();
            //Resultado resultado = (Resultado) request.getAttribute("resultado");
            Resultado resultado = null;
            String txtId = request.getParameter("txtId");
            int id = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
            }
            cliente = new Cliente();
            Fachada fachada = new Fachada();
            resultado = fachada.consultar((EntidadeDominio) cliente);

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
        }

        if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
            resultado.setMsg("Bem vindo ");
            request.setAttribute("bemvindo", resultado);
            d = request.getRequestDispatcher("index.jsp");
        }

        if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
            resultado.setMsg("Usuário alterado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("msggeral.jsp");
        }

        if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {

            request.setAttribute("clientes", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("LadoAdmin/cadartesanato.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("VISUALIZAR1")) {

            request.setAttribute("artesanato", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("single.jsp");
        }

        if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
            resultado.setMsg("Usuário excluido com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("msggeral.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

            request.setAttribute("clientes", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqcliente.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {

            request.setAttribute("resultado", resultado);
            //d = request.getRequestDispatcher("pesqartesanato.jsp");
            d = request.getRequestDispatcher("index.jsp");
        }
        d.forward(request, response);

    }

}
