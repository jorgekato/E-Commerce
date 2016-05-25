<%-- 
    Document   : produtoperiodo
    Created on : 22/05/2016, 08:51:18
    Author     : Henrique
--%>

<%@page import="e_commer.dominio.Produto"%>
<%@page import="java.util.HashMap"%>
<%@page import="e_commer.filtroAnalise.FiltroEstoqueMinimo"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroProdutoQtdePeriodo"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<div class="content">
    <div class="account-in">
        <div class="box">

            <div class="box-chart">
                <h2>Produtos Qtde Por Periodo</h2>

                <%                    Resultado grafico = (Resultado) request.getAttribute("grafico");
                    Resultado produtos = (Resultado) request.getAttribute("produtos");
                    if (grafico != null && grafico.getMsg() != null) {
                        out.print(grafico.getMsg());
                    }

                %>

                <div class=" account-top register">
                    <form action="${pageContext.request.contextPath}/ServletGrafico3" method="POST">

                            
                        <select name="txtId">
                            <%                                if (produtos != null) {
                                List<EntidadeDominio> pro = produtos.getEntidades();
                                for(int i = 0; i < pro.size(); i++){
                                    Produto p = (Produto)pro.get(i);
                            %>
                            <option value="<%= p.getId()%>"><%= p.getNome()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                        <input type="submit" value="grafico2" name="operacao" /></p>
                    </form>
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
                        labels: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
                        datasets: [
                            {
                                label: "Produto: <%= ((FiltroProdutoQtdePeriodo) grafico.getEntidades().get(0)).getNome()%>.",
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
                                                FiltroProdutoQtdePeriodo f = (FiltroProdutoQtdePeriodo) entidades.get(i);
                                                HashMap<String, Integer> hmQtde = f.getHmQtde();

                                                sbRegistro.append(hmQtde.get("Janeiro"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Fevereiro"));
                                                sbRegistro.append(",");
                                                sbRegistro.append(hmQtde.get("Março"));
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
                <%
                    }
                %>
            </div>
        </div>

    </div>	
</div>
<!---->