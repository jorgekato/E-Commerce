/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.dao;

import e_commer.core.util.Conexao;
import e_commer.dominio.EntidadeDominio;
import e_commer.filtroAnalise.FiltroClienteVendaPeriodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class FiltroClienteVendaPeriodoDAO extends AbstractJdbcDAO{

    public FiltroClienteVendaPeriodoDAO(String table, String idTable) {
        super(table, idTable);
    }

    public FiltroClienteVendaPeriodoDAO() {
        super(null, null);
    }
    

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        
        FiltroClienteVendaPeriodo f = (FiltroClienteVendaPeriodo) entidade;
        PreparedStatement pst = null;
        String sql = null;

        
            sql = "select cli_nome, sum(ite_valor_unit * ite_qtde) as valor\n"
                    + "from tb_clientes join \n"
                    + "     tb_pedidos using (cli_id) join \n"
                    + "     tb_itens_pedidos using(ped_id) join \n"
                    + "     tb_produtos using (pro_id) \n"
                    + "     where (ped_dt_compra between ? and ?)\n"
                    + "     group by (cli_nome)     \n"
                    + "     order by 2 desc ";
            
        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            pst.setDate(1, new java.sql.Date(f.getDt_inicial().getTime()));
            pst.setDate(2, new java.sql.Date(f.getDt_final().getTime()));

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> grafico = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                FiltroClienteVendaPeriodo f1 = new FiltroClienteVendaPeriodo();
                
                
                f1.setNome(rs.getString("cli_nome"));
                f1.setValor(rs.getDouble("valor"));
                f1.setDt_inicial(f.getDt_inicial());
                f1.setDt_final(f.getDt_final());
                
                grafico.add(f1);
            }
            return grafico;
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return null;
    }

    
    
    
}
