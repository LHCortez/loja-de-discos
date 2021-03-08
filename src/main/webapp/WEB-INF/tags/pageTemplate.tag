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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script data-search-pseudo-elements defer src="https://kit.fontawesome.com/0641055230.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>

<div id="background-image"></div>
<div class="container pagina-container">

    <%@ include file="/WEB-INF/view/header.jsp"%>

    <main>
        <jsp:doBody />
    </main>

    <%@ include file="/WEB-INF/view/footer.jsp"%>

</div>



</body>

</html>

