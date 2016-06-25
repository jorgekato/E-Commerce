package e_commer.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Artesanato;
import e_commer.dominio.Categorias;

/**
 *
 * @author Henrique
 */
public class ArtesanatoDAO extends AbstractJdbcDAO {

    private final String nome = "art_nome";
    private final String catId = "cat_id";
    private final String valor_unit = "art_valor_unit";
    private final String cores = "art_cores";
    private final String descricao = "art_descricao";
    private final String flg_ativo = "art_flg_ativo";
    private final String dt_cadastro = "art_dt_cadastro"; 
    private final String tbCategorias = "tb_categorias";
    private final String cat_nome = "cat_nome_categoria";
    
    public ArtesanatoDAO() {
        super("tb_artesanatos", "art_id");
    }

    public void salvar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Artesanato artesanato = (Artesanato) entidade;
        
        if(artesanato.getDescricao() == null){
            artesanato.setDescricao("");
        }
        try {
            connection.setAutoCommit(false);
            //Falta incluir categoria	
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(table);
            sql.append(" (" );
            sql.append(nome);
            sql.append(", ");
            sql.append(valor_unit);
            sql.append(", ");
            sql.append(descricao);
            sql.append(", ");
            sql.append(flg_ativo);
            sql.append(", ");
            sql.append(dt_cadastro);
            sql.append(", ");
            sql.append(catId);
            sql.append(") ");
            sql.append("VALUES (?,?,?,?,?,?)");

            pst = connection.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, artesanato.getNome().toUpperCase());
            pst.setDouble(2, artesanato.getPrecoUnit());
            pst.setString(3, artesanato.getDescricao().toUpperCase());
            pst.setBoolean(4, artesanato.getFlg_ativo());
            Timestamp time = new Timestamp(artesanato.getDtCadastro().getTime());
            pst.setTimestamp(5, time);
            pst.setInt(6, artesanato.getCategoria().getId());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            artesanato.setId(id);

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
        Artesanato artesanato = (Artesanato) entidade;

        try {
            connection.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(table);
            sql.append(" SET " );
            sql.append(nome);
            sql.append("=?, ");
            sql.append(valor_unit);
            sql.append("=?, ");
            sql.append(descricao);
            sql.append("=?, ");
            sql.append(flg_ativo);
            sql.append("=? ");
            sql.append("WHERE ");
            sql.append(idTable);
            sql.append("=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, artesanato.getNome());
            pst.setDouble(2, artesanato.getPrecoUnit());
            pst.setString(3, artesanato.getDescricao());
            pst.setBoolean(4, artesanato.getFlg_ativo());
            pst.setInt(5, artesanato.getId());
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

        Artesanato artesanato = (Artesanato) entidade;
        String sql = null;

        if (artesanato.getNome()== null) {
            artesanato.setNome("");
        }

        if (artesanato.getId() == null && artesanato.getNome().equals("")) {
            sql = "SELECT * FROM " + table + " JOIN " + tbCategorias + " USING(cat_id) order by art_nome";
        } else if (artesanato.getId() != null && artesanato.getNome().equals("")) {
            sql = "SELECT * FROM " + table +  " JOIN " + tbCategorias + " USING(cat_id) WHERE " + idTable + "=?";
        } else if (artesanato.getId() == null && !artesanato.getNome().equals("")) {
            sql = "SELECT * FROM " + table + " JOIN " + tbCategorias + " USING(cat_id) WHERE " + nome + " like ?";

        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (artesanato.getId() != null && artesanato.getNome().equals("")) {
                pst.setInt(1, artesanato.getId());
            } else if (artesanato.getId() == null && !artesanato.getNome().equals("")) {
                pst.setString(1, (artesanato.getNome()+ "%").toUpperCase());
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> artesanatos = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                Artesanato a = new Artesanato();
                a.setId(rs.getInt(idTable));
                a.setNome(rs.getString(nome));
                a.setPrecoUnit(rs.getDouble(valor_unit));                
                a.setDescricao(rs.getString(descricao));
                a.setFlg_ativo(rs.getBoolean(flg_ativo));
                java.sql.Date dtCadastroEmLong = rs.getDate(dt_cadastro);
                Date dtCadastro = new Date(dtCadastroEmLong.getTime());
                a.setDtCadastro(dtCadastro);
                Categorias cat = new Categorias();
                cat.setId(rs.getInt(catId));
                cat.setNomeCategoria(rs.getString(cat_nome));
                a.setCategoria(cat);
                artesanatos.add(a);
            }
            if(artesanatos.size()>0)
                return artesanatos;
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
