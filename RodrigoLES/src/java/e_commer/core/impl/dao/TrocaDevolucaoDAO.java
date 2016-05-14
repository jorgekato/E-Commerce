package e_commer.core.impl.dao;

import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Pedido;
import e_commer.dominio.Produto;
import e_commer.dominio.Relatorio;
import e_commer.dominio.TrocaDevolucao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class TrocaDevolucaoDAO extends AbstractJdbcDAO {

    private final String pedId = "ped_id";
    private final String proId = "pro_id";
    private final String qtde = "td_quantidade";
    private final String dtSolicitacao = "td_dt_solicitacao";
    private final String motivo = "td_motivo";
    private final String status = "td_status";
    private final String anotacao = "td_anotacao";
    private final String acao = "td_acao";
    private final String dtUltModificacao = "td_dt_ultima_modificacao";
    private final String tbRelatorio = "tb_relatorios";
    private final String relDtRegistro = "rel_dt_registro";
    private final String relComentario = "rel_comentario";
    private final String relStatus = "rel_status";
    

    public TrocaDevolucaoDAO() {
        super("tb_troca_devolucao", "td_id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        TrocaDevolucao td = (TrocaDevolucao) entidade;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO " + table + "(");
            sql.append(pedId + ", " + proId + ", " + qtde + ", " + dtSolicitacao + ", ");
            sql.append(motivo + ", " + status + ", " + anotacao + ", " + acao + ") ");
            sql.append("VALUES(?,?,?,?,?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, td.getPedido().getId());
            pst.setInt(2, td.getProId());
            pst.setInt(3, td.getQuantidade());
            Timestamp dtSolic = new Timestamp(td.getDtCadastro().getTime());
            pst.setTimestamp(4, dtSolic);
            pst.setString(5, td.getMotivo());
            pst.setString(6, "AGUARDANDO PRODUTO");
            pst.setString(7, td.getAnotacao());
            pst.setString(8, "");
            pst.executeUpdate();

            Relatorio rel = new Relatorio();
            rel.setComentario("Inicio do Relatorio");
            rel.setStatus("AGUARDANDO PRODUTO");
            td.addRelatorio(rel);

            ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            td.setId(id);
            sql = new StringBuilder();
            pst = null;
            sql.append("INSERT INTO " + tbRelatorio + " (");
            sql.append(idTable + ", " + relDtRegistro + ", " + relComentario + ", " + relStatus);
            sql.append(") VALUES (?,?,?,?)");

            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, td.getId());
            pst.setTimestamp(2, dtSolic);
            pst.setString(3, td.getRelatorio().get(0).getComentario());
            pst.setString(4, td.getStatus());
            pst.executeUpdate();

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
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        TrocaDevolucao td = (TrocaDevolucao) entidade;

        try {
            connection.setAutoCommit(false);
            //altera a tabela de troca de devolucoes
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE " + table + " SET " + status + "=?," + acao + "=?," + dtUltModificacao + "=?");
            sql.append(" WHERE " + idTable + "=?");
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, td.getStatus());
            pst.setString(2, td.getAcao());
            Timestamp dtmodficacao = new Timestamp(td.getDtCadastro().getTime());
            pst.setTimestamp(3, dtmodficacao);
            pst.setInt(4, td.getId());

            pst.executeUpdate();

            //se acao for dar credito ao cliente
            if(td.getAcao().equals("CREDITO NA LOJA")){
                
            }
            
            
            sql = new StringBuilder();
            pst = null;
            //altera a tabela de relatorios
            sql.append("INSERT INTO " + tbRelatorio + " (");
            sql.append(idTable + ", " + relDtRegistro + ", " + relComentario + ", " + relStatus);
            sql.append(") VALUES (?,?,?,?)");
            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, td.getId());
            pst.setTimestamp(2, dtmodficacao);
            pst.setString(3, td.getRelatorio().get(0).getComentario());
            pst.setString(4, td.getStatus());
            pst.executeUpdate();

            if (td.getStatus().equals("CANCELADO")) {
                PedidoDAO pedDAO = new PedidoDAO();
                pedDAO.alterar(td.getPedido());
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
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException, NullPointerException {
        openConnection();
        PreparedStatement pst = null;
        TrocaDevolucao td = (TrocaDevolucao) entidade;

        if (td.getPedido() == null) {
            Cliente cli = new Cliente();
            cli.setId(null);
            Pedido ped = new Pedido();
            ped.setCliente(cli);
            td.setPedido(ped);
        }

        try {
            StringBuilder sql = new StringBuilder();

            if (td.getId() != null) {
                sql.append("SELECT * FROM " + table + " WHERE " + idTable + "=?");
            } else if (td.getPedido().getCliente().getId() != null) {
                sql.append("SELECT * FROM " + table + " JOIN tb_pedidos using(ped_id) "
                        + "JOIN tb_clientes using(cli_id)"
                        + "JOIN tb_produtos using(pro_id)");
                sql.append("WHERE cli_id = ?");
            } else {
                sql.append("SELECT * FROM " + table);
            }

//            
//                ResultSet rs = pst.executeQuery();
//                while(rs.next()){
//                    td = new TrocaDevolucao();
//                    Pedido ped = new Pedido();
//                    Cliente cli = new Cliente();
//                    td.setId(rs.getInt(idTable));
//                    td.setStatus(rs.getString(status));
//                    td.setQuantidade(rs.getInt(qtde));
//                    cli.setId(rs.getInt("cli_id"));
//                    
//                }
//            }
            pst = connection.prepareStatement(sql.toString());
            if (td.getId() != null) {
                pst.setInt(1, td.getId());
            } else if (td.getPedido().getCliente().getId() != null) {
                pst.setInt(1, td.getPedido().getCliente().getId());
            }

            List<EntidadeDominio> td_lista = new ArrayList<EntidadeDominio>();
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                td = new TrocaDevolucao();
                Pedido ped = new Pedido();
                td.setId(rs.getInt(idTable));
                td.setMotivo(rs.getString(motivo));
                td.setStatus(rs.getString(status));
                td.setAcao(rs.getString(acao));
                td.setQuantidade(rs.getInt(qtde));
                td.setAnotacao(rs.getString(anotacao));
                java.sql.Date data = rs.getDate(dtSolicitacao);
                Date dtSolic = new Date(data.getTime());
                td.setDtCadastro(dtSolic);
                td.setProId(rs.getInt(proId));

                ped.setId(rs.getInt(pedId));
                PedidoDAO daoped = new PedidoDAO();
                List<EntidadeDominio> pedidos = daoped.consultar((EntidadeDominio) ped);
                for (EntidadeDominio e : pedidos) {
                    if (e.getId() == ped.getId()) {
                        ped = (Pedido) e;
                    }
                }
                //for para retornar apenas um item de pedido
                for (int i = 0; i < ped.getItens().size();) {
                    if (ItemProduto.class.getName().equals(ped.getItens().get(i).getClass().getName())) {
                        ItemProduto itemp = (ItemProduto) ped.getItens().get(i);
                        if (itemp.getProduto().getId() != td.getProId()) {
                            ped.remove(itemp);//retira da lista
                        } else {
                            i++;
                        }
                    } else {
                        ItemArtesanato itemp = (ItemArtesanato) ped.getItens().get(i);
                        if (itemp.getArtesanato().getId() != td.getProId()) {
                            ped.remove(itemp);
                        } else {
                            i++;
                        }
                    }
                }//for
                td.setPedido(ped);

                sql = new StringBuilder();
                pst = null;
                sql.append("SELECT * FROM " + tbRelatorio);
                sql.append(" WHERE " + idTable + "=?");
                pst = connection.prepareStatement(sql.toString());
                pst.setInt(1, td.getId());
                ResultSet rs1 = pst.executeQuery();
                while (rs1.next()) {
                    Relatorio rel = new Relatorio();
                    java.sql.Date datarel = rs1.getDate(relDtRegistro);
                    Date d = new Date(datarel.getTime());
                    rel.setDtCadastro(d);
                    rel.setComentario(rs1.getString(relComentario));
                    rel.setStatus(rs1.getString(relStatus));
                    td.addRelatorio(rel);
                }

                td_lista.add(td);
            }//WHILE

            return td_lista;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
