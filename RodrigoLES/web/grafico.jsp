<%-- 
    Document   : grafico
    Created on : 09/05/2016, 20:27:44
    Author     : Henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1" name="viewport">

    <title>Chart.js - criando gráficos com a biblioteca Chart.js</title>

    <script src="${pageContext.request.contextPath}/js/Chart.min.js"></script>

    <style type="text/css">

    *{
        font-family: calibri;        
    }

    .box {
        margin: 0px auto;
        width: 70%;
    }

    .box-chart {
        width: 100%;
        margin: 0 auto;
        padding: 10px;
    }

    </style>  

    <script type="text/javascript">
        var randomnb = function(){ return Math.round(Math.random()*300)};
    </script>  

</head>

<body>    

    <div class="box">

        <h1>Chart.js - criando gráficos com a biblioteca Chart.js - DEMO</h1>
        <small>Arquivo com testes e demo de funcionalidades da biblioteca Chart.js criado para o tutorial do blog Web Social Dev.</small>

        <h2>Gráfico Barra</h2>
        <small>Dados gerados com função javascript para números randômicos até 300.</small>

        <div class="box-chart">

            <canvas id="GraficoBarra" style="width:100%;"></canvas>

            <script type="text/javascript">                                        

                var options = {
                    responsive:true
                };

                var data = {
                    labels: ["Marina", "Carlos", "MariaJ", "MariaA", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
                    datasets: [
                        {
                            label: "Dados primários",
                            fillColor: "rgba(220,220,220,0.5)",
                            strokeColor: "rgba(220,220,220,0.8)",
                            highlightFill: "rgba(220,220,220,0.75)",
                            highlightStroke: "rgba(220,220,220,1)",
                            data: [<%= 127
                                        %>, <%= 119 %>, <%= 72.48%>, <%= 52 %>, randomnb(), randomnb(), randomnb(), randomnb(), randomnb(), randomnb(), randomnb(), randomnb()]
                        }
                    ]
                };                

                window.onload = function(){
                    var ctx = document.getElementById("GraficoBarra").getContext("2d");
                    var BarChart = new Chart(ctx).Bar(data, options);
                }           
            </script>

            <p><a href="http://websocialdev.com/graficos-chart-js-introducao/">Voltar para o página do tutorial</p>

        </div>
    
    </div>
</body>

</html>