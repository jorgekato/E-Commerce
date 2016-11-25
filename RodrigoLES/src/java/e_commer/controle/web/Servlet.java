package e_commer.controle.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import e_commer.controle.web.command.ICommand;
import e_commer.controle.web.command.impl.AlterarCommand;
import e_commer.controle.web.command.impl.ConsultarCommand;
import e_commer.controle.web.command.impl.ExcluirCommand;
import e_commer.controle.web.command.impl.SalvarCommand;
import e_commer.controle.web.command.impl.VisualizarCommand;
import e_commer.controle.web.vh.IViewHelper;
import e_commer.controle.web.vh.impl.ArtesanatoViewHelper;
import e_commer.controle.web.vh.impl.ClienteViewHelper;
import e_commer.controle.web.vh.impl.FornecedorViewHelper;
import e_commer.controle.web.vh.impl.ProdutoViewHelper;
import e_commer.controle.web.vh.impl.CategoriaViewHelper;
import e_commer.controle.web.vh.impl.CarrinhoViewHelper;
import e_commer.controle.web.vh.impl.ConsulProdArtViewHelper;
import e_commer.controle.web.vh.impl.CreditoViewHelper;
import e_commer.controle.web.vh.impl.EnderecoViewHelper;
import e_commer.controle.web.vh.impl.FiltroClienteVendaPeriodoViewHelper;
import e_commer.controle.web.vh.impl.FiltroProdutoVendaPeriodoViewHelper;
import e_commer.controle.web.vh.impl.FiltroEstoqueMinimoViewHelper;
import e_commer.controle.web.vh.impl.FiltroEstoqueGeralViewHelper;
import e_commer.controle.web.vh.impl.FiltroPerfilIdadeSexoViewHelper;
import e_commer.controle.web.vh.impl.FiltroProdutoQtdePeriodoViewHelper;
import e_commer.controle.web.vh.impl.PedidoViewHelper;
import e_commer.controle.web.vh.impl.TrocaDevolucaoViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.EntidadeDominio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;

/**
 * Servlet implementation class Servlet
 */
//Para tratar as imagens dos produtos
@MultipartConfig
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Map<String, ICommand> commands;
    private static Map<String, IViewHelper> vhs;

    /**
     * Default constructor.
     */
    public Servlet() {

        /* Utilizando o command para chamar a fachada e indexando cada command 
    	 * pela operação garantimos que esta servelt atenderá qualquer operação */
        commands = new HashMap<String, ICommand>();

        commands.put("SALVAR", new SalvarCommand());
        commands.put("EXCLUIR", new ExcluirCommand());
        commands.put("CONSULTAR", new ConsultarCommand());
        //add para teste
        commands.put("CONSULTAR1", new ConsultarCommand());
        commands.put("VISUALIZAR1", new VisualizarCommand());
        commands.put("VISUALIZAR", new VisualizarCommand());
        commands.put("ALTERAR", new AlterarCommand());
        commands.put("ALTERAR1", new AlterarCommand());
        commands.put("CONSULTAR2", new ConsultarCommand());
        commands.put("ENVIAR", new SalvarCommand());
        commands.put("HISTORICO", new ConsultarCommand());
        commands.put("grafico1", new ConsultarCommand());
        commands.put("grafico2", new ConsultarCommand());
        commands.put("grafico4", new ConsultarCommand());
        commands.put("graficoPerfil", new ConsultarCommand());
        
        
        
        
        // commands.put("ATUALIZAR", new AlterarCommand());

        /* Utilizando o ViewHelper para tratar especificações de qualquer tela e indexando 
    	 * cada viewhelper pela url em que esta servlet é chamada no form
    	 * garantimos que esta servelt atenderá qualquer entidade */
        vhs = new HashMap<String, IViewHelper>();
        /*A chave do mapa é o mapeamento da servlet para cada form que 
    	 * está configurado no web.xml e sendo utilizada no action do html
         */
        vhs.put("/Artesanatos/SalvarFornecedor", new FornecedorViewHelper());
        vhs.put("/Artesanatos/SalvarCliente", new ClienteViewHelper());
        vhs.put("/Artesanatos/SalvarProduto", new ProdutoViewHelper());
        vhs.put("/Artesanatos/SalvarCategoria", new CategoriaViewHelper());
        vhs.put("/Artesanatos/SalvarArtesanato", new ArtesanatoViewHelper());
        vhs.put("/Artesanatos/SalvarCarrinho", new CarrinhoViewHelper());
        vhs.put("/Artesanatos/SalvarPedidos", new PedidoViewHelper());
        vhs.put("/Artesanatos/SalvarTrocaDevolucao", new TrocaDevolucaoViewHelper());
        vhs.put("/Artesanatos/SalvarCredito", new CreditoViewHelper());
        vhs.put("/Artesanatos/ServletGrafico1", new FiltroClienteVendaPeriodoViewHelper());
        vhs.put("/Artesanatos/ServletGrafico2", new FiltroProdutoVendaPeriodoViewHelper());
        vhs.put("/Artesanatos/EstoqueMin", new FiltroEstoqueMinimoViewHelper());
        vhs.put("/Artesanatos/EstoqueGeral", new FiltroEstoqueGeralViewHelper());
        vhs.put("/Artesanatos/ServletGrafico3", new FiltroProdutoQtdePeriodoViewHelper());
        vhs.put("/Artesanatos/ServletGrafico4", new FiltroClienteVendaPeriodoViewHelper());
        vhs.put("/Artesanatos/SalvarEndereco", new EnderecoViewHelper());
        vhs.put("/Artesanatos/CONSULPRODART", new ConsulProdArtViewHelper());
        vhs.put("/Artesanatos/ServletGraficoPerfil", new FiltroPerfilIdadeSexoViewHelper());
        
        
    }

    /**
     * TODO Descricao do Metodo
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see
     * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doProcessRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doProcessRequest(request, response);
    }

    protected void doProcessRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Obt�m a uri que invocou esta servlet (O que foi definido no methdo do form html)
        String uri = request.getRequestURI();

        //Obt�m a opera��o executada
        String operacao = request.getParameter("operacao");

        //Obt�m um viewhelper indexado pela uri que invocou esta servlet
        IViewHelper vh = vhs.get(uri);

        //O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
        //Aqui é onde seta os atributos que vem do front end, menos a data
        EntidadeDominio entidade = vh.getEntidade(request);

        //Obt�m o command para executar a respectiva opera��o
        ICommand command = commands.get(operacao);

        /*Executa o command que chamar� a fachada para executar a opera��o requisitada
		 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro 
		 * ou entidades de retorno
         */
        Resultado resultado = null;

        if (command != null) {
            resultado = command.execute(entidade);
        } else {
            //Usado para o carrinho de compras
            resultado = new Resultado();
            if (entidade != null) {
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } else {
                resultado.setEntidades(null);
            }

        }
        /*
		 * Executa o m�todo setView do view helper espec�fico para definir como dever� ser apresentado 
		 * o resultado para o usu�rio
         */
        vh.setView(resultado, request, response);

    }
}
