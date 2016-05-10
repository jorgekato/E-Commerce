package e_commer.teste;

import java.sql.Connection;
import java.sql.SQLException;

import e_commer.core.util.Conexao;

public class TesteConexao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//crtl+shift+o
		try {
			Connection conn = Conexao.getConnection();
			if(conn != null)
				//sysout crtl+space
				System.out.println("Conectou");
			else
				System.out.println("Deu merda!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
