<html xmlns:th="http://www.thymeleaf.org">
<head th:include="layout :: head(title=~{::title},links=~{})">
    <title>Please Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/logIn.css">
</head>
<body th:include="layout :: body" th:with="content=~{::content}">
<div class="loginForm" th:fragment="content">
    <form name="f" th:action="/login?${_csrf.parameterName}=${_csrf.token}" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </fieldset>
    </form>
</div>
</body>
</html>