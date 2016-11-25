package e_commer.core.impl.dao;

import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.AbstractItem;
import e_commer.dominio.Artesanato;
import e_commer.dominio.Cidade;
import e_commer.dominio.Cliente;
import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Estado;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Pedido;
import e_commer.dominio.Produto;
import e_commer.dominio.Relatorio;
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
public class PedidoDAO extends AbstractJdbcDAO {

    private final String forma_pgto = "ped_forma_pgto";
    private final String tipo_envio = "ped_tipo_envio";
    private final String dt_compra = "ped_dt_compra";
    private final String status = "ped_status";
    private final String tbItensPedido = "tb_itens_pedidos";
    private final String cliId = "cli_id";

    private final String pedId = "ped_id";
    private final String proId = "pro_id";
    private final String ite_qtde = "ite_qtde";
    private final String vlrUnit = "ite_valor_unit";
    private final String item_status = "item_status";
    private final String item_tipo = "item_tipo";
    private final String endEntrega = "ped_end_id";

    private final String tbHistorico = "tb_historico_pedido";
    private final String hisDtRegistro = "his_dt_registro";
    private final String hisComentario = "his_comentario";
    private final String hisStatus = "his_status";

    private final String tbEnderecos = "tb_enderecos";
    private final String bairro = "end_bairro";
    private final String cidade = "end_cidade";
    private final String estado = "end_estado";
    private final String logradouro = "end_logradouro";
    private final String numero = "end_numero";
    private final String complemento = "end_complemento";
    private final String cep = "end_cep";
    
    
    public PedidoDAO() {
        super("tb_pedidos", "ped_id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;

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
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;

        try {
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(table);
            sql.append(" SET ");
            sql.append(status);
            sql.append("=? ");
            sql.append("WHERE ");
            sql.append(idTable);
            sql.append("=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, pedido.getStatus());
            pst.setInt(2, pedido.getId());
            pst.executeUpdate();

            if (pedido.getStatus().equals("ENVIADO")) {
                ProdutoDAO proDAO = new ProdutoDAO();
                Resultado resultado = null;
                List<AbstractItem> entidades = pedido.getItens();
                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {

                        if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                            ItemProduto item = (ItemProduto) entidades.get(i);
                            item.setItem_Status(pedido.getStatus());
                            proDAO.alterar(item);
                        }
                        pst.executeUpdate();

                    }
                }
            } else if (pedido.getStatus().equals("CANCELADO")) {
                ProdutoDAO proDAO = new ProdutoDAO();
                Resultado resultado = null;
                List<AbstractItem> entidades = pedido.getItens();
                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {

                        if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                            ItemProduto item = (ItemProduto) entidades.get(i);
                            item.setItem_Status(pedido.getStatus());
                            proDAO.alterar(item);
                        }
                        pst.executeUpdate();

                    }
                }
            }

            sql = new StringBuilder();
            pst = null;
            //altera a tabela de historico de pedidos
            sql.append("INSERT INTO " + tbHistorico + " (");
            sql.append(idTable + ", " + hisDtRegistro + ", " + hisComentario + ", " + hisStatus);
            sql.append(") VALUES (?,?,?,?)");
            pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, pedido.getId());
            Timestamp dtmodificacao = new Timestamp(new Date().getTime());
            pst.setTimestamp(2, dtmodificacao);
            pst.setString(3, pedido.getHistorico().get(0).getComentario());
            pst.setString(4, pedido.getStatus());
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
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws NullPointerException {
        PreparedStatement pst = null;

        Pedido pedido = (Pedido) entidade;
        String sql = null;
        boolean flg;

        try {
            openConnection();

            if (pedido.getPagamento() == null) {
                pedido.setPagamento("");
            }

            if (pedido.getCliente() == null) {
                Cliente cli = new Cliente();
                pedido.setCliente(cli);
            }

            if (pedido.getId() != null) {
                flg = true;
            } else {
                flg = false;
            }

            if (pedido.getId() == null && pedido.getPagamento().equals("") && pedido.getCliente().getId() == null) {
                sql = "SELECT * FROM " + table + " join  tb_clientes using (cli_id) order by ped_dt_compra desc";
            } else if (pedido.getId() != null && pedido.getPagamento().equals("") && pedido.getCliente().getId() == null) {
                sql = "SELECT * FROM " + table + " join tb_clientes using (cli_id) WHERE " + idTable + "=? order by ped_dt_compra desc ";
            } else if (pedido.getId() == null && pedido.getPagamento().equals("") && pedido.getCliente().getId() != null) {
                sql = "SELECT * FROM " + table + " join tb_clientes using (cli_id) WHERE cli_id=? order by ped_dt_compra desc";
            } else if (pedido.getId() == null && !pedido.getPagamento().equals("") && pedido.getCliente().getId() == null) {
                sql = "SELECT * FROM " + table + " join tb_Clientes using (cli_id) WHERE art_nome like ? order by ped_dt_compra desc";

            }

            pst = connection.prepareStatement(sql);

            if (pedido.getId() != null && pedido.getPagamento().equals("") && pedido.getCliente().getId() == null) {
                pst.setInt(1, pedido.getId());
            } else if (pedido.getId() == null && !pedido.getPagamento().equals("") && pedido.getCliente().getId() == null) {
                pst.setString(1, "%" + pedido.getPagamento() + "%");
            } else if (pedido.getId() == null && pedido.getPagamento().equals("") && pedido.getCliente().getId() != null) {
                pst.setInt(1, pedido.getCliente().getId());
            }

            Pedido p = new Pedido();
            Cliente c;

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
            while (rs.next()) {

                p = new Pedido();
                c = new Cliente();
                Endereco e = new Endereco();
                p.setId(rs.getInt(idTable));
                c.setId(rs.getInt(cliId));
                c.setNome(rs.getString("cli_nome"));
                c.setCpf(rs.getString("cli_cpf"));
                c.setSexo(rs.getString("cli_sexo"));
                c.setEmail(rs.getString("cli_email"));
                p.setCliente(c);
                p.setStatus(rs.getString(status));
                p.setPagamento(rs.getString(forma_pgto));
                p.setServico(rs.getString(tipo_envio));
                java.sql.Date dtCadastroEmLong = rs.getDate(dt_compra);
                Date dtCadastro = new Date(dtCadastroEmLong.getTime());
                p.setDtCadastro(dtCadastro);
                e.setId(rs.getInt(endEntrega));
                
                sql= null;
                pst = null;
                sql = "select * from " + tbEnderecos + " where end_id=?"; 
                pst = connection.prepareStatement(sql);
                pst.setInt(1, e.getId());
                ResultSet rsEnd = pst.executeQuery();
                while(rsEnd.next()){ 
                    Cidade cid = new Cidade();
                    Estado est = new Estado();
                    est.setNome(rsEnd.getString(estado));
                    cid.setEstado(est);
                    cid.setNome(rsEnd.getString(cidade));
                    e.setCidade(cid);
                    e.setLogradouro(rsEnd.getString(logradouro));
                    e.setNumero(rsEnd.getString(numero));
                    e.setBairro(rsEnd.getString(bairro));
                    e.setComplemento(rsEnd.getString(complemento));
                    e.setCep(rsEnd.getString(cep));
                }
                p.setEndereco(e); 
                
                sql = null;
                pst = null;
                sql= "SELECT * FROM " + tbHistorico + " WHERE " + idTable + "=?";
                pst = connection.prepareStatement(sql.toString());
                pst.setInt(1, p.getId());
                ResultSet rs1 = pst.executeQuery();
                while (rs1.next()) {
                    Relatorio rel = new Relatorio();
                    java.sql.Date datarel = rs1.getDate(hisDtRegistro);
                    Date d = new Date(datarel.getTime());
                    rel.setDtCadastro(d);
                    rel.setComentario(rs1.getString(hisComentario));
                    rel.setStatus(rs1.getString(hisStatus));
                    p.addHistorico(rel);
                }
                pedidos.add(p);
            }

            if (flg) {

                sql = "select * from tb_itens_pedidos where ped_id = ?";
                pst = connection.prepareStatement(sql);
                pst.setInt(1, p.getId());
                rs = pst.executeQuery();

                while (rs.next()) {

                    if (rs.getString("item_tipo").equals("ARTESANATO")) {

                        Artesanato artesanato = new Artesanato();
                        artesanato.setId((rs.getInt("pro_id")));
                        //artesanato.setPrecoUnit(rs.getDouble("art_valor_unit"));
                        ArtesanatoDAO daoa = new ArtesanatoDAO();
                        ItemArtesanato itema = new ItemArtesanato();
                        List<EntidadeDominio> art = daoa.consultar((EntidadeDominio) artesanato);

//                        fachada = new Fachada();
//                        resultado = fachada.consultar((EntidadeDominio) artesanato);
                        for (EntidadeDominio e : art) {
                            if (e.getId() == artesanato.getId()) {
                                itema.setArtesanato((Artesanato) e);
                            }
                        }
                        itema.setQuantidade((rs.getInt("ite_qtde")));
                        itema.setValorUnit(rs.getDouble("ite_valor_unit"));
                        itema.setTipo_item(rs.getString("item_tipo"));
                        //preciso add o campo item_status
                        itema.setItem_Status(rs.getString("item_status"));
                        p.adiciona(itema);
                        //pedidos.add(0, p);
                        pedidos.set(0, p);

                    } //Adiciona um item de produto ao carrinho
                    else if (rs.getString("item_tipo").equals("PRODUTO")) {

                        Produto produto = new Produto();
                        produto.setId((rs.getInt("pro_id")));
                        // produto.setPrecoUnit(rs.getDouble("pro_valor_unit"));
                        ProdutoDAO daop = new ProdutoDAO();
                        ItemProduto itemp = new ItemProduto();
                        List<EntidadeDominio> prod = daop.consultar((EntidadeDominio) produto);
//                        fachada = new Fachada();
//                        resultado = fachada.consultar((EntidadeDominio) produto);
                        for (EntidadeDominio e : prod) {
                            if (e.getId() == produto.getId()) {
                                itemp.setProduto((Produto) e);
                            }
                        }
                        itemp.setQuantidade((rs.getInt("ite_qtde")));
                        itemp.setValorUnit(rs.getDouble("ite_valor_unit"));
                        itemp.setTipo_item(rs.getString("item_tipo"));
                        //preciso add o campo item_status
                        itemp.setItem_Status(rs.getString("item_status"));
                        p.adiciona(itemp);
                        //pedidos.add(0, p);
                        pedidos.set(0, p);
                    }

                }
            }

            return pedidos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
