<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a href="/home/"><img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="logo"></a>
<div class="menu mainMenu">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <nav>
            <a href="/home/#accountsTable">Accounts</a>
            <a href="#" id="addAccBtn">Add Account</a>
            <a href="#" id="addDealBtn">Add Deal</a>
            <a href="/home/#statistic">Statistic</a>
        </nav>
    </c:if>
</div>
<div class="menu logInMenu">
    <nav>
        <c:if test="${pageContext.request.userPrincipal.name == '' or pageContext.request.userPrincipal.name == null}">
            <a href="#" class="notLoggedIn">Log In</a>
            <a href="<c:url value="/registration"/>" class="notLoggedIn">Sing In</a>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="<c:url value="/logout" />">Log Out</a>
        </c:if>
    </nav>
</div>
