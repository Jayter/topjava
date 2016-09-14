<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>

<html>
<head>
    <title>Meal list</title>
</head>

<body>
<h2>Meal list</h2>
<c:set var="meals" value="${listMeals}" />
<table>
    <c:forEach items="${meals}" var="meal">
        <tr style="color:
                    <c:if test="${meal.exceed}"> red </c:if>
                    <c:if test="${!meal.exceed}"> green </c:if>
                ">
            <td width=200>
                <c:set var="date" value="${meal.dateTime}"/>
                <h2> ${f:matches(date, 'dd.MM.yyyy HH:mm')}</h2>
            </td>
            <td width=100>
                <h2>${meal.description}</h2>
            </td>
            <td width=60>
                <h2>${meal.calories}</h2>
            </td>
            <td width=60>
                <h2>${meal.exceed}</h2>
            </td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
