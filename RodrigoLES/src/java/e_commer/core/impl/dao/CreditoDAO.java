package e_commer.core.impl.dao;

import e_commer.dominio.Cliente;
import e_commer.dominio.Credito;
import e_commer.dominio.EntidadeDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class CreditoDAO extends AbstractJdbcDAO {

    private final String tbCreditos = "tb_creditos";
    private final String codigo = "cre_codigo";
    private final String saldo = "cre_saldo";
    private final String creDtCad = "cre_dt_cadastro";
    private final String dtValidade = "cre_dt_validade";
    private final String flgAtivo = "cre_flg_ativo";
    private final String cliId = "cli_id";

    public CreditoDAO() {
        super("tb_creditos", "cre_id");
    }

    public void salvar() {

    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        Credito cre = (Credito) entidade;

        try {
            connection.setAutoCommit(false);

            sql.append("INSERT INTO " + tbCreditos + "(" + codigo + ", " + saldo + ", " + creDtCad + ", ");
            sql.append(dtValidade + ", " + flgAtivo + ", " + cliId);
            sql.append(") VALUES(?,?,?,?,?,?)");
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, cre.getCodigo());
            pst.setDouble(2, cre.getSaldo());
            Timestamp credata = new Timestamp(cre.getDtCadastro().getTime());
            pst.setTimestamp(3, credata);
            credata = new Timestamp(cre.getDtValidade().getTime());
            pst.setTimestamp(4, credata);
            pst.setBoolean(5, cre.getFlgAtivo());
            pst.setInt(6, cre.getCliente().getId());
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
        StringBuilder sql = new StringBuilder();
        Credito cre = (Credito) entidade;

        try {
            connection.setAutoCommit(false);
            //expirou a data de validade?(cre.getDtValidade().getTime() - new Date().getTime() <= 0)
            if (cre.getFlgAtivo() == false) {
                sql.append("UPDATE " + tbCreditos + " SET " + flgAtivo + "=? ");
                sql.append("WHERE cre_id=?");
            } else {//utilizou o credito
                sql.append("UPDATE " + tbCreditos + " SET " + codigo + "=?, " + saldo + "=? ");
                sql.append("WHERE cre_id=?");
            }

            pst = connection.prepareStatement(sql.toString());
            if (cre.getFlgAtivo() == false) {
                pst.setBoolean(1, cre.getFlgAtivo());
                pst.setInt(2, cre.getId());
            } else {
                pst.setString(1, cre.getCodigo());
                pst.setDouble(2, cre.getSaldo());
                pst.setInt(3, cre.getId());
            }

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

    public void alterar(EntidadeDominio entidade, Connection conn) throws SQLException {

        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        Credito cre = (Credito) entidade;

        try {
            //expirou a data de validade?(cre.getDtValidade().getTime() - new Date().getTime() <= 0)
            sql.append("UPDATE " + tbCreditos + " SET " + flgAtivo + "=? ");
            sql.append("WHERE " + codigo +"=?");

            pst = conn.prepareStatement(sql.toString());

            pst.setBoolean(1, false);
            pst.setString(2, cre.getCodigo());

            pst.executeUpdate();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        openConnection();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        Credito cre = (Credito) entidade;

        try {

            if (cre.getCodigo() != null && cre.getCliente().getId() != null) {
                sql.append("SELECT * FROM " + tbCreditos + " JOIN  tb_clientes using (cli_id) ");
                sql.append("WHERE cre_codigo =? and cli_id =?");
            } else if (cre.getCliente().getId() != null) {
                sql.append("SELECT * FROM " + tbCreditos + " JOIN  tb_clientes using (cli_id) ");
                sql.append("WHERE " + flgAtivo + "=true and cli_id =?");
            }

            pst = connection.prepareStatement(sql.toString());

            if (cre.getCodigo() != null && cre.getCliente().getId() != null) {
                pst.setString(1, cre.getCodigo());
                pst.setInt(2, cre.getCliente().getId());
            } else if (cre.getCliente().getId() != null) {
                pst.setInt(1, cre.getCliente().getId());
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                Credito cred = new Credito();
                Cliente cli = new Cliente();
                cred.setCodigo(rs.getString(codigo));
                cred.setSaldo(rs.getDouble(saldo));
                java.sql.Date dateLong = rs.getDate(dtValidade);
                Date dtValidade = new Date(dateLong.getTime());
                cred.setDtValidade(dtValidade);
                cred.setFlgAtivo(rs.getBoolean(flgAtivo));
                cli.setId(rs.getInt(cliId));
                cli.setNome(rs.getString("cli_nome"));

                cred.setCliente(cli);
                entidades.add(cred);
            }
            return entidades;

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
        return null;
    }

}
