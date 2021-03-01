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

    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/0641055230.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/sweetalert2.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/js.js"></script>
</head>

<body class="bg-light">

    <%@ include file="/WEB-INF/view/crud/header.jsp"%>

    <main class="container-xl mt-5 mb-3">
        <jsp:doBody />
    </main>


</body>

</html>
