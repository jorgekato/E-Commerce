<%-- 
    Document   : relatperfil
    Created on : 19/06/2016, 13:26:18
    Author     : Henrique
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroPerfilIdadeSexo"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<div class="content">
    <div class="account-in">
        <div class="box">

            <div class="box-chart">
                <h2>Perfil por Idade e Sexo</h2>

                <%                    Resultado grafico = (Resultado) request.getAttribute("grafico");
                    if (grafico != null && grafico.getMsg() != null) {
                        out.print(grafico.getMsg());
                    }

                %>

                <div class=" account-top register">
                    <form action="${pageContext.request.contextPath}/ServletGraficoPerfil" method="POST">
                        <p>Data Inicial: <input type="text" name="txtDataInicial" id="data" f /></p>
                        <p>Data Final: <input type="text" name="txtDataFinal" id="data1" />
                            <input type="submit" value="graficoPerfil" name="operacao" /></p>
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
                        labels: ["Ate 19 anos", "De 20 à 39 anos", "De 40 à 59 anos", "Acima de 60 anos"],
                        datasets: [
                            {
                                label: "Masculino.",
                                fillColor: "rgba(151,187,205,0.2)",
                                strokeColor: "rgba(151,187,205,1)",
                                pointColor: "rgba(151,187,205,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(151,187,205,1)",
                                data: [<%
                                    if (grafico != null) {
                                        List<EntidadeDominio> entidades = grafico.getEntidades();
                                        StringBuilder sbRegistro = new StringBuilder();

                                        if (entidades != null) {

                                            FiltroPerfilIdadeSexo f = (FiltroPerfilIdadeSexo) entidades.get(0);
                                            List<Integer> mas = f.getMasculino();

                                            sbRegistro.append(mas.get(0));
                                            sbRegistro.append(",");
                                            sbRegistro.append(mas.get(1));
                                            sbRegistro.append(",");
                                            sbRegistro.append(mas.get(2));
                                            sbRegistro.append(",");
                                            sbRegistro.append(mas.get(3));
                                            sbRegistro.append(" ");

                                            out.print(sbRegistro.toString());
                                        }
                                    }
                    %>]

                            }
                            , {
                                label: "Feminino.",
                                fillColor: "rgba(220,0,50,0.2)",
                                strokeColor: "rgba(220,0,50,1)",
                                pointColor: "rgba(220,0,50,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(220,0,50,1)",
                                data: [<%
                                    if (grafico != null) {
                                        List<EntidadeDominio> entidades = grafico.getEntidades();
                                        StringBuilder sbRegistro = new StringBuilder();

                                        if (entidades != null) {

                                            FiltroPerfilIdadeSexo f = (FiltroPerfilIdadeSexo) entidades.get(0);
                                            List<Integer> fem = f.getFeminino();

                                            sbRegistro.append(fem.get(0));
                                            sbRegistro.append(",");
                                            sbRegistro.append(fem.get(1));
                                            sbRegistro.append(",");
                                            sbRegistro.append(fem.get(2));
                                            sbRegistro.append(",");
                                            sbRegistro.append(fem.get(3));
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
                <%                    }
                %>
            </div>
        </div>

    </div>	
</div>
<!---->