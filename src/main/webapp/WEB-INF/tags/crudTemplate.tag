<%@ tag language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>LHC Discos</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css">
    <link type='text/css' rel='stylesheet' media='screen' href='${pageContext.request.contextPath}/css/style.css'>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">

    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/0641055230.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sweetalert2.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/js.js"></script>

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
</head>

<body class="bg-light">

    <%@ include file="/WEB-INF/view/crud/header.jsp"%>

    <main class="container-xl mt-5 mb-3">
        <jsp:doBody />
    </main>


</body>

</html>
