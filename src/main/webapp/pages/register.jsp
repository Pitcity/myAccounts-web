<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <!--link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
<header class="slider">
    <div class="middleContainer">
        <c:import url="../WEB-INF/parts/header.jsp"></c:import>
    </div>
</header>
<main>
    <section class="glyphicon-registration-mark">
        <input type="text" name="Name" required placeholder="Name">
        <input type="text" name="Surname" required placeholder="Surname">
        <input type="text" name="Password" required placeholder="Password">
        <input type="text" name="ConfirmPassword" required placeholder="Confirm password">
    </section>
</main>
<footer><c:import url="../WEB-INF/parts/footer.jsp"></c:import></footer>
</body>

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script src="http://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/messages.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/validation.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/mainPage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery.dataTables.min.js"></script>

</html>
