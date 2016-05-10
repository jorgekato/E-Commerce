/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.util;

import e_commer.dominio.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Henrique
 */
public class ProdutoDAO {

    protected Connection conn = null;
    protected PreparedStatement pst = null;

    public boolean salvar(Produto prod) {

        try {
            conn = Conexao.getConnection();
            if (conn == null) {
                return false;
            }

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO produtos("
                    + " nome,\n"
                    + "	marca,\n"
                    + "	modelo,\n"
                    + "	quantidade,\n"
                    + "	fabricante,\n"
                    + "	valor_unit,\n"
                    + "	estoque_min,\n"
                    + "	descricao)");
            sql.append("VALUES (?,?,?,?,?,?,?,?)");

            pst = conn.prepareStatement(sql.toString());
            pst.setString(1, prod.getNome());
            pst.setString(2, prod.getMarca());
            pst.setString(3, prod.getModelo());
            pst.setInt(4, prod.getQuantidade());
            pst.setString(5, prod.getFabricante());
            pst.setDouble(6, prod.getPrecoUnit());
            pst.setInt(7, prod.getEstoqueMin());
            pst.setString(8, prod.getDescricao());
            pst.executeUpdate();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                pst.close();
                conn.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

    }

    public boolean alterar(Produto prod) {

        try {
            conn = Conexao.getConnection();
            if (conn == null) {
                return false;
            }

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE produtos SET "
                    + " nome=?,\n"
                    + "	marca=?,\n"
                    + "	modelo=?,\n"
                    + "	quantidade=?,\n"
                    + "	fabricante=?,\n"
                    + "	valor_unit=?,\n"
                    + "	estoque_min=?,\n"
                    + "	descricao=?");
            sql.append("WHERE prod_id=?");

            pst = conn.prepareStatement(sql.toString());
            pst.setString(1, prod.getNome());
            pst.setString(2, prod.getMarca());
            pst.setString(3, prod.getModelo());
            pst.setInt(4, prod.getQuantidade());
            pst.setString(5, prod.getFabricante());
            pst.setDouble(6, prod.getPrecoUnit());
            pst.setInt(7, prod.getEstoqueMin());
            pst.setString(8, prod.getDescricao());
            pst.setInt(9, prod.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                pst.close();
                conn.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

    }

    public Produto buscar(int id) {

        try {
            conn = Conexao.getConnection();
            //achar um jeito de verificar se a conn é null e como retornar

            StringBuilder sql = new StringBuilder();
            sql.append("Select "
                    + " cat_id,\n "
                    + " nome,\n"
                    + "	marca,\n"
                    + "	modelo,\n"
                    + "	quantidade,\n"
                    + "	fabricante,\n"
                    + "	valor_unit,\n"
                    + "	estoque_min,\n"
                    + "	descricao");
            sql.append("From produtos ");
            sql.append("WHERE prod_id=?");

            pst = conn.prepareStatement(sql.toString());
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            Produto prod = null;
            while (rs.next()) {
                prod = new Produto();
                prod.setNome(rs.getString("categoria"));
                prod.setMarca(rs.getString("marca"));
                prod.setModelo(rs.getString("modelo"));
                //prod.setCategoria(request.getParameter("categoria"));
                prod.setQuantidade(rs.getInt("qtde"));
                prod.setFabricante(rs.getString("fabricante"));
                prod.setPrecoUnit(rs.getDouble("valorUnit"));
                prod.setEstoqueMin(rs.getInt("estoMin"));
                prod.setDescricao(rs.getString("descricao"));
            }
            return prod;

        } catch (Exception e) {

        } finally {
            try {
                pst.close();
                conn.close();

            } catch (Exception e) {

            }
        }
        return null;
    }

    public boolean excluir(Produto prod) {

        try {
            conn = Conexao.getConnection();
            if (conn == null) {
                return false;
            }

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE produtos SET "
                    + " nome=?,\n"
                    + "	marca=?,\n"
                    + "	modelo=?,\n"
                    + "	quantidade=?,\n"
                    + "	fabricante=?,\n"
                    + "	valor_unit=?,\n"
                    + "	estoque_min=?,\n"
                    + "	descricao=?");
            sql.append("WHERE cat_id=?");

            pst = conn.prepareStatement(sql.toString());
            pst.setString(1, prod.getNome());
            pst.setString(2, prod.getMarca());
            pst.setString(3, prod.getModelo());
            pst.setInt(4, prod.getQuantidade());
            pst.setString(5, prod.getFabricante());
            pst.setDouble(6, prod.getPrecoUnit());
            pst.setInt(7, prod.getEstoqueMin());
            pst.setString(8, prod.getDescricao());
            pst.executeUpdate();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                pst.close();
                conn.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

    }

}


