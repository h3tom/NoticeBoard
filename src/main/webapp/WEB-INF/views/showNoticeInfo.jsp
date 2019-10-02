<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 25.09.2019
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notice Info</title>
    <%@include file="fragments/head.jspf"%>
    <script src="<c:url value="/resources/static/js/commentCharCountScript.js"/>"></script>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="row">
        <div class="col-md-5">
            <div class="card bg-dark my-4">
                <img src="data:image/jpeg;base64,${noticeInfo.image}" class="card-img-top"
                     onerror="this.onerror=null;this.src='<c:url
                             value="/resources/static/img/no-image-available.png"/>'"
                     height="300" alt="No Image">
                <div class="card-body text-white">
                    <h6 class="card-title">
                        <a href="<c:url value="/user-page/${noticeInfo.owner}"/>">${noticeInfo.owner}</a> ${noticeInfo.created.dayOfMonth}.${noticeInfo.created.monthValue}.${noticeInfo.created.year}
                        ${noticeInfo.created.hour}:${noticeInfo.created.minute}
                        <c:if test="${username.length()>0 and !username.equals(noticeInfo.owner)}">
                            <a class="btn btn-danger pull-right mr-3 mb-3">
                                <i class="fa fa-comments"></i> Private Message
                            </a>
                        </c:if>
                    </h6>
                    <h6 class="card-text">
                        <p style="color: #ff6600"> Title: </p>
                        ${noticeInfo.title}
                    </h6>
                    <hr style="border-color: darkred">
                    <h6 class="card-text">
                        <p style="color: #ff6600"> Content: </p>
                        ${noticeInfo.content}
                    </h6>
                    <hr style="border-color: darkred">
                    <p class="card-text">
                        <small class="text-muted"> Offer valid till: ${noticeInfo.endDate}</small>
                    </p>
                </div>
            </div>
        </div>


        <div class="offset-1 col-md-5">
            <c:if test="${noticeInfo.endDate.isAfter(localDate) or noticeInfo.endDate.isEqual(localDate)}">
                <div class="card text-white bg-dark my-4">
                    <div class="card-body">
                        <h5 class="card-title text-center">Add comment</h5>
                        <form:form modelAttribute="commentData" method="post" action="/add-comment">
                            <div class="form-label-group">
                                <form:textarea class="form-control" path="content" id="inputComment"
                                               maxlength="60"/>
                                <span style="color: bisque; float: right">/60</span>
                                <span class="counter" style="color: bisque; float: right">0</span>
                                <label for="inputComment"><i class="fa fa-comment"></i> Content</label>
                            </div>
                            <form:hidden path="noticeId" value="${noticeInfo.id}"/>
                            <button class="btn btn-primary pull-right" type="submit"> Add</button>
                        </form:form>
                    </div>
                </div>
            </c:if>
            <p class="my-4" style="color: white">Comments:</p>
            <c:forEach items="${allComments}" var="comment">
                <div class="card text-white bg-dark mt-1">
                    <div class="card-body">
                            ${comment.unregisteredUsername} <a
                            href="<c:url value="/user-page/${comment.registeredUsername}"/>">${comment.registeredUsername}</a>
                            ${comment.created.dayOfMonth}.${comment.created.monthValue}.${comment.created.year}
                            ${comment.created.hour}:${comment.created.minute}
                        <hr style="border-color: darkred">
                        <h6 class="card-text">
                            <p style="color: #ff6600"> Content: </p>
                                ${comment.content}
                        </h6>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
