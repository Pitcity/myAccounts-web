<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>
<header class="slider">
    <div class="middleContainer">
        <c:import url="../parts/header.jsp"></c:import>
    </div>
</header>
<main>
    <div class="middleContainer flex">
        <form class=" needs-validation" novalidate>
            <div class="form-group">
                <h2>Registration form</h2>
            </div>
            <div class="form-row">
                <div class="col-md-5">
                    <label for="validationServer01">First name</label>
                    <input type="text" class="form-control is-valid" id="validationServer01" placeholder="First name"
                           required>
                    <div class="invalid-feedback">
                        Please provide a valid name.
                    </div>
                </div>
                <div class="col-md-7">
                    <label for="validationServer02">Last name</label>
                    <input type="text" class="form-control is-invalid" id="validationServer02" placeholder="Last name"
                           required>
                    <div class="invalid-feedback">
                        Please provide a valid surname.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-12">
                    <label for="validationServer03">Email</label>
                    <input type="email" class="form-control is-invalid" id="validationServer03" placeholder="Email"
                           required>
                    <div class="invalid-feedback">
                        Please provide a valid email.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-12">
                    <label for="validationServer05">Password</label>
                    <input type="email" class="form-control is-invalid" id="validationServer05" placeholder="Password"
                           required>
                    <div class="invalid-feedback">
                        Password should not contain special symbols.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-12 passwordSecurity">

                </div>
            </div>
            <div class="form-row">
                <div class="col-md-12">
                    <label for="validationServer06">Confirm password</label>
                    <input type="email" class="form-control is-invalid" id="validationServer06" placeholder="Confirm password"
                           required>
                    <div class="invalid-feedback">
                        Passwords are not the same.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <input type="submit" class="btn btn-success " id="registerAcc" value="Register">
                <input type="reset" class="btn btn-danger " id="cleanRegisterForm" value="Clean">
            </div>
        </form>
    </div>
</main>
<footer><c:import url="../parts/footer.jsp"></c:import></footer>
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
