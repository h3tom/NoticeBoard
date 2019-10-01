<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hetom
  Date: 23.09.2019
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Notice</title>
    <%@include file="fragments/head.jspf"%>
    <script src="<c:url value="/resources/static/js/optionMultipleScript.js"/>"></script>
</head>
<body>

<div class="container">

    <%@include file="fragments/header.jspf" %>

    <div class="row">
        <div class="col-md-5 mx-auto">
            <div class="card text-white bg-dark my-4">
                <div class="card-body">
                    <h5 class="card-title text-center">Add Notice</h5>
                    <form:form modelAttribute="addNotice" method="post" enctype="multipart/form-data">
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
                        <div class="form-label-group">
                            <form:input path="endDate" type="date" class="form-control" id="addDate"/>
                            <label for="addContent"><i class="fa fa-calendar"></i> Ending date</label>
                            <span style="color: red; "><form:errors path="endDate"/></span>
                        </div>
                        <div class="form-label-group">
                            <form:input path="photo" type="file" class="form-control-file" id="addPhoto"/>
                            <label for="addPhoto"><i class="fa fa-picture-o"></i> Photo</label>
                            <span style="color: red; "><form:errors path="photo"/></span>
                        </div>
                        <div class="form-label-group">
                            <form:select path="categories" class="form-control" items="${allCategories}"
                                         itemValue="id" itemLabel="title" multiple="true" id="addCategories"/>
                            <label for="addCategories"><i class="fa fa-tags"></i> Categories</label>
                            <span style="color: red; "><form:errors path="categories"/></span>
                        </div>
                        <br>
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Add</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jspf" %>

</div>

</body>
</html>
