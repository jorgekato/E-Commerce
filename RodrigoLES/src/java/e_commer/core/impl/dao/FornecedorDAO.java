
package e_commer.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Fornecedor;

public class FornecedorDAO extends AbstractJdbcDAO {
	
	public FornecedorDAO() {
		super("tb_fornecedor", "id_for");		
	}
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Fornecedor fornecedor = (Fornecedor)entidade;
		Endereco end = fornecedor.getEndereco();
		
		try {
			connection.setAutoCommit(false);			
			EnderecoDAO endDAO = new EnderecoDAO();
			endDAO.connection = connection;
			endDAO.ctrlTransaction = false;
			endDAO.salvar(end);			
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_fornecedor(rzsocial, cnpj, end_id, ");
			sql.append("dt_cadastro) VALUES (?,?,?,?)");		
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, fornecedor.getNome());
			pst.setString(2, fornecedor.getCnpj());
			pst.setInt(3, end.getId());
			Timestamp time = new Timestamp(fornecedor.getDtCadastro().getTime());
			pst.setTimestamp(4, time);
			pst.executeUpdate();			
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
	/** 
	 * TODO Descri��o do M�todo
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}
	/** 
	 * TODO Descri��o do M�todo
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	

}
