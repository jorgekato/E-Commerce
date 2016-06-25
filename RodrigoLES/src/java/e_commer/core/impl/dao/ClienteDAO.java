package e_commer.core.impl.dao;

import e_commer.dominio.Cidade;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import e_commer.dominio.Cliente;
import e_commer.dominio.Cliente.Nivel;
import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Estado;
import e_commer.dominio.Login;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class ClienteDAO extends AbstractJdbcDAO {

    private final String nome = "cli_nome";
    private final String email = "cli_email";
    private final String cpf = "cli_cpf";
    private final String sexo = "cli_sexo";
    private final String telefone = "cli_telefone";
    private final String flg_ativo = "cli_flg_ativo";
    private final String nivel_acesso = "cli_nivel";
    private final String dt_nascimento = "cli_dt_nascimento";
    private final String dt_cadastro = "cli_dt_cadastro";
    private final String tableLoguin = "tb_logins";
    private final String senha = "log_senha";
    private final String ultimo_acesso = "log_dt_ultimo_acesso";
    private final String tableEndereco = "tb_enderecos";
    private final String cidade = "end_cidade";
    private final String estado = "end_estado";
    private final String bairro = "end_bairro";
    private final String logradouro = "end_logradouro";
    private final String numero = "end_numero";
    private final String complemento = "end_complemento";
    private final String cep = "end_cep";
    private final String flgAtivo = "end_flg_ativo";

    public ClienteDAO() {
        super("tb_clientes", "cli_id");
    }

    public void salvar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Cliente cliente = (Cliente) entidade;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            
            if (cliente.getId() == null) {//novo cliente
                
                
                sql.append("INSERT INTO " + table + " (");
                sql.append(nome + ", " + sexo + ", " + email + ", " + cpf + ", " + flg_ativo + ", ");
                sql.append(dt_nascimento + ", " + dt_cadastro + ", " + nivel_acesso + ", "+ telefone + ") ");
                sql.append("VALUES (?,?,?,?,?,?,?,?,?)");

                pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

                pst.setString(1, cliente.getNome().toUpperCase());
                pst.setString(2, cliente.getSexo());
                pst.setString(3, cliente.getEmail());
                pst.setString(4, cliente.getCpf());
                pst.setBoolean(5, cliente.getFlg_ativo());
                pst.setDate(6, new java.sql.Date(cliente.getDtNascimento().getTime()));
                Timestamp time = new Timestamp(cliente.getDtCadastro().getTime());
                pst.setTimestamp(7, time);

                if (cliente.getNivel() == Cliente.Nivel.ADMINISTRADOR) {
                    pst.setString(8, "ADMINISTRADOR");
                } else if (cliente.getNivel() == Cliente.Nivel.COLABORADOR) {
                    pst.setString(8, "COLABORADOR");
                } else if (cliente.getNivel() == Cliente.Nivel.CLIENTE) {
                    pst.setString(8, "CLIENTE");
                }
                pst.setString(9, cliente.getTelefone());

                pst.executeUpdate();

                ResultSet rs = pst.getGeneratedKeys();
                int id = 0;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                cliente.setId(id);

                sql = new StringBuilder();
                sql.append("INSERT INTO ");
                sql.append(tableLoguin);
                sql.append(" (");
                sql.append(senha);
                sql.append(", ");
                sql.append(idTable);
                sql.append(", ");
                sql.append(ultimo_acesso);
                sql.append(") ");
                sql.append("VALUES (?,?,?)");

                pst = connection.prepareStatement(sql.toString());
                pst.setString(1, cliente.getLogin().getPassword());
                pst.setInt(2, cliente.getId());
                time = new Timestamp(cliente.getDtCadastro().getTime());
                pst.setTimestamp(3, time);

                pst.executeUpdate();

                sql = new StringBuilder();
                sql.append("INSERT INTO ");
                sql.append(tableEndereco);
                sql.append(" (");
                sql.append(idTable);
                sql.append(", ");
                sql.append(cidade);
                sql.append(", ");
                sql.append(estado);
                sql.append(", ");
                sql.append(bairro);
                sql.append(", ");
                sql.append(logradouro);
                sql.append(", ");
                sql.append(complemento);
                sql.append(", ");
                sql.append(numero);
                sql.append(", ");
                sql.append(cep);
                sql.append(", ");
                sql.append(flgAtivo);
                sql.append(") ");
                sql.append("VALUES (?,?,?,?,?,?,?,?,?)");

                pst = connection.prepareStatement(sql.toString());
                pst.setInt(1, cliente.getId());
                List<EntidadeDominio> endereco = cliente.getEndereco();
                pst.setString(2, ((Endereco)endereco.get(0)).getCidade().getNome().toUpperCase());
                pst.setString(3, ((Endereco)endereco.get(0)).getCidade().getEstado().getNome().toUpperCase());
                pst.setString(4, ((Endereco)endereco.get(0)).getBairro().toUpperCase());
                pst.setString(5, ((Endereco)endereco.get(0)).getLogradouro().toUpperCase());
                pst.setString(6, ((Endereco)endereco.get(0)).getComplemento().toUpperCase());
                pst.setString(7, ((Endereco)endereco.get(0)).getNumero());
                pst.setString(8, ((Endereco)endereco.get(0)).getCep());
                pst.setBoolean(9, true);
                pst.executeUpdate();
            
            } else { //adicionando outro endereco

                sql = new StringBuilder();
                sql.append("INSERT INTO ");
                sql.append(tableEndereco);
                sql.append(" (");
                sql.append(idTable);
                sql.append(", ");
                sql.append(cidade);
                sql.append(", ");
                sql.append(estado);
                sql.append(", ");
                sql.append(bairro);
                sql.append(", ");
                sql.append(logradouro);
                sql.append(", ");
                sql.append(complemento);
                sql.append(", ");
                sql.append(numero);
                sql.append(", ");
                sql.append(cep);
                sql.append(", ");
                sql.append(flgAtivo);
                sql.append(") ");
                sql.append("VALUES (?,?,?,?,?,?,?,?,?)");

                pst = connection.prepareStatement(sql.toString());
                pst.setInt(1, cliente.getId());
                List<EntidadeDominio> endereco = cliente.getEndereco();
                //int ultimoEndereco = endereco.lastIndexOf(endereco);
                int ultimoEndereco = endereco.size()-1;
                pst.setString(2, ((Endereco)endereco.get(ultimoEndereco)).getCidade().getNome().toUpperCase());
                pst.setString(3, ((Endereco)endereco.get(ultimoEndereco)).getCidade().getEstado().getNome().toUpperCase());
                pst.setString(4, ((Endereco)endereco.get(ultimoEndereco)).getBairro().toUpperCase());
                pst.setString(5, ((Endereco)endereco.get(ultimoEndereco)).getLogradouro().toUpperCase());
                pst.setString(6, ((Endereco)endereco.get(ultimoEndereco)).getComplemento().toUpperCase());
                pst.setString(7, ((Endereco)endereco.get(ultimoEndereco)).getNumero());
                pst.setString(8, ((Endereco)endereco.get(ultimoEndereco)).getCep());
                pst.setBoolean(9, ((Endereco)endereco.get(0)).getFlgAtivo());
//            time = new Timestamp(cliente.getDtCadastro().getTime());
//            pst.setTimestamp(9, time);
                pst.executeUpdate();
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
     * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
     */
    @Override
    public void alterar(EntidadeDominio entidade) {
        openConnection();
        PreparedStatement pst = null;
        Cliente cliente = (Cliente) entidade;

        try {
            connection.setAutoCommit(false);    //para não fazer o commit no banco de dados automaticamente
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ");
            sql.append(table);
            sql.append(" SET ");
            sql.append(nome);
            sql.append("=?, ");
            sql.append(telefone);
            sql.append("=?, ");
            sql.append(flg_ativo);
            sql.append("=?, ");
            sql.append(email);
            sql.append("=? ");
            sql.append("WHERE ");
            sql.append(idTable);
            sql.append("=?");

            pst = connection.prepareStatement(sql.toString());
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getTelefone());
            pst.setBoolean(3, cliente.getFlg_ativo());
            pst.setString(4, cliente.getEmail());
            pst.setInt(5, cliente.getId());
            pst.executeUpdate();    //executa a alteração dos dados no banco de dados
            
            connection.commit();    //salva a alteração no banco de dados
        } catch (SQLException erro) {
            try {
                connection.rollback();
            } catch (SQLException erro1) {
                erro1.printStackTrace();
            }
            erro.printStackTrace();
        } finally {
            try {
                pst.close();
                connection.close();
            } catch (SQLException erro) {
                erro.printStackTrace();
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
        String sql = null;

        if (cliente.getNome() == null) {
            cliente.setNome("");
        }
        if (cliente.getEmail() == null) {
            cliente.setEmail("");
        }

//        if (cliente.getId() == null && cliente.getNome().equals("")) {
//            sql = "SELECT * FROM clientes join login using(cli_id)";
//        } else if (cliente.getId() != null && cliente.getNome().equals("")) {
//            sql = "SELECT * FROM clientes WHERE cli_id =?";
//        } else if (cliente.getId() == null && !cliente.getNome().equals("")) {
//            sql = "SELECT * FROM clientes WHERE cli_nome like ?";
//        }
        if (cliente.getId() == null && cliente.getNome().equals("") && cliente.getEmail().equals("")) {
            sql = "SELECT * FROM " + table + " join " + tableLoguin + " using(" + idTable + ") order by cli_nome";
        } else if (cliente.getId() == null && cliente.getNome().equals("") && !cliente.getEmail().equals("")) {
            sql = "SELECT * FROM " + table + " join " + tableLoguin + " using(" + idTable + ") where " + email + "=?";
        } else if (cliente.getId() != null && cliente.getNome().equals("")) {
            sql = "SELECT * FROM " + table + " join " + tableLoguin + " using(" + idTable + ") WHERE " + idTable + "=?";
        } else if (cliente.getId() == null && !cliente.getNome().equals("")) {
            sql = "SELECT * FROM " + table + " join " + tableLoguin + " using(" + idTable + ")  WHERE " + nome + " like ?";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (cliente.getId() == null && cliente.getNome().equals("") && !cliente.getEmail().equals("")) {
                pst.setString(1, cliente.getEmail());
            } else if (cliente.getId() != null && cliente.getNome().equals("")) {
                pst.setInt(1, cliente.getId());
            } else if (cliente.getId() == null && !cliente.getNome().equals("")) {
                pst.setString(1, (cliente.getNome() + "%").toUpperCase());
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                Cliente cli = new Cliente();
                Login log = new Login();
//                Endereco end = new Endereco();
//                Cidade cid = new Cidade();
//                Estado estado = new Estado();

                cli.setId(rs.getInt(idTable));
                cli.setNome(rs.getString(nome));
                cli.setEmail(rs.getString(email));
                cli.setCpf(rs.getString(cpf));
                cli.setTelefone(rs.getString(telefone));
                String nivel = rs.getString(nivel_acesso);
                if (nivel.equals(Nivel.ADMINISTRADOR.toString())) {
                    cli.setNivel(Nivel.ADMINISTRADOR);
                } else if (nivel.equals(Nivel.COLABORADOR.toString())) {
                    cli.setNivel(Nivel.COLABORADOR);
                } else if (nivel.equals(Nivel.CLIENTE.toString())) {
                    cli.setNivel(Nivel.CLIENTE);
                }
                cli.setSexo(rs.getString(sexo));
                java.sql.Date date = rs.getDate(dt_nascimento);
                Date dtNasc = new Date(date.getTime());
                cli.setDtNascimento(dtNasc);

                java.sql.Date datCad = rs.getDate(dt_cadastro);
                Date dtCad = new Date(datCad.getTime());
                cli.setDtCadastro(dtCad);

                log.setPassword(rs.getString(senha));
                java.sql.Date dtCadastroEmLong = rs.getDate(ultimo_acesso);
                Date dtAcesso = new Date(dtCadastroEmLong.getTime());
                log.setUltimoAcesso(dtAcesso);
                cli.setLogin(log);
//                estado.setNome(rs.getString(this.estado));
//                cid.setEstado(estado);
//                cid.setNome(rs.getString(cidade));
//                end.setCidade(cid);
//                end.setLogradouro(rs.getString(logradouro));
//                end.setNumero(rs.getString(numero));
//                end.setCep(rs.getString(cep));
//                end.setBairro(rs.getString(bairro));
                EnderecoDAO endDAO = new EnderecoDAO();
                cli.setEndereco(endDAO.consultar(cli));
                //cli.addEndereco(end);
                cli.setFlg_ativo(rs.getBoolean(flg_ativo));
                clientes.add(cli);
            }
            return clientes;
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return null;
    }

}
