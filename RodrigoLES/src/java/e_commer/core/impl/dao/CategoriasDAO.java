/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.dao;

import java.sql.SQLException;
import java.util.List;

import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Categorias;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Henrique
 */
public class CategoriasDAO extends AbstractJdbcDAO {
    
    private final String nome = "cat_nome_categoria";
    private final String descricao = "cat_descricao";
    private final String flg_ativo = "cat_flg_ativo";
    private final String dt_cadastro = "cat_dt_cadastro";

    public CategoriasDAO() {
        super("tb_categorias", "cat_id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) {

        //abrindo a conexão
        openConnection();
        //criando um objeto para preparar o comando sql
        PreparedStatement pst = null;
        //Esta fazendo um cast dizendo que a entidade é uma filha
        Categorias categoria = (Categorias) entidade;

        try {
            //Desativando o auto-commit...
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(table);
            sql.append(" (");
            sql.append(nome);
            sql.append(", ");
            sql.append(descricao);
            sql.append(", ");
            sql.append(flg_ativo);
            sql.append(", ");
            sql.append(dt_cadastro);
            sql.append(" ) ");
            sql.append("VALUES (?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, categoria.getNomeCategoria().toUpperCase());
            pst.setString(2, categoria.getDescricao().toUpperCase());
            pst.setBoolean(3, categoria.getFlg_ativo());
            Timestamp time = new Timestamp(categoria.getDtCadastro().getTime());
            pst.setTimestamp(4, time);
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            //não entendi o que ele faz aqui
            if (rs.next()) {
                id = rs.getInt(1);
            }
            categoria.setId(id);

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
    public void alterar(EntidadeDominio entidade) throws SQLException{
        
        openConnection();
        PreparedStatement pst = null;
        Categorias categoria = (Categorias) entidade;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(table);
            sql.append(" SET ");
            sql.append(nome);
            sql.append("=?, ");
            sql.append(descricao);
            sql.append("=?, ");
            sql.append(flg_ativo);  
            sql.append("=? ");
            sql.append("WHERE ");
            sql.append(idTable);
            sql.append("=?");            
            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, categoria.getNomeCategoria().toUpperCase());
            pst.setString(2, categoria.getDescricao().toUpperCase());
            pst.setBoolean(3, categoria.getFlg_ativo());
            pst.setInt(4, categoria.getId());

            pst.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        
        PreparedStatement pst = null;
        Categorias categoria = (Categorias) entidade;
        String sql = null;

        if (categoria.getNomeCategoria()== null) {
            categoria.setNomeCategoria("");
        }

        if (categoria.getId() == null && categoria.getNomeCategoria().equals("")) {
            sql = "SELECT * FROM "+ table + " order by cat_nome_categoria";
        } else if (categoria.getId() != null && categoria.getNomeCategoria().equals("")) {
            sql = "SELECT * FROM "+ table + " WHERE " + idTable +"=?";
        } else if (categoria.getId() == null && !categoria.getNomeCategoria().equals("")) {
            sql = "SELECT * FROM "+ table + " WHERE " + nome + " like ?";

        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (categoria.getId() != null && categoria.getNomeCategoria().equals("")) {
                pst.setInt(1, categoria.getId());
            } else if (categoria.getId() == null && !categoria.getNomeCategoria().equals("")) {
                pst.setString(1, (categoria.getNomeCategoria()+ "%").toUpperCase());
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                Categorias cat = new Categorias();
                cat.setId(rs.getInt(idTable));
                cat.setNomeCategoria(rs.getString(nome));
                cat.setDescricao(rs.getString(descricao));
                cat.setFlg_ativo(rs.getBoolean(flg_ativo));
                java.sql.Date dtCadastroEmLong = rs.getDate(dt_cadastro);
                Date dtCadastro = new Date(dtCadastroEmLong.getTime());
                cat.setDtCadastro(dtCadastro);
                categorias.add(cat);
            }
            return categorias;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }

}
