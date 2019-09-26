<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <div class="col-md-10">
        <div class="card bg-dark offset-3 my-4">
            <div class="row no-gutters">
                <div class="col-md-5">
                    <img src="data:image/jpeg;base64,${noticeInfo.image}" class="card-img-top"
                         onerror="this.onerror=null;this.src='<c:url
                                 value="/resources/static/img/no-image-available.png"/>'" height="220"
                         alt="No Image">
                </div>
                <div class="card-colour text-white col-md-7">
                    <div class="card-body">
                        <h6 class="card-title">
                            ${noticeInfo.owner} ${noticeInfo.created.dayOfMonth}.${noticeInfo.created.monthValue}.${noticeInfo.created.year}
                            ${noticeInfo.created.hour}:${noticeInfo.created.minute}
                        </h6>
                        <h5 class="card-title">
                            ${noticeInfo.title}
                        </h5>
                        <p class="card-text">
                            ${noticeInfo.content}
                        </p>
                        <p class="card-text">
                            <small class="text-muted">
                                Offer valid till: ${noticeInfo.endDate}
                            </small>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
