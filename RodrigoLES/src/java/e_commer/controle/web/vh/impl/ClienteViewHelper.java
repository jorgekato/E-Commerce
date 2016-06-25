package e_commer.controle.web.vh.impl;

import e_commer.controle.web.command.ICommand;
import e_commer.controle.web.command.impl.ConsultarCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
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

            String id = request.getParameter("txtId");
            String nome = request.getParameter("txtNome");
            String email = request.getParameter("txtEmail");
            String sexo = request.getParameter("txtSexo");
            String cpf = request.getParameter("txtCpf");
            String telefone = request.getParameter("txtTelefone");
            String password = request.getParameter("txtPassWord");
            String nivel = request.getParameter("txtNivel");
            String dtNasc = request.getParameter("txtDataNascimento");
            String flg_ativo = request.getParameter("txtFlgAtivo");
            String dtCadastro = request.getParameter("txtDtCadastro");
            //colocar o endereço
            String endereco = request.getParameter("txtEndereco");
            String numero = request.getParameter("txtEnderecoNumero");
            String cep = request.getParameter("txtEnderecoCEP");
            String complemento = request.getParameter("txtEnderecoComplemento");
            String bairro = request.getParameter("txtEnderecoBairro");
            String cidade = request.getParameter("txtEnderecoCidade");
            String estado = request.getParameter("txtEnderecoEstado");

            cliente = new Cliente();

            if (id != null && !id.trim().equals("")) {
                cliente.setId(Integer.parseInt(id));
            }

            if (nome != null && !nome.trim().equals("")) {
                cliente.setNome(nome);
            }

            if (email != null && !email.trim().equals("")) {
                cliente.setEmail(email);
            }
            if (sexo != null && !sexo.trim().equals("")) {
                cliente.setSexo(sexo);
            }

            if (nivel != null && !nivel.trim().equals("")) {

                if (nivel.equals(Cliente.Nivel.ADMINISTRADOR.toString())) {
                    cliente.setNivel(Cliente.Nivel.ADMINISTRADOR);
                } else if (nivel.equals(Cliente.Nivel.COLABORADOR.toString())) {
                    cliente.setNivel(Cliente.Nivel.COLABORADOR);
                } else if (nivel.equals(Cliente.Nivel.CLIENTE.toString())) {
                    cliente.setNivel(Cliente.Nivel.CLIENTE);
                }

            }

            if (telefone != null && !telefone.trim().equals("")) {
                cliente.setTelefone(telefone);
            }

            if (dtNasc != null && !dtNasc.trim().equals("")) {
                cliente.setDtNascimento(ConverteDate.converteStringDate(dtNasc));
            }

            Login loguin = new Login();
            if (password != null && !password.trim().equals("")) {
                loguin.setPassword(password);
            }

//            if (id != null && !id.trim().equals("")) {
//                loguin.setId(Integer.parseInt(id));
//            }
            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                loguin.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
            }

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
                end.setComplemento(complemento);
            } else {
                end.setComplemento("");
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
            cliente.addEndereco(end);

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
        } else if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
            resultado.setMsg("Bem vindo ");
            request.setAttribute("bemvindo", resultado);
            d = request.getRequestDispatcher("index.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
            resultado.setMsg("Usuário alterado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("msggeral.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("ALTERAR1")) {
            request.getSession().setAttribute("usuario", resultado.getEntidades().get(0));
            resultado.setMsg("Usuário alterado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("minhaconta.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {

            request.setAttribute("clientes", resultado);
            d = request.getRequestDispatcher("LadoAdmin/cadcliente.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR1")) {

            request.setAttribute("artesanato", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("single.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
            resultado.setMsg("Usuário excluido com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("msggeral.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

            request.setAttribute("clientes", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqcliente.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {
            
            request.setAttribute("resultado", resultado);
            //d = request.getRequestDispatcher("pesqartesanato.jsp");
            d = request.getRequestDispatcher("index.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("CONSULTAR2")) {

            request.setAttribute("cliente", resultado);
            d = request.getRequestDispatcher("minhaconta.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("CONSULTAR3")) {

            request.setAttribute("cliente", resultado);
            d = request.getRequestDispatcher("cartconfirmar.jsp");
        }
        if(resultado.getMsg() != null){
            request.setAttribute("mensagem", resultado.getMsg());
            d= request.getRequestDispatcher("register.jsp");
        }
        d.forward(request, response);

    }

}
