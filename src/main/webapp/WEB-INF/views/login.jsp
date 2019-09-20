<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 18.09.2019
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/style.css"/> ">
    <link rel="stylesheet"
          href='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>'>
    <script src='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>'></script>
    <script src='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"/>'></script>
</head>
<body>

<div class="container" >

    <%@include file="fragments/header.jspf" %>

    <div class="row">
        <div class="col-md-5 mx-auto">
            <div class="card text-white my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Sign In</h5>
                    <div>
                        <c:choose>
                            <c:when test="${param.error != null}">
                                <p class="text-center"><font color="red">Wrong username/password</font></p>
                            </c:when>
                            <c:when test="${param.logout != null}">
                                <p class="text-center"><font color="yellow">Successfully logged out</font></p>
                            </c:when>
                        </c:choose>
                    </div>
                    <form:form method="post" modelAttribute="loginData">
                        <div class="form-label-group">
                            <form:input path="username" id="inputUsername" class="form-control"/>
                            <label for="inputUsername">Username</label>
                        </div>

                        <div class="form-label-group">
                            <form:password path="password" id="inputPassword" class="form-control"/>
                            <label for="inputPassword">Password</label>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                        <a class="btn btn-lg btn-warning btn-block text-uppercase" href="<c:url value="/register"/>">Sign
                            up</a>
                    </form:form>
                </div>
            </div>
        </div>
    </div>


</div>

</body>
</html>
