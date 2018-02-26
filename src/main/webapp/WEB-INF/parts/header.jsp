<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: IhorTovpinets
  Date: 25.02.2018
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a href="#"><img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="logo"></a>
<div class="menu mainMenu">
    <nav>
        <a href="#accountsTable">Accounts</a>
        <a href="#" id="addAccBtn">Add Account</a>
        <a href="#" id="addDealBtn">Add Deal</a>
        <a href="#statistic">Statistic</a>
    </nav>
</div>
<div class="menu logInMenu">
    <nav>
        <a href="#" class="notLoggedIn">Log In</a>
        <a href="../../pages/register.jsp" class="notLoggedIn">Sing In</a>
        <a href="#" class="loggedIn">Log Out</a>
    </nav>
</div>
