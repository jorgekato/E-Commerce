<%-- 
    Document   : graficobarra
    Created on : 09/05/2016, 20:48:09
    Author     : Henrique
--%>

<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroClienteVendaPeriodo"%>
<div class="content">
    <div class="account-in">
        <div class="box">

            <div class="box-chart">

                <canvas id="GraficoBarra" style="width:100%;"></canvas>

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
                        labels: [
                    <%                        
                        if (grafico != null) {
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
                                fillColor: "rgba(220,220,220,0.5)",
                                strokeColor: "rgba(220,220,220,0.8)",
                                highlightFill: "rgba(220,220,220,0.75)",
                                highlightStroke: "rgba(220,220,220,1)",
                                data: [
                    
                    <%
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
                    %>
                                                            
                                        ]
                            }
                        ]
                    };
                    window.onload = function () {
                        var ctx = document.getElementById("GraficoBarra").getContext("2d");
                        var BarChart = new Chart(ctx).Bar(data, options);
                    }
                </script>
            </div>

        </div>

    </div>	


</div>
<!---->
