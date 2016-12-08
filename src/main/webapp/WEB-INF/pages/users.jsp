<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <title>Users</title>
</head>

<body>

<td><a href="<c:url value="/meters/0" />">Go to meters</a></td>

<c:url var="addAction" value="/add"/>
<form:form action="${addAction}" commandName="user">
    <table border>
        <caption>User</caption>
        <tr>
            <td>
                <form:label path="id">
                    <spring:message text="ID"/>
                </form:label>
            </td>
            <td><form:input path="id" readonly="true"/></td>
        </tr>
        <tr>
            <td>
                <form:label path="mail">
                    <spring:message text="Mail"/>
                </form:label>
            </td>
            <td><form:input type="text" path="mail" maxlength="45" required="required"/></td>
        </tr>
        <tr>
            <td>
                <form:label path="min">
                    <spring:message text="Min"/>
                </form:label>
            </td>
            <td><form:input type="number" path="min"/></td>
        </tr>
        <tr>
            <td>
                <form:label path="max">
                    <spring:message text="Max"/>
                </form:label>
            </td>
            <td><form:input type="number" path="max"/></td>
        </tr>
        <tr>
            <td>
                <form:label path="util">
                    <spring:message text="Util"/>
                </form:label>
            </td>
            <td><form:input type="number" path="util" min = "1" max = "3" value = "2"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.mail}">
                    <input type="submit" value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.mail}">
                    <input type="submit" value="<spring:message text="Add User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

<br/>

<table border align="centre" border="2">
    <caption>Users</caption>
    <tr>
        <th width="40">ID</th>
        <th width="300">Mail</th>
        <th width="40">Min</th>
        <th width="40">Max</th>
        <th width="40">Util</th>
        <th width="40">Edit</th>
        <th width="40">Remove</th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td width="40"><c:out value="${user.id}"/></td>
            <td width="300"><c:out value="${user.mail}"/></td>
            <td width="40"><c:out value="${user.min}"/></td>
            <td width="40"><c:out value="${user.max}"/></td>
            <td width="40"><c:out value="${user.util}"/></td>
            <td><a href="<c:url value="/edit/${user.id}" />">Edit</a></td>
            <td><a href="<c:url value="/remove/${user.id}" />">Remove</a></td>
        </tr>
    </c:forEach>
</table>

<br/>

<tr>
    Use paging:
    <c:if test="${page - 1 >= 0}">
        <td><a href="<c:url value="/users/${page - 1}" />">${page - 1}</a></td>
    </c:if>

    <td><a href="<c:url value="/users/${page}" />"><b><big>${page}</big></b></a></td>

    <c:if test="${page + 1 <= maxPage}">
        <td><a href="<c:url value="/users/${page + 1}" />">${page + 1}</a></td>
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
        <input type="button" value="Go" onclick="location.href = '/users/' + getEnteredPage()">
    </td>
</tr>

<br/>

<tr>
    <td>
        Or go to
    </td>
    <td><a href="<c:url value="/users/0" />">FirstPage</a></td>
    <td> or</td>
    <td><a href="<c:url value="/users/${maxPage}" />">LastPage</a></td>
</tr>
</body>

<br/>
Developed by <a href="<c:url value="http://sergiovanovi.com" />">sergiovanovi.com</a></td>
</html>