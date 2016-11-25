/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.dao;

import e_commer.dominio.Categorias;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Imagem;
import e_commer.dominio.Produto;
import e_commer.filtroAnalise.FiltroEstoqueGeral;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrique Padovani
 */
public class FiltroEstoqueGeralDAO extends AbstractJdbcDAO{

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
    
    public FiltroEstoqueGeralDAO(String table, String idTable) {
        super(table, idTable);
    }

    public FiltroEstoqueGeralDAO() {
        super(null, null);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

        PreparedStatement pst = null;
        String sql = null;
        
        sql = "SELECT * FROM tb_produtos JOIN " + tbCategoria + " USING(cat_id) order by pro_nome";
        
        try {
            openConnection();
            pst = connection.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> estoqueGeral = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                FiltroEstoqueGeral estGeral = new FiltroEstoqueGeral();
                estGeral.setId(rs.getInt("pro_id"));
                estGeral.setNome(rs.getString(nome));
                estGeral.setMarca(rs.getString(marca));
                estGeral.setModelo(rs.getString(modelo));
                estGeral.setQuantidade(rs.getInt(qtde_estoque));
                estGeral.setPrecoUnit(rs.getDouble(valor_unit));
                estGeral.setEstoqueMin(rs.getInt(estoque_min));
                estGeral.setQtdeMaxVenda(rs.getInt(qtde_max_venda));
                estGeral.setDescricao(rs.getString(descricao));
                estGeral.setFlg_ativo(rs.getBoolean(flg_ativo));

                java.sql.Date dtCadastroEmLong = rs.getDate(dtCadastro);
                Date dtCadastro = new Date(dtCadastroEmLong.getTime());
                estGeral.setDtCadastro(dtCadastro);
                estGeral.setNomeCategoria(rs.getString(cat_nome));
                
                estoqueGeral.add(estGeral);
            }

            return estoqueGeral;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
