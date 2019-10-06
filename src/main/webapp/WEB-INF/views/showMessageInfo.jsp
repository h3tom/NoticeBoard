<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 05.10.2019
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message info</title>
    <%@include file="fragments/head.jspf" %>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="row">
        <div class="col-md-5 mx-auto">
            <div class="card bg-dark my-4">
                <div class="card-body text-white">
                    <c:if test="${!username.equals(message.sender)}">
                        <h6 class="card-text">
                            <span style="color: #ff6600"> Sender: </span>
                                ${message.sender}
                        </h6>
                        <hr style="border-color: darkred">
                    </c:if>
                    <c:if test="${username.equals(message.sender)}">
                        <h6 class="card-text">
                            <span style="color: #ff6600"> Recipient: </span>
                                ${message.receiver}
                        </h6>
                        <hr style="border-color: darkred">
                    </c:if>
                    <h6 class="card-text">
                        <span style="color: #ff6600"> Title: </span>
                        ${message.title}
                    </h6>
                    <hr style="border-color: darkred">
                    <h6 class="card-text">
                        <p style="color: #ff6600"> Content: </p>
                        ${message.content}
                    </h6>
                    <hr style="border-color: darkred">
                    <p class="card-text">
                        <small class="text-muted">
                            Sent: ${message.created.dayOfMonth}.${message.created.monthValue}.${message.created.year}
                            ${message.created.hour}:${message.created.minute}
                        </small>
                        <c:if test="${!username.equals(message.sender)}">
                            <a class="btn btn-warning pull-right"
                               href="<c:url value="/add-message/${message.sender}"/>">Reply</a>
                        </c:if>
                    </p>
                </div>
            </div>
        </div>

    </div>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
