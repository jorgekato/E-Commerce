/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.teste;

import e_commer.core.impl.dao.CreditoDAO;
import e_commer.core.util.Conexao;
import e_commer.dominio.AbstractItem;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Pedido;
import e_commer.dominio.Relatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Henrique Padovani
 */
public class TestePedido {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Pedido pedido = new Pedido();
        
        Connection connection = Conexao.getConnection();
        PreparedStatement pst = null;
/*
        try {
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO ");
            sql.append(table);
            sql.append(" (");
            sql.append(cliId);
            sql.append(", ");
            sql.append(forma_pgto);
            sql.append(", ");
            sql.append(tipo_envio);
            sql.append(", ");
            sql.append(dt_compra);
            sql.append(", ");
            sql.append(status);
            sql.append(", ");
            sql.append(endEntrega);
            sql.append(" ) ");
            sql.append(" VALUES (?,?,?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, pedido.getCliente().getId());
            pst.setString(2, pedido.getPagamento());
            pst.setString(3, pedido.getServico());
            Timestamp dtPed = new Timestamp(pedido.getDtCadastro().getTime());
            pst.setTimestamp(4, dtPed);
            pst.setString(5, pedido.getStatus());
            pst.setInt(6, pedido.getEndereco().getId());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            pedido.setId(id);

            List<AbstractItem> entidades = pedido.getItens();
            if (entidades != null) {
                for (int i = 0; i < entidades.size(); i++) {

                    sql = new StringBuilder();
                    sql.append("INSERT INTO ");
                    sql.append(tbItensPedido);
                    sql.append("(" + pedId + "," + proId + "," + ite_qtde + "," + vlrUnit + "," + item_status + "," + item_tipo + ")");
                    sql.append(" VALUES (?,?,?,?,?,?)");

                    pst = connection.prepareStatement(sql.toString(),
                            Statement.RETURN_GENERATED_KEYS);

                    pst.setInt(1, pedido.getId());
                    if (ItemArtesanato.class.getName().equals(entidades.get(i).getClass().getName())) {
                        ItemArtesanato item = (ItemArtesanato) entidades.get(i);
                        pst.setInt(2, item.getArtesanato().getId());
                        pst.setInt(3, item.getQuantidade());
                        pst.setDouble(4, item.getArtesanato().getPrecoUnit());
                        pst.setString(5, "APROVADO");
                        pst.setString(6, item.getTipo_item());

                    } else if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                        ItemProduto item = (ItemProduto) entidades.get(i);
                        pst.setInt(2, item.getProduto().getId());
                        pst.setInt(3, item.getQuantidade());
                        pst.setDouble(4, item.getProduto().getPrecoUnit());
                        pst.setString(5, "APROVADO");
                        pst.setString(6, item.getTipo_item());
                    }
                    pst.executeUpdate();
                }
            }

            Relatorio rel = new Relatorio();
            rel.setComentario("Inicio do Relatorio");
            rel.setStatus("APROVADO");

            sql = new StringBuilder();
            pst = null;
            sql.append("INSERT INTO " + tbHistorico + " (");
            sql.append(idTable + ", " + hisDtRegistro + ", " + hisComentario + ", " + hisStatus);
            sql.append(") VALUES (?,?,?,?)");

            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, pedido.getId());
            pst.setTimestamp(2, dtPed);
            pst.setString(3, rel.getComentario());
            pst.setString(4, rel.getStatus());
            pst.executeUpdate();

            //Altera se houver crédito
            if(pedido.getCredito() != null){
                
                CreditoDAO creDAO = new CreditoDAO();
                creDAO.alterar(pedido.getCredito(), connection);
                
            }
            
            
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       */ 
    }
    
}
