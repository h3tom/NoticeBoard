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
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="addNotice col-md-3">
        <div class="card small text-white bg-dark my-4">
            <div class="card-body">
                <a href="<c:url value="/add-notice"/> ">
                    <h5 class="card-text text-center">
                        Add new Notice
                    </h5>
                </a>
            </div>
        </div>
    </div>

    <c:forEach items="${allNotices}" var="notice">
        <div class="card bg-dark offset-md-6 my-4" style="max-width: 500px;">
            <div class="row no-gutters">
                <div class="col-md-6">
                    <img src="" class="card-img-top"
                         onerror="this.onerror=null;this.src='<c:url
                                 value="/resources/static/img/no-image-available.png"/>'"
                         alt="No Image" style="height: 100%">
                </div>
                <div class="text-white col-md-6">
                    <div class="card-body">
                        <h6 class="card-title">${notice.owner} ${notice.created.dayOfMonth}.${notice.created.monthValue}.${notice.created.year}
                                ${notice.created.hour}:${notice.created.minute}</h6>
                        <h5 class="card-title">${notice.title}</h5>
                        <p class="card-text">${notice.content.substring(0,notice.content.length() < 30 ? notice.content.length() : 30)}...</p>
                        <p class="card-text"><small class="text-muted">Offer valid till: ${notice.endDate}</small></p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
