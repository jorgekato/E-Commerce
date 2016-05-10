package e_commer.controle.web.vh.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.controle.Fachada;
import e_commer.core.util.ConverteDate;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Artesanato;

/**
 *
 * @author Henrique
 */
public class ArtesanatoViewHelper implements IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        Artesanato artesanato = null;

        if (!operacao.equals("VISUALIZAR") && !operacao.equals("VISUALIZAR1")) {
            String nome = request.getParameter("nome");
            //falta categoria
            //ver como mandar apenas o id
            String valor_unit = request.getParameter("valorUnit");
            String cor = request.getParameter("cores");
            String descricao = request.getParameter("descricao");
            String flg_ativo = request.getParameter("txtFlgAtivo");
            String id = request.getParameter("txtId");
            String dtCadastro = request.getParameter("txtDtCadastro");

            artesanato = new Artesanato();

            if (nome != null && !nome.trim().equals("")) {
                artesanato.setNome(nome);
            }

//            if (cor != null && !cor.trim().equals("")) {
//                artesanato.setCores(cor);
//            }
            if (valor_unit != null && !valor_unit.trim().equals("")) {
                artesanato.setPrecoUnit(Double.parseDouble(valor_unit));
            }

            if (descricao
                    != null && !descricao.trim()
                    .equals("")) {
                artesanato.setDescricao(descricao);
            }

            if (flg_ativo
                    != null && !flg_ativo.trim()
                    .equals("")) {
                if (flg_ativo.equals("TRUE")) {
                    artesanato.setFlg_ativo(true);
                } else {
                    artesanato.setFlg_ativo(false);
                }
            }

            if (id != null && !id.trim().equals("")) {
                artesanato.setId(Integer.parseInt(id));
            }

            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                artesanato.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
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
            artesanato = new Artesanato();
            Fachada fachada = new Fachada();
            resultado = fachada.consultar((EntidadeDominio)artesanato);

            for (EntidadeDominio e : resultado.getEntidades()) {
                if (e.getId() == id) {
                    artesanato = (Artesanato) e;
                }
            }
        }

        return artesanato;
    }

    public void setView(Resultado resultado, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;

        String operacao = request.getParameter("operacao");

        if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
            resultado.setMsg("Artesanato cadastrado com sucesso!");
            request.setAttribute("resultado", resultado);            
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }

        else if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
            resultado.setMsg("Artesanato alterado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }

        else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {

            request.setAttribute("artesanato", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("LadoAdmin/cadartesanato.jsp");
        }
        else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR1")) {

            request.setAttribute("artesanato", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("singleartesanato.jsp");
        }

        else if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
            resultado.setMsg("Artesanato excluido com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }
        else if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqartesanato.jsp");
        }
        else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {

            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("artesanatostore.jsp");
        }
        
        d.forward(request, response);

    }

}
