/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.dao;

import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class ConsultaDAO extends AbstractJdbcDAO {

    String nome;
    Double valor;

    public ConsultaDAO() {
        super(null, null);
    }
    
    
    
    public ConsultaDAO(Connection connection, String table, String idTable) {
        super(table, idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
         return null;//To change body of generated methods, choose Tools | Templates.
    }

    public List<Cliente> consultar(Date dt_inicial, Date dt_final){
        String sql = "select cli_nome, sum(ite_valor_unit * ite_qtde)"
                + " from tb_clientes join tb_pedidos using(cli_id) join "
                + "tb_itens_pedidos using(ped_id) join "
                + "tb_produtos using (pro_id) "
                + "where ped_dt_compra between '01/01/2016' and '01/06/2016' "
                + "group by (cli_nome)"
                + "order by 2 desc";
        
        ConsultaDAO a = new ConsultaDAO();
        
        return null;
    }
//    
//
//
//select cli_nome, sum(ite_valor_unit * ite_qtde)
//from tb_clientes join 
//     tb_pedidos using (cli_id) join 
//     tb_itens_pedidos using(ped_id) join 
//     tb_produtos using (pro_id) 
//     where ped_dt_compra = '17/05/2016'
//     group by (cli_nome)     
//     order by 2 desc
    
}
