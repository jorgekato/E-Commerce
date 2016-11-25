<%-- 
    Document   : clientecompraperiodo
    Created on : 21/05/2016, 11:51:50
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroClienteVendaPeriodo"%>
<div class="content">
    <div class="account-in">
        <div class="box">

            <div class="box-chart">
                <h2>Cliente Mais Gastou Por Periodo</h2>
                <%                    Resultado grafico = (Resultado) request.getAttribute("grafico");

                    if (grafico != null && grafico.getMsg() != null) {
                        out.print(grafico.getMsg());
                    }
                    if (request.getAttribute("retorno") == null) {
                %>
                <div class=" account-top register">
                    <form action="${pageContext.request.contextPath}/ServletGrafico1" method="POST">
                        <p>Data Inicial: <input type="text" name="txtDataInicial" id="data" f /></p>
                        <p>Data Final: <input type="text" name="txtDataFinal" id="data1" />
                            <input type="submit" value="grafico2" name="operacao" /></p>
                    </form>
                </div>
                <br><br><br>

                <%
                } else if (grafico.getEntidades().size() > 0) {
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
                                        FiltroClienteVendaPeriodo f = (FiltroClienteVendaPeriodo) entidades.get(i);
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
                                label: "Período de : <%= ConverteDate.converteDateString(((FiltroClienteVendaPeriodo) grafico.getEntidades().get(0)).getDt_inicial())%> à <%= ConverteDate.converteDateString(((FiltroClienteVendaPeriodo) grafico.getEntidades().get(0)).getDt_final())%>. Em R$.",
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
                                                FiltroClienteVendaPeriodo f = (FiltroClienteVendaPeriodo) entidades.get(i);
                                                sbRegistro.append(f.getValor());

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
                        var LineChart = new Chart(ctx).Bar(data, options);
                        document.getElementById('js-legend').innerHTML = LineChart.generateLegend();
                    }
                </script>
                <div id="js-legend" class="chart-legend"> </div>
                <%                    } else {
                %>
                <h2>Cliente não possui compras no período selecionado.</h2>
                <%}%>
            </div>
        </div>

    </div>	


</div>
<!---->