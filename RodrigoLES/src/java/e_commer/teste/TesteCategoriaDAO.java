/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.teste;

import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.controle.Fachada;
import e_commer.core.impl.dao.CategoriasDAO;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Categorias;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class TesteCategoriaDAO {

    public static void main(String[] args) {

        CategoriasDAO prodao = new CategoriasDAO();

        Categorias cat = new Categorias();
        
        Resultado resultado = new Resultado();
        Fachada fachada = new Fachada();
        
        resultado = fachada.consultar(cat);
        
     //   resultado.setEntidades(prodao.consultar(cat));

        //List<EntidadeDominio> produtos = prodao.consultar(cat);
        List<EntidadeDominio> produtos = resultado.getEntidades();
        
        
        for (EntidadeDominio e : produtos) {
            Categorias pro = (Categorias) e;
            System.out.println("ID" + pro.getId());
            System.out.println("ID" + pro.getNomeCategoria());
            System.out.println("ID" + pro.getDtCadastro());
        }

        

    }

}
