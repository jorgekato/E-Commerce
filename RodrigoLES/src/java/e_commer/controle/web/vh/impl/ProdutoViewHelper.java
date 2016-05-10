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
import e_commer.dominio.Produto;

public class ProdutoViewHelper implements IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        Produto produto = null;

        if (!operacao.equals("VISUALIZAR")&& !operacao.equals("VISUALIZAR1")) {
            String nome = request.getParameter("nome");  //ok
            String marca = request.getParameter("marca");    //ok        
            String modelo = request.getParameter("modelo");
            String quantidade = request.getParameter("qtde");
            String valor_unit = request.getParameter("valorUnit");
            String estoque_min = request.getParameter("estoMin");
            String descricao = request.getParameter("descricao");
            String flg_ativo = request.getParameter("txtFlgAtivo");
            String id = request.getParameter("txtId");
            String dtCadastro = request.getParameter("txtDtCadastro");

            produto = new Produto();

            if (nome != null && !nome.trim().equals("")) {
                produto.setNome(nome);
            }
            
            if (marca != null && !marca.trim().equals("")) {
                produto.setMarca(marca);
            }
            
            if (modelo != null && !modelo.trim().equals("")) {
                produto.setModelo(modelo);
            }
            
            if (quantidade != null && !quantidade.trim().equals("")) {
                produto.setQuantidade(Integer.parseInt(quantidade));
            }
            
            if (valor_unit != null && !valor_unit.trim().equals("")) {
                produto.setPrecoUnit(Double.parseDouble(valor_unit));
            }
            
            if (estoque_min != null && !estoque_min.trim().equals("")) {
                produto.setEstoqueMin(Integer.parseInt(estoque_min));
            }
            
            if (descricao
                    != null && !descricao.trim()
                    .equals("")) {
                produto.setDescricao(descricao);
            }

            if (flg_ativo
                    != null && !flg_ativo.trim()
                    .equals("")) {
                if (flg_ativo.equals("TRUE")) {
                    produto.setFlg_ativo(true);
                } else {
                    produto.setFlg_ativo(false);
                }
            }

            if (id != null && !id.trim().equals("")) {
                produto.setId(Integer.parseInt(id));
            }

            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                produto.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
            }
        } else {
            
            Resultado resultado = null;
            String txtId = request.getParameter("txtId");
            int id = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
            }

            produto = new Produto();
            Fachada fachada = new Fachada();
            resultado = fachada.consultar((EntidadeDominio) produto);
            
            for (EntidadeDominio e : resultado.getEntidades()) {
                if (e.getId() == id) {
                    produto = (Produto) e;
                }
            }
        }

        return produto;
    }

    public void setView(Resultado resultado, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;

        String operacao = request.getParameter("operacao");

                
        if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
                resultado.setMsg("Produto cadastrado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }
        
        if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
                
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqprodutos.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {

            request.setAttribute("resultado", resultado);
            //d = request.getRequestDispatcher("pesqartesanato.jsp");
            d = request.getRequestDispatcher("produtostore.jsp");
        }
       
        if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {

            resultado.setMsg("Produto alterado com sucesso!");
            
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }

        if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {

            request.setAttribute("produto", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("LadoAdmin/cadproduto.jsp");
        }
        if (resultado.getMsg() == null && operacao.equals("VISUALIZAR1")) {

            request.setAttribute("produto", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("singleproduto.jsp");
        }

        if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {

            resultado.setMsg("Produto excluido com sucesso!");
            
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }



        d.forward(request, response);

    }

}
