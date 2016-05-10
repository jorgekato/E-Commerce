package e_commer.teste;

import java.util.Date;
import java.util.List;

import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Produto;

public class TesteProdutoDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ProdutoDAO prodao = new ProdutoDAO();
		
		Produto produto = new Produto();
		
		List<EntidadeDominio> produtos =  prodao.consultar(produto);
		
		for(EntidadeDominio e: produtos)
		{
			Produto pro = (Produto)e;
			System.out.println("ID" + pro.getId());
			System.out.println("ID" + pro.getDescricao());
			System.out.println("ID" + pro.getQuantidade());
			System.out.println("ID" + pro.getDtCadastro());
		}
		
		produto.setDescricao("Fanta");
		produto.setDtCadastro(new Date());
		produto.setQuantidade(50);
		
		
		
		prodao.salvar(produto);
		
		Produto proPesq = new Produto();
		proPesq.setDescricao("fa");
		produtos = prodao.consultar(proPesq);
		
		for(EntidadeDominio e: produtos)
		{
			Produto pro = (Produto)e;
			System.out.println("ID" + pro.getId());
			System.out.println("ID" + pro.getDescricao());
			System.out.println("ID" + pro.getQuantidade());
			System.out.println("ID" + pro.getDtCadastro());
		}

	}

}
