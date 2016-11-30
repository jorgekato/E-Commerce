<%-- 
    Document   : produtovendaperiodo
    Created on : 19/05/2016, 16:42:59
    Author     : Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroProdutoVendaPeriodo"%>
<div class="content">
    <div class="account-in">
        <div class="box">

            <div class="box-chart">
                <h2>Produtos Mais Vendidos Por Periodo</h2>
                <div class=" account-top register">
                    <form action="${pageContext.request.contextPath}/ServletGrafico2" method="POST">
                        <p>Data Inicial: <input type="text" name="txtDataInicial" id="data" /></p>
                        <p>Data Final: <input type="text" name="txtDataFinal" id="data1" />
                            <input type="submit" value="grafico2" name="operacao" /></p>
                    </form>
                </div>
                <br><br><br>

                <%                    Resultado grafico = (Resultado) request.getAttribute("grafico");
                    if (resultado != null && resultado.getMsg() != null) {
                        out.print(resultado.getMsg());
                    }
                    if (grafico != null) {
                %>
                <canvas id="GraficoLine" style="width:100%;"></canvas>

                <script type="text/javascript">

                    var options = {
                        responsive: true
                    };

                    var data = {
                        labels: [<%                            if (grafico != null) {
                                List<EntidadeDominio> entidades = grafico.getEntidades();
                                StringBuilder sbRegistro = new StringBuilder();

                                if (entidades != null) {
                                    for (int i = 0; i < entidades.size(); i++) {
                                        FiltroProdutoVendaPeriodo f = (FiltroProdutoVendaPeriodo) entidades.get(i);
                                        sbRegistro.append("\"");
                                        sbRegistro.append(f.getNome());
                                        sbRegistro.append("\"");
                                        if (i < entidades.size()) {
                                            sbRegistro.append(",");
                                        } else {
                                            sbRegistro.append(" ");
                                        }

                                    }
                                    out.print(sbRegistro.toString());
                                }
                            }
                    %>],
                        datasets: [
                            {
                                label: "Dados prim�rios",
                                fillColor: "rgba(220,220,220,0.2)",
                                strokeColor: "rgba(220,220,220,1)",
                                pointColor: "rgba(220,220,220,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(220,220,220,1)",
                                data: [<%
                                    if (grafico != null) {
                                        List<EntidadeDominio> entidades = grafico.getEntidades();
                                        StringBuilder sbRegistro = new StringBuilder();

                                        if (entidades != null) {
                                            for (int i = 0; i < entidades.size(); i++) {
                                                FiltroProdutoVendaPeriodo f = (FiltroProdutoVendaPeriodo) entidades.get(i);
                                                sbRegistro.append(f.getQtde());

                                                if (i < entidades.size()) {
                                                    sbRegistro.append(",");
                                                } else {
                                                    sbRegistro.append(" ");
                                                }

                                            }
                                            out.print(sbRegistro.toString());
                                        }
                                    }
                    %>]
                            }
                        ]
                    };

                    window.onload = function () {

                        var ctx = document.getElementById("GraficoLine").getContext("2d");
                        var LineChart = new Chart(ctx).Line(data, options);
                        document.getElementById('js-legend').innerHTML = LineChart.generateLegend();
                    }
                </script>
                <div id="js-legend" class="chart-legend"> </div>
                <%                    } else {
                %>
                <h2>Produto n�o possui vendas no per�odo selecionado.</h2>
                <%}%>
            </div>
        </div>

    </div>	


</div>
<!---->