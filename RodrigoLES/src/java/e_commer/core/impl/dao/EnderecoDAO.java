package e_commer.core.impl.dao;

import e_commer.dominio.Cidade;
import e_commer.dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Estado;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoDAO extends AbstractJdbcDAO {

    private final String tableEndereco = "tb_enderecos";
    private final String endId = "end_id";
    private final String cidade = "end_cidade";
    private final String estado = "end_estado";
    private final String bairro = "end_bairro";
    private final String logradouro = "end_logradouro";
    private final String numero = "end_numero";
    private final String complemento = "end_complemento";
    private final String cep = "end_cep";
    private final String flgAtivo = "end_flg_ativo";
    private final String cliId = "cli_id";
    

    protected EnderecoDAO(String table, String idTable) {
        super("tb_enderecos", "end_id");
    }

    public EnderecoDAO(Connection cx) {
        super(cx, "tb_enderecos", "end_id");
    }

    public EnderecoDAO() {
        super("tb_enderecos", "end_id");
    }

    public void salvar(EntidadeDominio entidade) {
        if (connection == null) {
            openConnection();
        }
        PreparedStatement pst = null;
        Endereco end = (Endereco) entidade;
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO tb_endereco(cidade, estado, ");
        sql.append("logradouro, numero, cep) ");
        sql.append(" VALUES (?, ?, ?, ?, ?)");
        try {
            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, end.getCidade().getNome().toUpperCase());
            pst.setString(2, end.getCidade().getEstado().getNome().toUpperCase());
            pst.setString(3, end.getLogradouro().toUpperCase());
            pst.setString(4, end.getNumero());
            pst.setString(5, end.getCep());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int idEnd = 0;
            if (rs.next()) {
                idEnd = rs.getInt(1);
            }
            end.setId(idEnd);

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (ctrlTransaction) {
                try {
                    pst.close();
                    if (ctrlTransaction) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        //Cliente cliente = (Cliente) entidade;
        Endereco end = (Endereco) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        if (connection == null) {
            openConnection();
        }
        try {
            connection.setAutoCommit(false);
            
                sql.append("UPDATE ");
                sql.append(tableEndereco);
                sql.append(" SET ");
                sql.append(flgAtivo);
                sql.append("=? ");
                sql.append("WHERE ");
                sql.append(idTable);
                sql.append("=?");

                pst = connection.prepareStatement(sql.toString());
                pst.setBoolean(1, end.getFlgAtivo());
                pst.setInt(2, end.getId());

                pst.executeUpdate();

            
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * TODO Descri��o do M�todo
     *
     * @param entidade
     * @return
     * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
     */
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM ");
        sql.append(tableEndereco);
        sql.append(" WHERE ");
        sql.append(cliId + "=?");
        sql.append(" and " + flgAtivo + "=true order by " + endId + " asc");

        try {
            openConnection();
            pst = connection.prepareStatement(sql.toString());

            pst.setInt(1, cliente.getId());

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
            while (rs.next()) {

                Endereco end = new Endereco();
                Cidade cid = new Cidade();
                Estado estado = new Estado();

                estado.setNome(rs.getString(this.estado));
                cid.setEstado(estado);
                cid.setNome(rs.getString(cidade));
                end.setCidade(cid);
                end.setId(rs.getInt(endId));
                end.setLogradouro(rs.getString(logradouro));
                end.setNumero(rs.getString(numero));
                end.setCep(rs.getString(cep));
                end.setBairro(rs.getString(bairro));
                end.setFlgAtivo(rs.getBoolean(flgAtivo));
                enderecos.add(end);
            }
            return enderecos;
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return null;
    }

}
