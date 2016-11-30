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
import e_commer.core.impl.controle.Fachada;
import e_commer.core.util.ConverteDate;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Categorias;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Henrique
 */
public class CategoriaViewHelper implements IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        Categorias categoria = null;

        if (!operacao.equals("VISUALIZAR")) {

            //Recebe todos os dados que vieram do front_end
            String nomeCategoria = request.getParameter("txtNome");
            String descricao = request.getParameter("txtDescricao");
            String flg_ativo = request.getParameter("txtFlgAtivo");
            String id = request.getParameter("txtId");
            String dtCadastro = request.getParameter("txtDtCadastro");

            categoria = new Categorias();

            //Verifica as condições dos atributos que vieram do front_end para quando for alteração
            if (nomeCategoria != null && !nomeCategoria.trim().equals("")) {
                categoria.setNomeCategoria(nomeCategoria);
            }

            if (descricao != null && !descricao.trim().equals("")) {
                categoria.setDescricao(descricao);
            }

            if (flg_ativo != null && !flg_ativo.trim().equals("")) {
                if (flg_ativo.equals("TRUE")) {
                    categoria.setFlg_ativo(true);
                } else {
                    categoria.setFlg_ativo(false);
                }
            }

            if (id != null && !id.trim().equals("")) {
                categoria.setId(Integer.parseInt(id));
            }

            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                categoria.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
            }
            
        } else {
            
            Resultado resultado = null;
            String txtId = request.getParameter("txtId");
            int id = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
            }
            
            categoria = new Categorias();
            ICommand command = new ConsultarCommand();
            resultado = command.execute((EntidadeDominio)categoria);

            for (EntidadeDominio e : resultado.getEntidades()) {
                if (e.getId() == id) {
                    categoria = (Categorias) e;
                }
            }
        }

        return categoria;
    }

    public void setView(Resultado resultado, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;

        String operacao = request.getParameter("operacao");

        if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
            resultado.setMsg("Categoria cadastrada com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
            resultado.setMsg("Categoria alterada com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {

            request.setAttribute("categoria", resultado.getEntidades().get(0));
            //criar uma pagina para visualização de categorias e referenciar abaixo
            d = request.getRequestDispatcher("LadoAdmin/cadcategoria.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
            resultado.setMsg("Categoria excluida com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqcategoria.jsp");
        }

        
        d.forward(request, response);

    }

}
