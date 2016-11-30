package e_commer.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import e_commer.core.IDAO;
import e_commer.core.IFachada;
import e_commer.core.IStrategy;
import e_commer.core.aplicacao.Resultado;
//import DAOS
import e_commer.core.impl.dao.ClienteDAO;
import e_commer.core.impl.dao.FornecedorDAO;
import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.core.impl.dao.CategoriasDAO;
import e_commer.core.impl.dao.ArtesanatoDAO;
import e_commer.core.impl.dao.CreditoDAO;
import e_commer.core.impl.dao.EnderecoDAO;
import e_commer.core.impl.dao.FiltroClienteVendaPeriodoDAO;
import e_commer.core.impl.dao.FiltroEstoqueMinimoDAO;
import e_commer.core.impl.dao.FiltroEstoqueGeralDAO;
import e_commer.core.impl.dao.FiltroPerfilIdadeSexoDAO;
import e_commer.core.impl.dao.FiltroProdutoQtdePeriodoDAO;
import e_commer.core.impl.dao.FiltroProdutoVendaPeriodoDAO;
import e_commer.core.impl.dao.PedidoDAO;
import e_commer.core.impl.dao.TrocaDevolucaoDAO;

import e_commer.core.impl.negocio.ComplementarDtCadastro;
import e_commer.core.impl.negocio.ComplementarDtValidadeCredito;
import e_commer.core.impl.negocio.ValidadorCnpj;
import e_commer.core.impl.negocio.ValidadorCpf;
import e_commer.core.impl.negocio.ValidadorDadosObrigatoriosFornecedor;
import e_commer.core.impl.negocio.ValidadorQtdProduto;
import e_commer.core.impl.negocio.ValidadorQtdeEstoque;
import e_commer.core.impl.negocio.ValidadorQtdeVendas;
import e_commer.core.impl.negocio.ValidadorCredito;

//import entidades domínio
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Fornecedor;
import e_commer.dominio.Produto;
import e_commer.dominio.Categorias;
import e_commer.dominio.Artesanato;
import e_commer.dominio.CarrinhoCompra;
import e_commer.dominio.Credito;
import e_commer.dominio.Endereco;
import e_commer.dominio.Pedido;
import e_commer.dominio.TrocaDevolucao;
import e_commer.filtroAnalise.FiltroClienteVendaPeriodo;
import e_commer.filtroAnalise.FiltroEstoqueMinimo;
import e_commer.filtroAnalise.FiltroEstoqueGeral;
import e_commer.filtroAnalise.FiltroPerfilIdadeSexo;
import e_commer.filtroAnalise.FiltroProdutoQtdePeriodo;
import e_commer.filtroAnalise.FiltroProdutoVendaPeriodo;

public class Fachada implements IFachada {

    /**
     * Mapa de DAOS, ser� indexado pelo nome da entidade O valor � uma inst�ncia
     * do DAO para uma dada entidade;
     */
    private Map<String, IDAO> daos;

    /**
     * Mapa para conter as regras de neg�cio de todas opera��es por entidade; O
     * valor � um mapa que de regras de neg�cio indexado pela opera��o
     */
    private Map<String, Map<String, List<IStrategy>>> rns;

    private Resultado resultado;

    public Fachada() {
        /* Intanciando o Map de DAOS */
        daos = new HashMap<String, IDAO>();
        /* Intanciando o Map de Regras de Negocio */
        rns = new HashMap<String, Map<String, List<IStrategy>>>();

        /* Criando inst�ncias dos DAOs a serem utilizados*/
        FornecedorDAO forDAO = new FornecedorDAO();
        ClienteDAO cliDAO = new ClienteDAO();
        ProdutoDAO proDAO = new ProdutoDAO();
        CategoriasDAO catDAO = new CategoriasDAO();
        ArtesanatoDAO artDAO = new ArtesanatoDAO();
        PedidoDAO pedDAO = new PedidoDAO();
        TrocaDevolucaoDAO tdDAO = new TrocaDevolucaoDAO();
        CreditoDAO creDAO = new CreditoDAO();
        FiltroClienteVendaPeriodoDAO filDAO = new FiltroClienteVendaPeriodoDAO();
        FiltroProdutoVendaPeriodoDAO fprDAO = new FiltroProdutoVendaPeriodoDAO();
        FiltroEstoqueMinimoDAO esmDAO = new FiltroEstoqueMinimoDAO();
        FiltroEstoqueGeralDAO estDAO = new FiltroEstoqueGeralDAO();
        FiltroProdutoQtdePeriodoDAO fqtDAO = new FiltroProdutoQtdePeriodoDAO();
        FiltroPerfilIdadeSexoDAO perDAO = new FiltroPerfilIdadeSexoDAO();
        EnderecoDAO endDAO = new EnderecoDAO();

        /* Adicionando cada dao no MAP indexando pelo nome da classe */
        daos.put(Fornecedor.class.getName(), forDAO);
        daos.put(Cliente.class.getName(), cliDAO);
        daos.put(Produto.class.getName(), proDAO);
        daos.put(Categorias.class.getName(), catDAO);
        daos.put(Artesanato.class.getName(), artDAO);
        daos.put(Pedido.class.getName(), pedDAO);
        daos.put(TrocaDevolucao.class.getName(), tdDAO);
        daos.put(Credito.class.getName(), creDAO);
        daos.put(FiltroClienteVendaPeriodo.class.getName(), filDAO);
        daos.put(FiltroProdutoVendaPeriodo.class.getName(), fprDAO);
        daos.put(FiltroEstoqueMinimo.class.getName(), esmDAO);
        daos.put(FiltroEstoqueGeral.class.getName(), estDAO);
        daos.put(FiltroProdutoQtdePeriodo.class.getName(), fqtDAO);
        daos.put(FiltroPerfilIdadeSexo.class.getName(), perDAO);
        daos.put(Endereco.class.getName(), endDAO);

        //-------------------------------------------------------------------------------------------
        /* Criando instancias de regras de negocio a serem utilizados*/
        ValidadorDadosObrigatoriosFornecedor vrDadosObrigatoriosFornecedor = new ValidadorDadosObrigatoriosFornecedor();
        ValidadorCnpj vCnpj = new ValidadorCnpj();
        ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
        ValidadorCpf vCpf = new ValidadorCpf();
        ValidadorQtdProduto vQtd = new ValidadorQtdProduto();
        ComplementarDtValidadeCredito cDtValidade = new ComplementarDtValidadeCredito();
        ValidadorQtdeEstoque vQtdeEst = new ValidadorQtdeEstoque();
        ValidadorQtdeVendas vQtdeVen = new ValidadorQtdeVendas();
        ValidadorCredito vCredito = new ValidadorCredito();

        //FORNECEDOR------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de neg�cio de fornencedor
		 * quando a opera��o for salvar
         */
        List<IStrategy> rnsSalvarFornecedor = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na opera��o salvar do fornecedor*/
        rnsSalvarFornecedor.add(vrDadosObrigatoriosFornecedor);
        rnsSalvarFornecedor.add(vCnpj);
        rnsSalvarFornecedor.add(cDtCadastro);

        /* Cria o mapa que poder� conter todas as listas de regras de neg�cio espec�fica 
		 * por opera��o  do fornecedor
         */
        Map<String, List<IStrategy>> rnsFornecedor = new HashMap<String, List<IStrategy>>();
        /*
		 * Adiciona a listra de regras na opera��o salvar no mapa do fornecedor (lista criada na linha 70)
         */
        rnsFornecedor.put("SALVAR", rnsSalvarFornecedor);

        /* Adiciona o mapa(criado na linha 79) com as regras indexadas pelas opera��es no mapa geral indexado 
		 * pelo nome da entidade
         */
        rns.put(Fornecedor.class.getName(), rnsFornecedor);

        //CLIENTE------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de neg�cio de cliente
	/* quando a opera��o for salvar
         */
        List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na opera��o salvar do cliente */
        rnsSalvarCliente.add(cDtCadastro);
        rnsSalvarCliente.add(vCpf);

        /* Cria o mapa que poder� conter todas as listas de regras de neg�cio espec�fica 
		 * por opera��o do cliente
         */
        Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
        /*
		 * Adiciona a listra de regras na opera��o salvar no mapa do cliente (lista criada na linha 93)
         */
        rnsCliente.put("SALVAR", rnsSalvarCliente);
        /* Adiciona o mapa(criado na linha 101) com as regras indexadas pelas opera��es no mapa geral indexado 
		 * pelo nome da entidade. Observe que este mapa (rns) � o mesmo utilizado na linha 88.
         */
        rns.put(Cliente.class.getName(), rnsCliente);

        //PRODUTO---------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de neg�cio de produto
		 * quando a opera��o for salvar
         */
        List<IStrategy> rnsSalvarProduto = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na opera��o salvar do produto */
        rnsSalvarProduto.add(cDtCadastro);
        rnsSalvarProduto.add(vQtd);

        /* Criando uma lista para conter as regras de neg�cio de produto
		 * quando a operacao for alterar
         */
        List<IStrategy> rnsAlterarProduto = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na opera��o alterar do produto */
        rnsAlterarProduto.add(vQtd);
        /* Criando uma lista para conter as regras de neg�cio de produto
		 * quando a operacao for alterar
         */
        List<IStrategy> rnsConsultarQtdeProduto = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na opera��o alterar do produto */
        rnsConsultarQtdeProduto.add(vQtd);

        /* Cria o mapa que podera conter todas as listas de regras de neg�cio espec�fica 
		 * por operacao do produto
         */
        Map<String, List<IStrategy>> rnsProduto = new HashMap<String, List<IStrategy>>();
        /*
		 * Adiciona a listra de regras na operacao salvar no mapa do produto (lista criada na linha 114)
         */
        rnsProduto.put("SALVAR", rnsSalvarProduto);
        /*
		 * Adiciona a listra de regras na opera��o alterar no mapa do produto (lista criada na linha 122)
         */
        rnsProduto.put("ALTERAR", rnsAlterarProduto);
        /*
		 * Adiciona a listra de regras na opera��o alterar no mapa do produto (lista criada na linha 122)
         */
        //rnsProduto.put("CONSULTAR", rnsAlterarProduto);

        /* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas opera��es no mapa geral indexado 
		 * pelo nome da entidade. Observe que este mapa (rns) � o mesmo utilizado na linha 88.
         */
        rns.put(Produto.class.getName(), rnsProduto);

        //CATEGORIA-------------------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de negócio de Categoria
         * quando a operação for salvar
         */
        List<IStrategy> rnsSalvarCategoria = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar da CATEGORIA */
        rnsSalvarCategoria.add(cDtCadastro);
        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
         * por operação do PRODUTO
         */
        Map<String, List<IStrategy>> rnsCategoria = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do produto (lista criada na linha 114)
         */
        rnsCategoria.put("SALVAR", rnsSalvarCategoria);

        /* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações no mapa geral indexado 
         * pelo nome da entidade. Observe que este mapa (rns) é o mesmo utilizado na linha 88.
         */
        rns.put(Categorias.class.getName(), rnsCategoria);

        //ARTESANATO-------------------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de negócio de Categoria
         * quando a operação for salvar
         */
        List<IStrategy> rnsSalvarArtesanato = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar da CATEGORIA */
        rnsSalvarArtesanato.add(cDtCadastro);
        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
         * por operação do PRODUTO
         */
        Map<String, List<IStrategy>> rnsArtesanato = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do produto (lista criada na linha 114)
         */
        rnsArtesanato.put("SALVAR", rnsSalvarArtesanato);

        /* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações no mapa geral indexado 
         * pelo nome da entidade. Observe que este mapa (rns) é o mesmo utilizado na linha 88.
         */
        rns.put(Artesanato.class.getName(), rnsArtesanato);

        //PEDIDO-------------------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de negócio de Pedidos
         * quando a operação for salvar
         */
        List<IStrategy> rnsSalvarPedido = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar da CATEGORIA */
        rnsSalvarPedido.add(cDtCadastro);
        rnsSalvarPedido.add(vQtdeEst);
        rnsSalvarPedido.add(vQtdeVen);
        rnsSalvarPedido.add(vCredito);
        List<IStrategy> rnsConsultarPedido = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação Consultar do Carrinho */
        rnsConsultarPedido.add(vQtdeEst);
        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
         * por operação do PRODUTO
         */
        Map<String, List<IStrategy>> rnsPedido = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do produto (lista criada na linha 114)
         */
        rnsPedido.put("SALVAR", rnsSalvarPedido);
        //rnsPedido.put("CONSULTAR", rnsConsultarPedido);

        /* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações no mapa geral indexado 
         * pelo nome da entidade. Observe que este mapa (rns) é o mesmo utilizado na linha 88.
         */
        rns.put(Pedido.class.getName(), rnsPedido);

        //TROCA E DEVOLUCAO-------------------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de negócio de TrocaDevolucao
         * quando a operação for salvar
         */
        List<IStrategy> rnsSalvarTrocaDevolucao = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar da CATEGORIA */
        rnsSalvarTrocaDevolucao.add(cDtCadastro);
        /* Criando uma lista para conter as regras de neg�cio de troca e devolucao
		 * quando a opera��o for alterar
         */
        List<IStrategy> rnsAlterarTrocaDevolucao = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operacao alterar da troca e devolucao */
        rnsAlterarTrocaDevolucao.add(cDtCadastro);
        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
         * por operação da TrocaDevolucao
         */
        Map<String, List<IStrategy>> rnsTrocaDevolucao = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do produto 
         */
        rnsTrocaDevolucao.put("SALVAR", rnsSalvarTrocaDevolucao);
        /*
         * Adiciona a listra de regras na operação alterar no mapa do produto 
         */
        rnsTrocaDevolucao.put("ALTERAR", rnsAlterarTrocaDevolucao);

        /* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações no mapa geral indexado 
         * pelo nome da entidade.
         */
        rns.put(TrocaDevolucao.class.getName(), rnsTrocaDevolucao);
        //Credito-------------------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de negócio de Credito
         * quando a operação for salvar
         */
        List<IStrategy> rnsSalvarCredito = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar da CATEGORIA */
        rnsSalvarCredito.add(cDtCadastro);
        rnsSalvarCredito.add(cDtValidade);
        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
         * por operação de Credito
         */
        Map<String, List<IStrategy>> rnsCredito = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do produto 
         */
        rnsCredito.put("SALVAR", rnsSalvarCredito);

        /* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações no mapa geral indexado 
         * pelo nome da entidade.
         */
        rns.put(Credito.class.getName(), rnsCredito);

        //Carrinho-------------------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de negócio de Credito
         * quando a operação for salvar
         */
        List<IStrategy> rnsConsultarCarrinho = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar da CATEGORIA */
        rnsConsultarCarrinho.add(vQtdeEst);
        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica 
         * por operação de Credito
         */
        Map<String, List<IStrategy>> rnsCarrinho = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do produto 
         */
        rnsCarrinho.put("CONSULTAR", rnsConsultarCarrinho);

        /* Adiciona o mapa(criado na linha 129) com as regras indexadas pelas operações no mapa geral indexado 
         * pelo nome da entidade.
         */
        rns.put(CarrinhoCompra.class.getName(), rnsCarrinho);
        
        //ENDERECO-------------------------------------------------------------------------------------------------------------------
        /* Criando uma lista para conter as regras de negócio de Endereco
         * quando a operação for salvar
         */
        List<IStrategy> rnsSalvarEndereco = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar do Endereco */
        rnsSalvarEndereco.add(cDtCadastro);
               
        Map<String, List<IStrategy>> rnsEndereco = new HashMap<String, List<IStrategy>>();
        /*
         * Adiciona a listra de regras na operação salvar no mapa do endereco 
         */
        rnsEndereco.put("SALVAR", rnsSalvarEndereco);

    }

    @Override
    public Resultado salvar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        //executa as regras criadas na fachada e add a data caso for uma regra
        String msg = executarRegras(entidade, "SALVAR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.salvar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch (SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar o registro!");

            }
        } else {
            resultado.setMsg(msg);

        }

        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "ALTERAR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.alterar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch (SQLException e) {
                e.printStackTrace();
                resultado.setMsg("N�o foi poss�vel realizar o registro!");

            }
        } else {
            resultado.setMsg(msg);

        }

        return resultado;

    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "EXCLUIR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.excluir(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch (SQLException e) {
                e.printStackTrace();
                resultado.setMsg("N�o foi poss�vel realizar o registro!");

            }
        } else {
            resultado.setMsg(msg);

        }

        return resultado;

    }

    @Override
    public Resultado consultar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();
        String msg = null;

        msg = executarRegras(entidade, "CONSULTAR");//TAVA "EXCLUIR"

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                if (dao != null) {
                    resultado.setEntidades(dao.consultar(entidade));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a consulta!");
            }
        } else {
            resultado.setMsg(msg);
        }
        return resultado;

    }

    @Override
    public Resultado visualizar(EntidadeDominio entidade) {
        resultado = new Resultado();
        resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
        resultado.getEntidades().add(entidade);
        return resultado;

    }

    private String executarRegras(EntidadeDominio entidade, String operacao) {
        String nmClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);

        if (regrasOperacao != null) {
            List<IStrategy> regras = regrasOperacao.get(operacao);

            if (regras != null) {
                for (IStrategy s : regras) {
                    //add a data
                    String m = s.processar(entidade);

                    if (m != null) {
                        msg.append(m);
                        msg.append("\n");
                    }
                }
            }

        }

        if (msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }
}
