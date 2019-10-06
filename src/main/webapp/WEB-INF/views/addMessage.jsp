<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 05.10.2019
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add message</title>
    <%@include file="fragments/head.jspf"%>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf"%>

    <div class="col-md-5 mx-auto">
        <div class="card text-white bg-dark my-4">
            <div class="card-body">
                <h5 class="card-title text-center">Add Message</h5>
                <form:form modelAttribute="messageData" method="post" action="/add-message">
                    <div class="form-label-group">
                        <form:input path="title" class="form-control"
                                    id="addTitle"/>
                        <label for="addTitle"><i class="fa fa-pencil"></i> Title</label>
                        <span style="color: red; "><form:errors path="title"/></span>
                    </div>
                    <div class="form-label-group">
                        <form:textarea path="content" class="form-control"
                                       id="addContent"/>
                        <label for="addContent"><i class="fa fa-pencil"></i> Content</label>
                        <span style="color: red; "><form:errors path="content"/></span>
                    </div>
                    <br>
                    <form:hidden path="sender" value="${messageData.sender}"/>
                    <form:hidden path="receiver" value="${messageData.receiver}"/>
                    <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Send</button>
                </form:form>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf"%>
</div>

</body>
</html>
