/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.dao;

import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Produto;
import e_commer.filtroAnalise.FiltroEstoqueMinimo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class FiltroEstoqueMinimoDAO extends AbstractJdbcDAO {

    public FiltroEstoqueMinimoDAO(String table, String idTable) {
        super(table, idTable);
    }

    public FiltroEstoqueMinimoDAO() {
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

        PreparedStatement pst = null;
        String sql = null;

        sql = "select pro_id,\n"
                + "	pro_nome, \n"
                + "	pro_qtde_estoque,\n"
                + "	pro_estoque_min, \n"
                + "	pro_valor_unit, sum(ite_qtde) as qtde\n"
                + "                from tb_pedidos join \n"
                + "                tb_itens_pedidos using (ped_id) join\n"
                + "                tb_produtos using (pro_id) \n"
                + "                where (ped_dt_compra between current_date-90 and current_date) and pro_qtde_estoque <= pro_estoque_min\n"
                + "                group by pro_id,\n"
                + "	pro_nome, \n"
                + "	pro_qtde_estoque,\n"
                + "	pro_estoque_min, \n"
                + "	pro_valor_unit    \n"
                + "                order by 2 asc;";

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> grafico = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                FiltroEstoqueMinimo p = new FiltroEstoqueMinimo();
                p.setId(rs.getInt("pro_id"));
                p.setNome(rs.getString("pro_nome"));
                p.setQuantidade(rs.getInt("pro_qtde_estoque"));
                p.setPrecoUnit(rs.getDouble("pro_valor_unit"));
                p.setEstoqueMin(rs.getInt("pro_estoque_min"));
                p.setQtdeVendida(rs.getInt("qtde"));
                grafico.add(p);
            }
            return grafico;
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return null;
    }

}
