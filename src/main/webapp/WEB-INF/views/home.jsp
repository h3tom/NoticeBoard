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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/style.css"/> ">
    <link rel="stylesheet"
          href='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>'>
    <link href="<c:url value="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>"
          rel="stylesheet">
    <script src='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>'></script>
    <script src='<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"/>'></script>
    <script src="<c:url value="/resources/static/js/selectCategoryScript.js"/>"></script>
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
                                ${notice.owner} ${notice.created.dayOfMonth}.${notice.created.monthValue}.${notice.created.year}
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
