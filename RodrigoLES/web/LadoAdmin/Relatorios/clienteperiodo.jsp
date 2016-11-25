<%-- 
    Document   : analisecomparativoclienteperiodo
    Created on : 19/05/2016, 16:12:52
    Author     : Henrique
--%>
<%@page import="e_commer.dominio.Cliente"%>
<%@page import="java.util.HashMap"%>
<%@page import="e_commer.filtroAnalise.FiltroEstoqueMinimo"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroClienteVendaPeriodo"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<div class="content">
    <div class="account-in">
        <div class="box">

            <div class="box-chart">
                <h2>Clientes Qtde Por Periodo</h2>

                <%                    Resultado grafico = (Resultado) request.getAttribute("grafico");
                    Resultado clientes = (Resultado) request.getAttribute("clientes");
                    if (grafico != null && grafico.getMsg() != null) {
                        out.print(grafico.getMsg());
                    }

                %>

                <div class=" account-top register">

                </div>
                <br><br><br>
                <%                    if (grafico != null) {
                %>
                <canvas id="GraficoLine" style="width:100%;"></canvas>
                <br><br>

                <script type="text/javascript">

                    var options = {
                        responsive: true
                    };
                    var data = {
                        labels: ["Janeiro", "Fevereiro", "Mar�o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
                        datasets: [
                            {
                                label: "Cliente: <%= ((FiltroClienteVendaPeriodo) grafico.getEntidades().get(0)).getNome()%>. Valores em R$.",
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

                                                FiltroClienteVendaPeriodo f = (FiltroClienteVendaPeriodo) entidades.get(0);
                                                HashMap<String, Integer> hmQtde = f.getHmQtde();

                                                sbRegistro.append(hmQtde.get("Janeiro"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Fevereiro"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Mar�o"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Abril"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Maio"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Junho"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Julho"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Agosto"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Setembro"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Outubro"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Novembro"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Dezembro"));
                                                sbRegistro.append(" ");

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
                <h2>Cliente n�o possui compras no per�odo selecionado.</h2>
                <%}%>
            </div>
        </div>

    </div>	
</div>
<!---->