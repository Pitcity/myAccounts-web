<%@page import="dao.AccountDao"%>
<jsp:useBean id="obj" class="entity.Account">
</jsp:useBean>
<jsp:setProperty property="*" name="obj"/>

<%
    int i=AccountDao.addAcc(obj);
    if(i>0) {
        System.out.print("You are successfully registered");
    }
%>