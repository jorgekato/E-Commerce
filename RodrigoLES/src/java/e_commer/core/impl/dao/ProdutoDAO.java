package e_commer.core.impl.dao;

import e_commer.core.aplicacao.Resultado;
import e_commer.core.util.ManipulaImagem;
import e_commer.dominio.Categorias;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Imagem;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Produto;

public class ProdutoDAO extends AbstractJdbcDAO {

    private final String nome = "pro_nome";
    private final String marca = "pro_marca";
    private final String modelo = "pro_modelo";
    private final String valor_unit = "pro_valor_unit";
    private final String qtde_estoque = "pro_qtde_estoque";
    private final String estoque_min = "pro_estoque_min";
    private final String qtde_max_venda = "pro_qtde_max_venda";
    private final String descricao = "pro_descricao";
    private final String flg_ativo = "pro_flg_ativo";
    private final String dtCadastro = "pro_dt_cadastro";
    private final String cat_id = "cat_id";
    private final String cat_nome = "cat_nome_categoria";
    private final String tbCategoria = "tb_categorias";

    public ProdutoDAO() {
        super("tb_produtos", "pro_id");
    }

    public void salvar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Produto produto = (Produto) entidade;

        if (produto.getModelo() == null) {
            produto.setModelo(" ");
        }
        if (produto.getMarca() == null) {
            produto.setMarca(" ");
        }
        try {
            connection.setAutoCommit(false);
            //Falta incluir categoria	
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO " + table + "(");
            sql.append(nome + "," + marca + "," + modelo + "," + qtde_estoque + "," + valor_unit + "," + estoque_min + ",");
            sql.append(qtde_max_venda + "," + descricao + "," + flg_ativo + "," + dtCadastro + "," + cat_id + ")");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, produto.getNome().toUpperCase());
            pst.setString(2, produto.getMarca().toUpperCase());
            pst.setString(3, produto.getModelo().toUpperCase());
            pst.setInt(4, produto.getQuantidade());
            pst.setDouble(5, produto.getPrecoUnit());
            pst.setInt(6, produto.getEstoqueMin());
            pst.setInt(7, produto.getQtdeMaxVenda());
            pst.setString(8, produto.getDescricao().toUpperCase());
            pst.setBoolean(9, produto.getFlg_ativo());
            Timestamp time = new Timestamp(produto.getDtCadastro().getTime());
            pst.setTimestamp(10, time);
            pst.setInt(11, produto.getCategoria().getId());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            produto.setId(id);

            ImagemDAO imgDAO = new ImagemDAO();
            imgDAO.salvar(produto, connection);

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

    /**
     * TODO Descri��o do M�todo
     *
     * @param entidade
     * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
     */
    @Override
    public void alterar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        //Produto produto = (Produto) entidade;
        Produto produto = null;
        StringBuilder sql = null;

        try {
            connection.setAutoCommit(false);

            //Se alteração por decorrencia de pedido.
            if (ItemProduto.class.getName().equals(entidade.getClass().getName())) {
                ItemProduto item = (ItemProduto) entidade;
                produto = (Produto) item.getProduto();

                sql = new StringBuilder();
                sql.append("UPDATE " + table + " SET " + qtde_estoque + "=? ");
                sql.append("WHERE " + idTable + "=?");

                pst = connection.prepareStatement(sql.toString());
                if (item.getItem_Status().equals("ENVIADO")) {
                    pst.setInt(1, (produto.getQuantidade() - item.getQuantidade()));
                } else if (item.getItem_Status().equals("CANCELADO")) {
                    pst.setInt(1, (produto.getQuantidade() + item.getQuantidade()));
                }
                pst.setInt(2, produto.getId());
                pst.executeUpdate();

            } else {//Se alteração das caracteristicas do produto
                produto = (Produto) entidade;
                sql = new StringBuilder();
                sql.append("UPDATE " + table + " SET " + nome + "=?, " + marca + "=?, " + modelo + "=?, ");
                sql.append(qtde_estoque + "=?, " + valor_unit + "=?, " + estoque_min + "=?, " + qtde_max_venda + "=?, ");
                sql.append(descricao + "=?, " + flg_ativo + "=?, " + cat_id + "=? ");
                sql.append("WHERE " + idTable + "=?");

                pst = connection.prepareStatement(sql.toString());
                pst.setString(1, produto.getNome().toUpperCase());
                pst.setString(2, produto.getMarca().toUpperCase());
                pst.setString(3, produto.getModelo().toUpperCase());
                pst.setInt(4, produto.getQuantidade());
                pst.setDouble(5, produto.getPrecoUnit());
                pst.setInt(6, produto.getEstoqueMin());
                pst.setInt(7, produto.getQtdeMaxVenda());
                pst.setString(8, produto.getDescricao().toUpperCase());
                pst.setBoolean(9, produto.getFlg_ativo());
                pst.setInt(10, produto.getCategoria().getId());
                pst.setInt(11, produto.getId());

                pst.executeUpdate();

                ImagemDAO imgDAO = new ImagemDAO();
                imgDAO.alterar(produto, connection);

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

    /**
     * TODO Descri��o do M�todo
     *
     * @param entidade
     * @return
     * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
     */
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        PreparedStatement pst = null;

        Produto produto = (Produto) entidade;
        String sql = null;

        if (produto.getNome() == null) {
            produto.setNome("");
        }
        

        if (produto.getId() == null && produto.getNome().equals("") && produto.getCategoria().getId() == null) {
            sql = "SELECT * FROM " + table + " JOIN " + tbCategoria + " USING(cat_id) order by pro_nome";
        } else if (produto.getCategoria().getId() != null) {
            sql = "SELECT * FROM " + table + " JOIN " + tbCategoria + " USING(cat_id) WHERE " + cat_id + "=?";
        } else if (produto.getId() != null && produto.getNome().equals("")) {
            sql = "SELECT * FROM " + table + " JOIN " + tbCategoria + " USING(cat_id) WHERE " + idTable + "=?";
        } else if (produto.getId() == null && !produto.getNome().equals("")) {
            sql = "SELECT * FROM " + table + " JOIN " + tbCategoria + " USING(cat_id) WHERE " + nome + " like ?";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (produto.getId() != null && produto.getNome().equals("")) {
                pst.setInt(1, produto.getId());
            } else if (produto.getId() == null && !produto.getNome().equals("")) {
                pst.setString(1, "%" + produto.getNome().toUpperCase() + "%");
            } else if (produto.getCategoria().getId() != null) {
                pst.setInt(1, produto.getCategoria().getId());
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt(idTable));
                p.setNome(rs.getString(nome));
                p.setMarca(rs.getString(marca));
                p.setModelo(rs.getString(modelo));
                p.setQuantidade(rs.getInt(qtde_estoque));
                p.setPrecoUnit(rs.getDouble(valor_unit));
                p.setEstoqueMin(rs.getInt(estoque_min));
                p.setQtdeMaxVenda(rs.getInt(qtde_max_venda));
                p.setDescricao(rs.getString(descricao));
                p.setFlg_ativo(rs.getBoolean(flg_ativo));

                java.sql.Date dtCadastroEmLong = rs.getDate(dtCadastro);
                Date dtCadastro = new Date(dtCadastroEmLong.getTime());
                p.setDtCadastro(dtCadastro);
                Categorias cat = new Categorias();
                cat.setId(rs.getInt(cat_id));
                cat.setNomeCategoria(rs.getString(cat_nome));
                p.setCategoria(cat);

                ImagemDAO imgDao = new ImagemDAO();
                List<EntidadeDominio> entidades = imgDao.consultar(p, connection);
                Imagem[] img = new Imagem[entidades.size()];
                for (int i = 0; i < entidades.size(); i++) {
                    img[i] = (Imagem) entidades.get(i);
                }

                p.setFoto(img);

                produtos.add(p);
            }

            return produtos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO Descri��o do M�todo
     *
     * @param entidade
     * @return
     * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
     */
    public Produto consultar(int id) {
        PreparedStatement pst = null;

        String sql = null;

        sql = "SELECT * FROM tb_produtos WHERE " + idTable + "=?";

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            Produto p = new Produto();
            while (rs.next()) {

                p.setId(rs.getInt(idTable));
                p.setNome(rs.getString(nome));
                p.setMarca(rs.getString(marca));
                p.setModelo(rs.getString(modelo));
                p.setQuantidade(rs.getInt(qtde_estoque));
                p.setPrecoUnit(rs.getDouble(valor_unit));
                p.setEstoqueMin(rs.getInt(estoque_min));
                p.setQtdeMaxVenda(rs.getInt(qtde_max_venda));
                p.setDescricao(rs.getString(descricao));
                p.setFlg_ativo(rs.getBoolean(flg_ativo));

                java.sql.Date dtCadastroEmLong = rs.getDate(dtCadastro);
                Date dtCadastro = new Date(dtCadastroEmLong.getTime());
                p.setDtCadastro(dtCadastro);

            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
