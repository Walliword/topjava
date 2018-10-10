<%--
  Created by IntelliJ IDEA.
  User: Vallian
  Date: 10.10.2018
  Time: 7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
    <title>Meals</title>
</head>

<body>
<%-- Строка со ссылкой на индекс --%>
<h3><a href="index.html">Home</a></h3>
<%-- Снос строки --%>
<br/>
<%-- Непосредственно таблица --%>
<table class="table table-hover table-bordered">

    <tr>
        <th>Description</th>
        <th>Calories</th>
        <th>Date</th>
    </tr>

    <tbody>
    <c:forEach var="meal" items="${pageContext.request.getAttribute('mealsFiltered')}">

        <c:set var="col" value="${meal.exceed ? 'red': 'green'}"/>
        <tr style="color: ${col}">
            <td>${fn:replace(meal.dateTime,"T"," ")}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>

</html>
