<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>MyAccounts-Web</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>

<body class="slider">
<header class="slider">
    <div class="middleContainer">
        <c:import url="parts/header.jsp"></c:import>
        <div class="flex">
            <div class="headerCenterBlock">
                <h1>It is time to control your money</h1>
                <c:if test="${pageContext.request.userPrincipal.name == '' or pageContext.request.userPrincipal.name == null}">
                    <a href="<c:url value="/registration"/>">
                        <div id="joinButton">Join now!</div>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</header>
<c:if test="${pageContext.request.userPrincipal.name == '' or pageContext.request.userPrincipal.name == null}">
    <c:import url="login.jsp"></c:import>
</c:if>
<sec:authorize access="hasRole('USER')">
    <main>

        <section id="accountsTable" class="transparentBack">
            <div class="middleContainer">
                <div id="divForTableWithAccs"></div>
            </div>
        </section>

        <section id="statistic" class="transparentBack">

        </section>


        <div id="dialogForAditingAccount" class="flow modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <form class="form" id="addingAccountForm">
                            <div class="form-group">
                                <h2>Account information</h2>
                            </div>
                            <div class="form-group">
                                <label for="accAddName">Name</label>
                                <input type="text" class="form-control" id="accAddName" name="accAddName"
                                       placeholder="Enter name for the Account">
                            </div>
                            <input type="text" id="accEditId" hidden>
                            <div class="form-group">
                                <label for="accAddDeposit">Deposit</label>
                                <input type="text" class="form-control" id="accAddDeposit" name="accAddDeposit"
                                       placeholder="Enter amount of money on account">
                            </div>
                            <div class="form-group">
                                <label for="accAddDescription">Description</label>
                                <input type="text" class="form-control" id="accAddDescription" name="accAddDescription"
                                       placeholder="Enter description">
                            </div>
                            <input type="button" class="btn btn-success " id="addNewAcc" value="Create Account">
                            <input type="button" hidden class="btn btn-success " id="editNewAcc" value="Edit Account">
                            <input type="button" class="btn btn-danger " id="hideDialog" value="Cancel">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="dialogForAdditingDeal" class="flow modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <form class="form" id="addingDealForm">
                            <div class="form-group">
                                <h2>Deal information</h2>
                            </div>
                            <div class="form-group">
                                <label for="addDealSeller">Seller</label>
                                <select onchange="checkSelected()" class="form-control" id="addDealSeller"
                                        name="addDealSeller">
                                </select>
                                <input type="text" hidden id="inputAddDealSeller" class="form-control"
                                       placeholder="Enter name for Seller">
                            </div>
                            <div class="form-group">
                                <label for="addDealBuyer">Buyer</label>
                                <select onchange="checkSelected()" class="form-control" id="addDealBuyer"
                                        name="addDealBuyer">
                                </select>
                                <input type="text" hidden id="inputAddDealBuyer" class="form-control"
                                       placeholder="Enter name for Buyer">
                            </div>
                            <div class="error" hidden id="inputAccountsError">
                                One account cant be buyer and seller in one time
                            </div>
                            <div class="form-group">
                                <label for="addDealSum">Sum</label>
                                <input type="text" class="form-control" id="addDealSum" name="addDealSum"
                                       placeholder="Enter sum for Deal">
                            </div>
                            <div class="form-group">
                                <label for="addDealNote">Note</label>
                                <input type="text" class="form-control" id="addDealNote" name="addDealNote"
                                       placeholder="Enter note for Deal">
                            </div>
                            <div class="form-group">
                                <label for="addDealDate">Date</label>
                                <input class="form-control" id="addDealDate" class="date-picker" name="addDealDate">
                            </div>
                            <input type="text" id="dealId" hidden>
                            <input type="button" class="btn btn-success " id="addNewDeal" value="Add Deal">
                            <input type="button" hidden class="btn btn-success " id="editNewDeal" value="Edit Account">
                            <input type="button" class="btn btn-danger " id="hideDealDialog" value="Cancel">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="dialogForDealsFroAcc" class="flow modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-title">
                        <div id="dealsForAccountName"></div>
                    </div>
                    <div class="modal-body">
                        <div id="divForTableWithDeals"></div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn" id="closeDealsForAcc" value="Close">
                    </div>
                </div>
            </div>
        </div>


        <div id="dialogForServerErrors" class="flow modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <h1 class="has-error error" id="errorMessage"></h1>
                        <input type="button" id="closeErrorDialog" class="btn" value="Close">
                    </div>
                </div>
            </div>
        </div>
    </main>
</sec:authorize>
<c:import url="parts/footer.jsp"></c:import>
</body>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/jquery.dataTables.min.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script src="http://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/messages.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/validation.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/mainPage.js"></script>

</html>