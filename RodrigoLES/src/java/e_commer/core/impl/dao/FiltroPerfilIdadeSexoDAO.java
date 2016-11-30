/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.dao;

import e_commer.dominio.EntidadeDominio;
import e_commer.filtroAnalise.FiltroPerfilIdadeSexo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class FiltroPerfilIdadeSexoDAO extends AbstractJdbcDAO {

    public FiltroPerfilIdadeSexoDAO(String table, String idTable) {
        super(table, idTable);
    }

    public FiltroPerfilIdadeSexoDAO() {
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

        FiltroPerfilIdadeSexo f = (FiltroPerfilIdadeSexo) entidade;
        PreparedStatement pst = null;
        String sql = null;
        String sql2 = null;

        sql = "select \n"
                + "	sum(case when t.idade < 20 then 1 else 0 end) as ate20,\n"
                + "        sum(case when t.idade >19 and t.idade < 40 then 1 else 0 end) as ate40,\n"
                + "        sum(case when t.idade >39 and t.idade < 60 then 1 else 0 end) as ate60,\n"
                + "        sum(case when t.idade > 59 then 1 else 0 end) as acima60\n"
                + "from\n"
                + "(select cli_nome, count(ped_id), (current_date - cli_dt_nascimento)/365 as Idade\n"
                + "from tb_clientes join \n"
                + "     tb_pedidos using (cli_id) \n"
                + "where (ped_dt_compra between ? and ?) and cli_sexo = 'MASCULINO'   \n"
                + "group by cli_nome, cli_dt_nascimento  \n"
                + "order by 1 desc) t";

        sql2 = "select \n"
                + "	sum(case when t.idade < 20 then 1 else 0 end) as ate20,\n"
                + "        sum(case when t.idade >19 and t.idade < 40 then 1 else 0 end) as ate40,\n"
                + "        sum(case when t.idade >39 and t.idade < 60 then 1 else 0 end) as ate60,\n"
                + "        sum(case when t.idade > 59 then 1 else 0 end) as acima60\n"
                + "from\n"
                + "(select cli_nome, count(ped_id), (current_date - cli_dt_nascimento)/365 as Idade\n"
                + "from tb_clientes join \n"
                + "     tb_pedidos using (cli_id) \n"
                + "where (ped_dt_compra between ? and ?) and cli_sexo = 'FEMININO'   \n"
                + "group by cli_nome, cli_dt_nascimento  \n"
                + "order by 1 desc) t";

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            pst.setDate(1, new java.sql.Date(f.getDt_inicial().getTime()));
            pst.setDate(2, new java.sql.Date(f.getDt_final().getTime()));

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> grafico = new ArrayList<EntidadeDominio>();
            FiltroPerfilIdadeSexo f1 = new FiltroPerfilIdadeSexo();
            while (rs.next()) {

                List<Integer> mas = new ArrayList<Integer>();

                mas.add(rs.getInt("ate20"));
                mas.add(rs.getInt("ate40"));
                mas.add(rs.getInt("ate60"));
                mas.add(rs.getInt("acima60"));

                f1.setMasculino(mas);

            }

            pst = connection.prepareStatement(sql2);

            pst.setDate(1, new java.sql.Date(f.getDt_inicial().getTime()));
            pst.setDate(2, new java.sql.Date(f.getDt_final().getTime()));

            rs = pst.executeQuery();

            while (rs.next()) {
                
                List<Integer> fem = new ArrayList<Integer>();

                fem.add(rs.getInt("ate20"));
                fem.add(rs.getInt("ate40"));
                fem.add(rs.getInt("ate60"));
                fem.add(rs.getInt("acima60"));

                f1.setFeminino(fem);                
            }
            grafico.add(f1);
            return grafico;
        } catch (SQLException erro) {
            erro.printStackTrace();
        }

        return null;
    }

}
