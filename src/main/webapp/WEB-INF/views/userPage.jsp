<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 28.09.2019
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
    <%@include file="fragments/head.jspf" %>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="container py-4 my-2">
        <div class="row">
            <div class="col-md-4">
                <img class="w-100" src="data:image/jpeg;base64,${user.avatar}" alt="No image"/>
            </div>

            <div class="col-md-8">
                <h2>
                    ${user.firstName} ${user.lastName}
                </h2>
                <h3 class="text-primary">
                    ${user.username}
                </h3>
                <section class="d-flex mt-5">
                    <c:if test="${!username.equals(user.username)}">
                        <a class="btn btn-warning mr-3 mb-3" href="mailto:${user.email}">
                            <i class="fa fa-comments"></i> Send email</a>
                        <a class="btn btn-danger mr-3 mb-3">
                            <i class="fa fa-comments"></i> Private Message
                        </a>
                    </c:if>
                </section>
                <section class="mt-4">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="activeNotices-tab" data-toggle="tab" href="#activeNotices"
                               role="tab"
                               aria-controls="activeNotices" aria-selected="true">
                                Active Notices
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="finishedNotices-tab" data-toggle="tab" href="#finishedNotices"
                               role="tab"
                               aria-controls="finishedNotices" aria-selected="false">
                                Finished Notices
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="comments-tab" data-toggle="tab" href="#comments" role="tab"
                               aria-controls="comments" aria-selected="false">
                                Latest Comments
                            </a>
                        </li>
                        <c:if test="${username.equals(user.username)}">
                            <li class="nav-item">
                                <a class="nav-link" id="messages-tab" data-toggle="tab" href="#messages" role="tab"
                                   aria-controls="messages" aria-selected="false">
                                    Messages
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <div class="tab-content py-4" id="myTabContent">
                        <div class="tab-pane py-3 fade show active" id="activeNotices" role="tabpanel"
                             aria-labelledby="activeNotices-tab">
                            <c:forEach items="${user.notices}" var="notice">
                                <c:if test="${notice.endDate.isAfter(localDate) || notice.endDate.isEqual(localDate)}">
                                    <div class="offset-2 col-md-8 my-2">
                                        <div class="card bg-dark text-white">
                                                ${notice.created.dayOfMonth}.${notice.created.monthValue}.${notice.created.year}
                                                ${notice.created.hour}:${notice.created.minute}
                                            <div class="card-body">
                                                <span style="color: #ff6600">Title: </span>${notice.title}
                                                <a class="btn btn-warning pull-right"
                                                   href="<c:url value="/notice-info/${notice.id}"/>">Go to notice</a>
                                                <p class="card-text">
                                                    <small class="text-muted"> Offer valid
                                                        till: ${notice.endDate}</small>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>

                        <div class="tab-pane py-3 fade" id="finishedNotices" role="tabpanel"
                             aria-labelledby="finishedNotices-tab">
                            <c:forEach items="${user.notices}" var="notice">
                                <c:if test="${notice.endDate.isBefore(localDate)}">
                                    <div class="offset-2 col-md-8 my-2">
                                        <div class="card bg-dark text-white">
                                                ${notice.created.dayOfMonth}.${notice.created.monthValue}.${notice.created.year}
                                                ${notice.created.hour}:${notice.created.minute}
                                            <div class="card-body">
                                                <span style="color: #ff6600">Title: </span>${notice.title}
                                                <a class="btn btn-warning pull-right"
                                                   href="<c:url value="/notice-info/${notice.id}"/>">Go to notice</a>
                                                <p class="card-text">
                                                    <small class="text-muted"> Offer valid
                                                        till: ${notice.endDate}</small>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>

                        <div class="tab-pane py-3 fade" id="comments" role="tabpanel"
                             aria-labelledby="comments-tab">
                            <c:forEach items="${user.comments}" var="comment" end="9">
                                <div class="offset-2 col-md-8 my-2">
                                    <div class="card bg-dark text-white">
                                            ${comment.created.dayOfMonth}.${comment.created.monthValue}.${comment.created.year}
                                            ${comment.created.hour}:${comment.created.minute}
                                        <div class="card-body">
                                            <span style="color: #ff6600">Content: </span>${comment.content}
                                            <a class="btn btn-warning pull-right"
                                               href="<c:url value="/notice-info/${comment.noticeId}"/>">Go to notice</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <c:if test="${username.equals(user.username)}">
                            <div class="tab-pane py-3 fade" id="messages" role="tabpanel"
                                 aria-labelledby="messages-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <p class="my-1" style="color: white">Received:</p>
                                        <c:forEach items="${messagesReceived}" var="messageReceived">
                                            <div class="my-2">
                                                <div class="card bg-dark text-white">
                                                        <span>${messageReceived.created.dayOfMonth}.${messageReceived.created.monthValue}.${messageReceived.created.year}
                                                        ${messageReceived.created.hour}:${messageReceived.created.minute}
                                                    <c:if test="${messageReceived.readMessage==0}">
                                                        <span style="font-size: large; font-weight: bold">Message unread!</span>
                                                    </c:if>
                                                        </span>
                                                    <div class="card-body">
                                                        <span style="color: #ff6600">Sender: </span>${messageReceived.sender}
                                                        <br>
                                                        <span style="color: #ff6600">Title: </span>${messageReceived.title}
                                                        <a class="btn btn-warning pull-right"
                                                           href="<c:url value="/message/${messageReceived.id}"/>">Go
                                                            to message</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="my-1" style="color: white">Sent:</p>
                                        <c:forEach items="${messagesSent}" var="messageSent">
                                            <div class="my-2">
                                                <div class="card bg-dark text-white">
                                                        <span>${messageSent.created.dayOfMonth}.${messageSent.created.monthValue}.${messageSent.created.year}
                                                        ${messageSent.created.hour}:${messageSent.created.minute}
                                                        </span>
                                                    <div class="card-body">
                                                        <span style="color: #ff6600">Title: </span>${messageSent.title}
                                                        <a class="btn btn-warning pull-right"
                                                           href="<c:url value="/message/${messageSent.id}"/>">Go
                                                            to message</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </section>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
