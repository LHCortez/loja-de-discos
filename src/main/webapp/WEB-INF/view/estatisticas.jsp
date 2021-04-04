<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:crudTemplate>

    <!--Load the AJAX API Google Charts-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <!--Load the Embed API-->
    <script>
        (function(w,d,s,g,js,fs){
            g=w.gapi||(w.gapi={});g.analytics={q:[],ready:function(f){this.q.push(f);}};
            js=d.createElement(s);fs=d.getElementsByTagName(s)[0];
            js.src='https://apis.google.com/js/platform.js';
            fs.parentNode.insertBefore(js,fs);js.onload=function(){g.load('analytics');};
        }(window,document,'script'));
    </script>

    <!-- Load the DateRangeSelector component script. -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/date-range-selector.js" charset="UTF-8"></script>

    <!-- Gráficos com dados vindos do BD, utilizando o Google Charts -->
    <script type="text/javascript">

        // Load the Visualization API and the corechart package.
        google.charts.load('visualization', '1', {'packages': ['corechart']});

        // Set a callback to run when the Google Visualization API is loaded.
        google.charts.setOnLoadCallback(drawChart);

        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        function drawChart() {
            var quantidadeItensComprados = "${pageContext.request.contextPath}/estatisticas/data/quantidadeItensComprados";
            jQuery.getJSON(quantidadeItensComprados, {
                format: "json"
            }).done(function (quantidadeItensComprados) {
                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Tipo de Produto');
                data.addColumn('number', 'Quantidade vendida total');
                jQuery.each(quantidadeItensComprados, function (key, val) {
                    data.addRows([[key, val]]);
                });
                // Set chart options
                var options = {
                    // 'width': 400,
                    // 'height': 400,
                    'chartArea': {'width': '80%', 'height': '70%'},
                    'backgroundColor': '#f8f9fa'
                };
                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById('quantidadeItensComprados_div'));
                chart.draw(data, options);
                // Redesenha o gráfico, ajustando o tamanho, ao alterar o tamanho da janela
                $(window).resize(function(){
                    chart.draw(data, options);
                });
            })

            var generoBandaItensComprados = "${pageContext.request.contextPath}/estatisticas/data/generoBandaItensComprados";
            jQuery.getJSON(generoBandaItensComprados, {
                format: "json"
            }).done(function (generoBandaItensComprados) {
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Tipo de Produto');
                data.addColumn('number', 'Quantidade vendida total');
                jQuery.each(generoBandaItensComprados, function (key, val) {
                    data.addRows([[key, val]]);
                });
                var options = {
                    // 'width': 400,
                    // 'height': 400,
                    'chartArea': {'width': '80%', 'height': '70%'},
                    'backgroundColor': '#f8f9fa'
                };
                var chart = new google.visualization.PieChart(document.getElementById('generoBandaItensComprados_div'));
                chart.draw(data, options);
                $(window).resize(function(){
                    chart.draw(data, options);
                });
            });

            var receitaPorMes = "${pageContext.request.contextPath}/estatisticas/data/receitaPorMes";
            jQuery.getJSON(receitaPorMes, {
                format: "json"
            }).done(function (receitaPorMes) {
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Mês');
                data.addColumn('number', 'Valor');
                jQuery.each(receitaPorMes, function (key, val) {
                    data.addRows([[key, val]]);
                });
                var options = {
                    'hAxis': {'title': 'Valor (R$)'},
                    'legend': {'position': 'none'},
                    // 'width': 600,
                    // 'height': 500,
                    'chartArea': {'width': '70%', 'height': '80%'},
                    'backgroundColor': '#f8f9fa'
                };
                var chart = new google.visualization.BarChart(document.getElementById('receitaPorMes_div'));
                chart.draw(data, options);
                $(window).resize(function(){
                    chart.draw(data, options);
                });
            });
        }

    </script>

    <!-- Gráficos com dados vindos do Google Analytics, utilizando Embed API -->
    <script type="text/javascript">

        gapi.analytics.ready(function() {
            /**
             * Authorize the user with an access token obtained server side.
             */
            gapi.analytics.auth.authorize({
                'serverAuth': {
                    'access_token': '${analyticsToken}'
                }
            });
            /**
             * Query params representing the first chart's date range.
             */
            var dateRangeSessions = {
                'start-date': '7daysAgo',
                'end-date': 'yesterday'
            };
            /**
             * Create a new DateRangeSelector instance to be rendered inside of an
             * element with the id "date-range-selector-1-container", set its date range
             * and then render it to the page.
             */
            var dateRangeSessionsSelector = new gapi.analytics.ext.DateRangeSelector({
                container: 'sessions_date_range'
            })
                .set(dateRangeSessions)
                .execute();

            /**
             * Creates a new DataChart instance showing sessions over the dataRanger1.
             * It will be rendered inside an element with the id "chart-1-container".
             */
            var sessionsChart = new gapi.analytics.googleCharts.DataChart({
                query: {
                    'ids': 'ga:239340375', // <-- Replace with the ids value for your view.
                    'metrics': 'ga:sessions,ga:users',
                    'dimensions': 'ga:date'
                },
                chart: {
                    'container': 'sessions_div',
                    'type': 'LINE',
                    'options': {
                        'backgroundColor': '#f8f9fa',
                        'width': '100%'
                    }
                }
            }).set({query: dateRangeSessions});
            sessionsChart.execute();
            /**
             * Register a handler to run whenever the user changes the date range from
             * the first datepicker. The handler will update the first dataChart
             * instance as well as change the dashboard subtitle to reflect the range.
             */
            dateRangeSessionsSelector.on('change', function(data) {
                dateRangeSessions = data;
                sessionsChart.set({query: data}).execute();
            });


            var dateRangePopularPages = {
                'start-date': '7daysAgo',
                'end-date': 'yesterday'
            };

            var dateRangePopularPagesSelector = new gapi.analytics.ext.DateRangeSelector({
                container: 'popular_pages_date_range'
            })
                .set(dateRangePopularPages)
                .execute();


            var popularPagesChart = new gapi.analytics.googleCharts.DataChart({
                query: {
                    'ids': 'ga:239340375',
                    'metrics': 'ga:pageviews',
                    'dimensions': 'ga:pagePathLevel2',
                    'sort': '-ga:pageviews',
                    'filters': 'ga:pagePath=@/produto/',
                    'max-results': 10
                },
                chart: {
                    'container': 'popular_pages_div',
                    'type': 'PIE',
                    'options': {
                        'backgroundColor': '#f8f9fa',
                        'width': '100%',
                        'pieHole': 4/9,
                    }
                }
            }).set(dateRangePopularPages);
            popularPagesChart.execute();

            dateRangePopularPagesSelector.on('change', function(data) {
                popularPagesChart.set({query: data}).execute();
            });

            // Redimensiona automaticamente o tamanho do gráfico ao alterar o tamanho da janela, porém
            // foi retirado devido a disparar muitas requisições à API do Analytics, retornando o erro
            // 403 - Quota Error: User Rate Limit Exceeded.
            // $(window).resize(function(){
            //     sessionsChart.execute();
            //     popularPagesChart.execute();
            // });

        });
    </script>


    <div class="row p-lg-3">
        <div class="col-md-12 text-center mb-5">
            <h1>Estatísticas</h1>
        </div>
        <div class="col-md-6 p-lg-5">
            <h2 class="fs-5">Quantidade de itens vendidos por tipo</h2>
            <div id="quantidadeItensComprados_div" class="chart"></div>
        </div>
        <div class="col-md-6 p-lg-5">
            <h2 class="fs-5">Quantidade de itens vendidos por gênero</h2>
            <div id="generoBandaItensComprados_div" class="chart"></div>
        </div>
        <div class="col-md-6 p-lg-5">
            <h2 class="fs-5">Pagamentos recebidos nos últimos 12 meses</h2>
            <div id="receitaPorMes_div" class="chart"></div>
        </div>
        <div class="col-md-6 p-lg-5 mt-5 mt-sm-0">
            <h2 class="fs-5">Tráfego no site</h2>
            <div id="sessions_div" class="chart overflow-auto"></div>
            <div id="sessions_date_range"></div>
        </div>
        <div class="col-md-6 p-lg-5 mt-5 mt-sm-0">
            <h2 class="fs-5">Produtos mais visitados (/produto/*)</h2>
            <div id="popular_pages_div" class="chart overflow-auto"></div>
            <div id="popular_pages_date_range"></div>
        </div>
    </div>

</tags:crudTemplate>