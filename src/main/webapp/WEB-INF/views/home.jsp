<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 22.09.2019
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <%@include file="fragments/head.jspf" %>
    <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"/>"
            integrity="sha256-4jNHNewFZSwkUiWXCfjQ0erj6guZPNAjfiJQiBHk4K4=" crossorigin="anonymous"></script>
    <script src="<c:url value="/resources/static/js/selectCategoryScript.js"/>"></script>
    <script src="<c:url value="/resources/static/js/chatScript.js"/>"></script>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="addNotice col-md-3 my-4">
        <a class="btn btn-lg btn-primary btn-block" href="<c:url value="/add-notice"/>">
            Add new Notice
        </a>
    </div>

    <div class="categories col-md-3">
        <div class="card text-white bg-dark">
            <div class="card-body">
                <h6 class="card-title">Categories:</h6>
                <label>
                    <select class="categoriesOptions form-control">
                        <option>All</option>
                        <c:forEach items="${allCategories}" var="category">
                            <option value="${category.id}">${category.title}</option>
                        </c:forEach>
                    </select>
                </label>
                <a class="goToCategory btn btn-warning" href="<c:url value="/home/"/>">Go to</a>
            </div>
        </div>
    </div>

    <c:if test="${username.length()>0}">
        <div class="messages col-md-3">
            <label for="chatMessageToSend">Your message:</label>
            <input id="chatMessageToSend" type="text"/>
            <span class="input-group-btn">
                <button class="btn btn-success" id="sendChatMessage">Send</button>
            </span>
            <div style="overflow: auto; height: 200px">
                <div id="response">
                </div>
            </div>
        </div>
    </c:if>

    <c:forEach items="${allNotices}" var="notice">
        <div class="card bg-dark offset-md-5 my-4" style="max-width: 600px;">
            <div class="row no-gutters">
                <div class="col-md-6">
                    <img src="data:image/jpeg;base64,${notice.image}" class="card-img"
                         onerror="this.onerror=null;this.src='<c:url
                                 value="/resources/static/img/no-image-available.png"/>'" height="220"
                         alt="No Image">
                </div>
                <div class="card-colour text-white col-md-6">
                    <div class="card-body">
                        <h6 class="card-title">
                            <a href="<c:url value="/user-page/${notice.owner}"/>">${notice.owner}</a> ${notice.created.dayOfMonth}.${notice.created.monthValue}.${notice.created.year}
                                ${notice.created.hour}:${notice.created.minute}
                        </h6>
                        <h5 class="card-title">
                            <a href="<c:url value="/notice-info/${notice.id}"/>">
                                    ${notice.title.substring(0,notice.title.length() < 12 ? notice.title.length() : 12)}(...)
                            </a>
                        </h5>
                        <p class="card-text">
                                ${notice.content.substring(0,notice.content.length() < 30 ? notice.content.length() : 30)}(...)
                        </p>
                        <p class="card-text">
                            <small class="text-muted">
                                Offer valid till: ${notice.endDate}
                            </small>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
