/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import e_commer.core.util.ManipulaImagem;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Imagem;
import e_commer.dominio.Produto;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henrique
 */
public class ImagemDAO extends AbstractJdbcDAO {

    public ImagemDAO() {
        super("imagens", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;

        try {
            connection.setAutoCommit(false);
            // preparar o sql
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO images ");
            sql.append("(img, id_produtos) ");
            sql.append(" VALUES (?,?)");

            pst = connection.prepareStatement(sql.toString());
           
            pst.setBytes(1, produto.getFoto().getImagem());
            
            pst.setInt(2, produto.getId());
            // executar no banco
            pst.executeUpdate();

            connection.commit(); // dá commit nas operações

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } 
//        catch (IOException ex) {
//            Logger.getLogger(ImagemDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } 
        finally {
            try {
                if (ctrlTransaction) {
                    pst.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;

        try {
            connection.setAutoCommit(false);
            // preparar o sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE imagens SET");
            sql.append(" imagem=? ");
            sql.append(" WHERE id_produtos=?");

            pst = connection.prepareStatement(sql.toString());

            // substituir as ? pelos valores do objeto
            //pst.setBlob(1, produto.getFoto().getImagem());
            pst.setInt(2, produto.getId());
            // executar no banco
            pst.executeUpdate();

            connection.commit(); // dá commit nas operações

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (ctrlTransaction) {
                    pst.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        if (connection == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;

        try {
            connection.setAutoCommit(false);
            // preparar o sql
            String sql = null;

            if (produto.getId() == null && produto.getFoto() == null) {
                sql = "SELECT * FROM imagens";
            } else if (produto.getId() != null) {
                sql = "SELECT * FROM images WHERE id_produtos=?";
            } else if (produto.getId() == null && produto.getFoto() != null && produto.getId() != null) {
                sql = "SELECT * FROM imagens WHERE id=?";
            }

            pst = connection.prepareStatement(sql.toString());

            // substituir as ? pelos valores do objeto
            if (sql.equals("SELECT * FROM imagens WHERE id_produtos=?")) {
                pst.setInt(1, produto.getId());
            } else if (sql.equals("SELECT * FROM imagens WHERE id=?")) {
                pst.setInt(1, produto.getFoto().getId());
            }

            // executar no banco
            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> resultado = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                Imagem img = new Imagem();
                img.setImagem(rs.getBytes("img"));
                //ver como alterar
                img.setUrl(ManipulaImagem.convertImagemBase64(img.getImagem()));

                resultado.add(img);
            }

            return resultado;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ctrlTransaction) {
                    pst.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
