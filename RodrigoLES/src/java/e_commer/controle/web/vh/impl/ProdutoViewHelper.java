package e_commer.controle.web.vh.impl;

import e_commer.controle.web.command.ICommand;
import e_commer.controle.web.command.impl.ConsultarCommand;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.util.ConverteDate;
import e_commer.core.util.ManipulaImagem;
import e_commer.dominio.Categorias;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Imagem;
import e_commer.dominio.Produto;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.Part;

public class ProdutoViewHelper implements IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        Produto produto = null;

        if (!operacao.equals("VISUALIZAR") && !operacao.equals("VISUALIZAR1")) {
            String nome = request.getParameter("nome");  //ok
            String marca = request.getParameter("marca");    //ok        
            String modelo = request.getParameter("modelo");
            String quantidade = request.getParameter("qtde");
            String qtdeMaxVenda = request.getParameter("qtdeMaxVenda");
            String valor_unit = request.getParameter("valorUnit");
            String estoque_min = request.getParameter("estoMin");
            String descricao = request.getParameter("descricao");
            String flg_ativo = request.getParameter("txtFlgAtivo");
            String id = request.getParameter("txtId");
            String dtCadastro = request.getParameter("txtDtCadastro");
            String catId = request.getParameter("categorias");
            produto = new Produto();

            //para pegar uma (1) imagem que vem da página JSP
            try {

                Imagem[] imagem = new Imagem[3];
                Part part = null;
                for (int i = 0; i < imagem.length; i++) {
                    part = request.getPart("imgUpload" + (i + 1));
                    if (part.getInputStream() != null) {
                        if (!part.getSubmittedFileName().equals("")) {
                            imagem[i] = new Imagem();
                            imagem[i].setImagem(ManipulaImagem.convertImagemBytes(part.getInputStream()));

                            //testar ao cadastrar sem imagem
                            if (imagem[i].getImagem() != null) {
                                //produto.setFoto(imagem);
                            } else {
                                //Pega imagem padrão e salva no BD
                                String caminho = request.getServletContext().getRealPath("/images/sem_imagem.png");
                                File file = new File(caminho);
                                InputStream fis = new FileInputStream(file);
                                imagem[i].setImagem(ManipulaImagem.convertImagemBytes(fis));
                                //produto.setFoto(imagem);
                            }
                            imagem[i].setPosicao(i);
                        } else if (operacao.equals("SALVAR")) {
                            imagem[i] = new Imagem();
                            String caminho = request.getServletContext().getRealPath("/images/sem_imagem.png");
                            File file = new File(caminho);
                            InputStream fis = new FileInputStream(file);
                            imagem[i].setImagem(ManipulaImagem.convertImagemBytes(fis));
                            imagem[i].setPosicao(i);
                        }
                    }
                }
                produto.setFoto(imagem);

            } catch (Exception e) {
                e.printStackTrace();
            }

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

            if (qtdeMaxVenda != null && !qtdeMaxVenda.trim().equals("")) {
                produto.setQtdeMaxVenda(Integer.valueOf(qtdeMaxVenda));
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
            Categorias cat = new Categorias();
            if (catId != null && !catId.trim().equals("")) {
                cat.setId(Integer.valueOf(catId));
            }
            produto.setCategoria(cat);
        } else {

            Resultado resultado = null;
            String txtId = request.getParameter("txtId");
            int id = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
            }

            produto = new Produto();
            ICommand command = new ConsultarCommand();
            resultado = command.execute((EntidadeDominio) produto);

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

        //transformar em else if
        if (request.getParameter("graficoproduto") != null) {
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/Relatorios/selecionaproduto.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
            resultado.setMsg("Produto cadastrado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqprodutos.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("produtostore.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {

            resultado.setMsg("Produto alterado com sucesso!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {

            request.setAttribute("produto", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("LadoAdmin/cadproduto.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR1")) {

            request.setAttribute("produto", resultado.getEntidades().get(0));
            d = request.getRequestDispatcher("singleproduto.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {

            resultado.setMsg("Produto excluido com sucesso!");

            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/msggeral.jsp");
        }

        d.forward(request, response);

    }

}
