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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/style.css"/> ">
    <link rel="stylesheet"
          href='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>'>
    <link href="<c:url value="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>"
          rel="stylesheet">
    <script src='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>'></script>
    <script src='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"/>'></script>
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
                            <i class="fa fa-comments"></i>Send email</a>
                        <button class="btn btn-danger mr-3 mb-3">
                            <i class="fa fa-comments"></i>Private Message
                        </button>
                    </c:if>
                </section>
                <section class="mt-4">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="notices-tab" data-toggle="tab" href="#notices" role="tab"
                               aria-controls="notices" aria-selected="true">
                                Notices
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="comments-tab" data-toggle="tab" href="#comments" role="tab"
                               aria-controls="comments" aria-selected="false">
                                Latest Comments
                            </a>
                        </li>
                    </ul>
                    <div class="tab-content py-4" id="myTabContent">
                        <div class="tab-pane py-3 fade show active" id="notices" role="tabpanel"
                             aria-labelledby="notices-tab">
                            <c:forEach items="${user.notices}" var="notice">
                                <div class="offset-2 col-md-8 my-2">
                                    <div class="card bg-dark text-white">
                                            ${notice.created.dayOfMonth}.${notice.created.monthValue}.${notice.created.year}
                                            ${notice.created.hour}:${notice.created.minute}
                                        <div class="card-body">
                                            <span style="color: #ff6600">Title: </span>${notice.title}
                                            <a class="btn btn-warning pull-right"
                                               href="<c:url value="/notice-info/${notice.id}"/>">Go to notice</a>
                                            <p class="card-text">
                                                <small class="text-muted"> Offer valid till: ${notice.endDate}</small>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="tab-pane py-3 fade" id="comments" role="tabpanel"
                             aria-labelledby="comments-tab">
                            <c:forEach items="${user.comments}" var="comment">
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
                    </div>
                </section>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
