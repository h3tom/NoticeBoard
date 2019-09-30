<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 29.09.2019
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <%@include file="fragments/headFragment.jspf"%>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="row">
        <div class="col-md-5 mx-auto">
            <div class="card text-white bg-dark my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Edit Info</h5>
                    <form:form method="post" modelAttribute="userData" enctype="multipart/form-data">
                        <div class="form-label-group">
                            <form:input path="firstName" id="inputFirstName" class="form-control"
                                        value="${user.firstName}"/>
                            <label for="inputFirstName"><i class="fa fa-id-card"></i> First Name</label>
                            <span style="color: red; "><form:errors path="firstName"/></span>
                        </div>

                        <div class="form-label-group">
                            <form:input path="lastName" id="inputLastName" class="form-control"
                                        value="${user.lastName}"/>
                            <label for="inputLastName"><i class="fa fa-id-card"></i> Last name</label>
                            <span style="color: red; "><form:errors path="lastName"/></span>
                        </div>

                        <div class="form-label-group">
                            <form:input path="username" id="inputUsername" class="form-control"
                                        value="${user.username}"/>
                            <label for="inputUsername"><i class="fa fa-user"></i> Username</label>
                            <span style="color: red; "><form:errors path="username"/></span>
                        </div>

                        <div class="form-label-group">
                            <form:input path="email" type="email" id="inputEmail" class="form-control"
                                        value="${user.email}"/>
                            <label for="inputEmail"><i class="fa fa-envelope-o"></i> Email address</label>
                            <span style="color: red; "><form:errors path="email"/></span>
                        </div>

                        <div class="form-label-group">
                            <form:password path="password" id="inputPassword" class="form-control"/>
                            <label for="inputPassword"><i class="fa fa-lock"></i> Password</label>
                            <span style="color: red; "><form:errors path="password"/></span>
                        </div>

                        <div class="form-label-group">
                            <form:password path="rePassword" id="inputRePassword" class="form-control"/>
                            <label for="inputRePassword"><i class="fa fa-lock"></i> RePassword</label>
                            <span style="color: red; "><form:errors path="rePassword"/></span>
                        </div>

                        <div class="form-label-group">
                            <form:input path="avatar" type="file" id="inputAvatar" class="form-control-file"/>
                            <label for="inputAvatar"><i class="fa fa-picture-o"></i> Avatar</label>
                            <span style="color: red; "><form:errors path="avatar"/></span>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Edit
                        </button>
                        <button class="btn btn-lg btn-warning btn-block text-uppercase" type="reset">Clean
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
