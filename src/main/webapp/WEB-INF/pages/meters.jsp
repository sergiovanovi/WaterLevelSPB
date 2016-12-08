<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meters</title>
</head>
<body>

<td><a href="<c:url value="/users/0" />">Go to users</a></td>

<table border align="centre" border="2">
    <caption>Meters</caption>
    <tr>
        <th width="40">Meter</th>
        <th width="200">Date</th>
    </tr>
    <c:forEach items="${meterList}" var="meter">
        <tr>
            <td width="40"><c:out value="${meter.meter}"/></td>
            <td width="200"><c:out value="${meter.date}"/></td>
        </tr>
    </c:forEach>
</table>

<br/>

<tr>
    Use paging:
    <c:if test="${page - 1 >= 0}">
        <td><a href="<c:url value="/meters/${page - 1}" />">${page - 1}</a></td>
    </c:if>

    <td><a href="<c:url value="/meters/${page}" />"><b><big>${page}</big></b></a></td>

    <c:if test="${page + 1 <= maxPage}">
        <td><a href="<c:url value="/meters/${page + 1}" />">${page + 1}</a></td>
    </c:if>
</tr>

<br/>
<tr>
    <td> Or choose page:</td>
    <td>
        <input type="number" id="enteredPage" min="0" max="${maxPage}" value="0">
        <script>
            function getEnteredPage() {
                var value = document.getElementById('enteredPage').value;
                return value;
            }
        </script>
        <input type="button" value="Go" onclick="location.href = '/meters/' + getEnteredPage()">
    </td>
</tr>

<br/>

<tr>
    <td>
        Or go to
    </td>
    <td><a href="<c:url value="/meters/0" />">FirstPage</a></td>
    <td> or</td>
    <td><a href="<c:url value="/meters/${maxPage}" />">LastPage</a></td>
</tr>

<br/>
Developed by <a href="<c:url value="http://sergiovanovi.com" />">sergiovanovi.com</a></td>
</body>
</html>
