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
import java.sql.Connection;

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
            sql.append("INSERT INTO tb_imagens ");
            sql.append("(img_imagem, pro_id, posicao) ");
            sql.append(" VALUES (?,?,?)");

            Imagem[] img = produto.getFoto();
            pst = connection.prepareStatement(sql.toString());
            for (int i = 0; i < img.length; i++) {
                pst.setBytes(1, img[i].getImagem());
                pst.setInt(2, produto.getId());
                pst.setInt(3, i);
                // executar no banco
                pst.executeUpdate();
            }
            connection.commit(); // dá commit nas operações

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } //        catch (IOException ex) {
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

    public void salvar(EntidadeDominio entidade, Connection conn) throws SQLException {
        if (conn == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;

        try {
            // preparar o sql
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_imagens ");
            sql.append("(img_imagem, pro_id, posicao) ");
            sql.append(" VALUES (?,?,?)");

            Imagem[] img = produto.getFoto();
            pst = conn.prepareStatement(sql.toString());
            for (int i = 0; i < img.length; i++) {
                pst.setBytes(1, img[i].getImagem());
                pst.setInt(2, produto.getId());
                pst.setInt(3, i);
                // executar no banco
                pst.executeUpdate();
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (ctrlTransaction) {
                    pst.close();
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
            sql.append("UPDATE tb_imagens SET");
            sql.append(" img_imagem=? ");
            sql.append(" WHERE pro_id=?");

            pst = connection.prepareStatement(sql.toString());

            // substituir as ? pelos valores do objeto
            //pst.setBytes(1, produto.getFoto().getImagem());
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

    public void alterar(EntidadeDominio entidade, Connection conn) throws SQLException {
        if (conn == null) {
            openConnection();
        }

        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;

        try {

            // preparar o sql
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE tb_imagens SET");
            sql.append(" img_imagem=? ");
            sql.append(" WHERE pro_id=? and posicao=?");

            pst = conn.prepareStatement(sql.toString());

            // substituir as ? pelos valores do objeto
            for (int i = 0; i < produto.getFoto().length; i++) {
                if (produto.getFoto()[i] != null) {
                    pst.setBytes(1, produto.getFoto()[i].getImagem());
                    pst.setInt(2, produto.getId());
                    pst.setInt(3, produto.getFoto()[i].getPosicao());
                    // executar no banco
                    pst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (ctrlTransaction) {
                    pst.close();
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
//                pst.setInt(1, produto.getFoto().getId());
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

    public List<EntidadeDominio> consultar(EntidadeDominio entidade, Connection conn) throws SQLException {
        if (conn == null) {
            //verificar pois tem que sair do consultar e não abrir conexão
            openConnection();
        }

        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;

        try {

            // preparar o sql
            String sql = null;

            if (produto.getId() == null && produto.getFoto() == null) {
                sql = "SELECT * FROM tb_imagens";
            } else if (produto.getId() != null) {
                sql = "SELECT * FROM tb_imagens WHERE pro_id=?";
            } else if (produto.getId() == null && produto.getFoto() != null && produto.getId() != null) {
                sql = "SELECT * FROM tb_imagens WHERE id=?";
            }

            pst = conn.prepareStatement(sql.toString());

            // substituir as ? pelos valores do objeto
            if (sql.equals("SELECT * FROM tb_imagens WHERE pro_id=?")) {
                pst.setInt(1, produto.getId());
            } else if (sql.equals("SELECT * FROM tb_imagens WHERE id=?")) {
//                pst.setInt(1, produto.getFoto().getId());
            }

            // executar no banco
            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> resultado = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                Imagem img = new Imagem();
                img.setImagem(rs.getBytes("img_imagem"));
                img.setPosicao(rs.getInt("posicao"));
                img.setId(rs.getInt("img_id"));
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
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
