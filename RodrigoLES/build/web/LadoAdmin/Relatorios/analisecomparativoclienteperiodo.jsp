<%-- 
    Document   : analisecomparativoclienteperiodo
    Created on : 19/05/2016, 16:12:52
    Author     : Henrique
--%>
<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroClienteVendaPeriodo"%>
<div class="content">
    <div class="account-in">
        <div class="box">

            <div class="box-chart">

                <form action="${pageContext.request.contextPath}/ServletGrafico1" method="POST">
                    <input type="text" id="txtId" name="txtDataInicial" ></input>
                    <p>Nome: <input type="text" name="txtDataFinal" ></p>
                    <input type="submit" value="grafico1" name="operacao" >
                </form>

                <canvas id="GraficoLine" style="width:100%;"></canvas>

                <%                    Resultado grafico = (Resultado) request.getAttribute("grafico");

                    if (resultado != null && resultado.getMsg() != null) {
                        out.print(resultado.getMsg());
                    }

                %>

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
                                label: "Dados primários",
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
                            },
                            {
                                label: "Dados secundários",
                                fillColor: "rgba(151,187,205,0.2)",
                                strokeColor: "rgba(151,187,205,1)",
                                pointColor: "rgba(151,187,205,1)",
                                pointStrokeColor: "#fff",
                                pointHighlightFill: "#fff",
                                pointHighlightStroke: "rgba(151,187,205,1)",
                                data: [28, 48, 40, 19, 86, 27, 90, 200, 87, 20, 50, 20]
                            }
                        ]
                    };

                    window.onload = function () {

                        var ctx = document.getElementById("GraficoLine").getContext("2d");
                        var LineChart = new Chart(ctx).Line(data, options);
                    }
                </script>

                <p><a href="http://websocialdev.com/graficos-chart-js-introducao/">Voltar para o página do tutorial</p>

            </div>
        </div>

    </div>	


</div>
<!---->