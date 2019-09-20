<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 18.09.2019
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/style.css"/> ">
    <link rel="stylesheet"
          href='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>'>
    <script src='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>'></script>
    <script src='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"/>'></script>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="row">
        <div class="col-md-5 mx-auto">
            <div class="card text-white my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Registration</h5>
                    <form:form method="post" modelAttribute="registrationData" enctype="multipart/form-data">
                        <div class="form-label-group">
                            <form:input path="firstName" id="inputFirstName" class="form-control"/>
                            <label for="inputFirstName">First Name</label>
                            <font color="red"><form:errors path="firstName"/></font>
                        </div>

                        <div class="form-label-group">
                            <form:input path="lastName" id="inputLastName" class="form-control"/>
                            <label for="inputLastName">Last name</label>
                            <font color="red"><form:errors path="lastName"/></font>
                        </div>

                        <div class="form-label-group">
                            <form:input path="username" id="inputUsername" class="form-control"/>
                            <label for="inputUsername">Username</label>
                            <font color="red"><form:errors path="username"/></font>
                        </div>

                        <div class="form-label-group">
                            <form:input path="email" type="email" id="inputEmail" class="form-control"/>
                            <label for="inputEmail">Email address</label>
                            <font color="red"><form:errors path="email"/></font>
                        </div>

                        <div class="form-label-group">
                            <form:password path="password" id="inputPassword" class="form-control"/>
                            <label for="inputPassword">Password</label>
                            <font color="red"><form:errors path="password"/></font>
                        </div>

                        <div class="form-label-group">
                            <form:password path="rePassword" id="inputRePassword" class="form-control"/>
                            <label for="inputRePassword">RePassword</label>
                            <font color="red"><form:errors path="rePassword"/></font>
                        </div>

                        <div class="form-label-group">
                            <form:input path="avatar" type="file" id="inputAvatar" class="form-control-file"/>
                            <label for="inputAvatar">Avatar</label>
                            <font color="red"><form:errors path="avatar"/></font>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register
                        </button>
                        <button class="btn btn-lg btn-warning btn-block text-uppercase" type="reset">Clean
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
