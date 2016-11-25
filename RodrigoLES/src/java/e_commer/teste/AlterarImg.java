/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.teste;

import e_commer.core.impl.dao.ImagemDAO;
import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.core.util.ConverteDate;
import e_commer.core.util.ManipulaImagem;
import e_commer.dominio.Categorias;
import e_commer.dominio.Imagem;
import e_commer.dominio.Produto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author Henrique
 */
public class AlterarImg {

    public static void main(String[] args) throws FileNotFoundException {

        Imagem imagem = new Imagem();

        String caminho = "C:\\Users\\Henrique\\Google Drive\\Fatec\\5º\\Lab Eng Soft\\sem_imagem.png";
        File file = new File(caminho);
        InputStream fis = new FileInputStream(file);
        imagem.setImagem(ManipulaImagem.convertImagemBytes(fis));
        Produto p = new Produto();
        //p.setFoto(imagem);
        
            p.setId(30);
            String nome = "nome";  //ok
            String marca = "marca";    //ok        
            String modelo = "modelo";
            String quantidade = "234";
            String qtdeMaxVenda = "43";
            String valor_unit = "23";
            String estoque_min = "3";
            String descricao = "descricao";
            String flg_ativo = "true";
            String dtCadastro = "12/05/2016";
            String catId = "1";
            

            if (nome != null && !nome.trim().equals("")) {
                p.setNome(nome);
            }

            if (marca != null && !marca.trim().equals("")) {
                p.setMarca(marca);
            }

            if (modelo != null && !modelo.trim().equals("")) {
                p.setModelo(modelo);
            }

            if (quantidade != null && !quantidade.trim().equals("")) {
                p.setQuantidade(Integer.parseInt(quantidade));
            }

            if (qtdeMaxVenda != null && !qtdeMaxVenda.trim().equals("")) {
                p.setQtdeMaxVenda(Integer.valueOf(qtdeMaxVenda));
            }

            if (valor_unit != null && !valor_unit.trim().equals("")) {
                p.setPrecoUnit(Double.parseDouble(valor_unit));
            }

            if (estoque_min != null && !estoque_min.trim().equals("")) {
                p.setEstoqueMin(Integer.parseInt(estoque_min));
            }

            if (descricao
                    != null && !descricao.trim()
                    .equals("")) {
                p.setDescricao(descricao);
            }

            if (flg_ativo
                    != null && !flg_ativo.trim()
                    .equals("")) {
                if (flg_ativo.equals("TRUE")) {
                    p.setFlg_ativo(true);
                } else {
                    p.setFlg_ativo(false);
                }
            }
            
            if (dtCadastro != null && !dtCadastro.trim().equals("")) {
                p.setDtCadastro(ConverteDate.converteStringDate(dtCadastro));
            }
            Categorias cat = new Categorias();
            if (catId != null && !catId.trim().equals("")) {
                cat.setId(Integer.valueOf(catId));
            }
            p.setCategoria(cat);

            ProdutoDAO imgDAO = new ProdutoDAO();

            try {
                imgDAO.salvar(p);
            } catch (Exception ex) {
                System.out.println(ex.toString());
                Logger.getLogger(AlterarImg.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }

}
