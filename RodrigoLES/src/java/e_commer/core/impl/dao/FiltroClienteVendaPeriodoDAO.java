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
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class FiltroClienteVendaPeriodoDAO extends AbstractJdbcDAO {

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

        if (f.getId() == null) {

            sql = "select cli_nome, sum(ite_valor_unit * ite_qtde) as valor, cli_id\n"
                    + "from tb_clientes join \n"
                    + "     tb_pedidos using (cli_id) join \n"
                    + "     tb_itens_pedidos using(ped_id) join \n"
                    + "     tb_produtos using (pro_id) \n"
                    + "     where (ped_dt_compra between ? and ?)\n"
                    + "     group by (cli_id)     \n"
                    + "     order by valor desc ";
        } else {
            sql = "SELECT \n"
                    + "  cli_nome, \n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 1 then (ite_qtde * ite_valor_unit) else 0 end) as Jan,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 2 then (ite_qtde * ite_valor_unit) else 0 end) as Fev,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 3 then (ite_qtde * ite_valor_unit) else 0 end) as Mar,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 4 then (ite_qtde * ite_valor_unit) else 0 end) as Abr,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 5 then (ite_qtde * ite_valor_unit) else 0 end) as Mai,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 6 then (ite_qtde * ite_valor_unit) else 0 end) as Jun,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 7 then (ite_qtde * ite_valor_unit) else 0 end) as Jul,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 8 then (ite_qtde * ite_valor_unit) else 0 end) as Ago,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 9 then (ite_qtde * ite_valor_unit) else 0 end) as Set,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 10 then (ite_qtde * ite_valor_unit) else 0 end) as Out,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 11 then (ite_qtde * ite_valor_unit) else 0 end) as Nov,\n"
                    + "  sum(case when EXTRACT(MONTH FROM ped_dt_compra)= 12 then ite_qtde else 0 end) as Dez\n"
                    + "from tb_pedidos join \n"
                    + "                tb_itens_pedidos using (ped_id) join\n"
                    + "                tb_clientes using (cli_id)\n"
                    + "where cli_id = ?\n"
                    + "group by\n"
                    + "  cli_nome\n"
                    + "  order by 1 ";
        }
        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (f.getId() == null) {
                pst.setDate(1, new java.sql.Date(f.getDt_inicial().getTime()));
                pst.setDate(2, new java.sql.Date(f.getDt_final().getTime()));
            } else {
                pst.setInt(1, f.getId());
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> grafico = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                FiltroClienteVendaPeriodo f1 = new FiltroClienteVendaPeriodo();
                HashMap<String, Integer> hmQtde = new HashMap<>();
                f1.setNome(rs.getString("cli_nome"));

                if (f.getId() != null) {
                    hmQtde.put("Janeiro", rs.getInt("jan"));
                    hmQtde.put("Fevereiro", rs.getInt("fev"));
                    hmQtde.put("Março", rs.getInt("mar"));
                    hmQtde.put("Abril", rs.getInt("abr"));
                    hmQtde.put("Maio", rs.getInt("mai"));
                    hmQtde.put("Junho", rs.getInt("jun"));
                    hmQtde.put("Julho", rs.getInt("jul"));
                    hmQtde.put("Agosto", rs.getInt("ago"));
                    hmQtde.put("Setembro", rs.getInt("set"));
                    hmQtde.put("Outubro", rs.getInt("out"));
                    hmQtde.put("Novembro", rs.getInt("nov"));
                    hmQtde.put("Dezembro", rs.getInt("dez"));
                    f1.setHmQtde(hmQtde);
                } else {
                    f1.setValor(rs.getDouble("valor"));
                    f1.setId(rs.getInt("cli_id"));
                    f1.setDt_inicial(f.getDt_inicial());
                    f1.setDt_final(f.getDt_final());
                }
                grafico.add(f1);
            }
            return grafico;
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return null;
    }

}
