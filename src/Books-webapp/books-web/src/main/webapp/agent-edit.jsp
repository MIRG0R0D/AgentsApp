<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Edit agent</h2>
<c:if test="${not empty chyba}">
    <div style="border: solid 1px red; background-color: yellow; padding: 10px">
        <c:out value="${chyba}"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/agents/editSave?id=${agent.id}" method="post">
    <table>
        <tr>
            <th>name:</th>
            <td><input type="text" name="name" value="<c:out value='${agent.name}'/>"/></td>
        </tr>
        <tr>
            <th>born:</th>
            <td><input type="text" name="born" value="<c:out value='${agent.born}'/>"/></td>
        </tr>
        <tr>
            <th>level:</th>
            <td><input type="text" name="level" value="<c:out value='${agent.level}'/>"/></td>
        </tr>
    </table>
    <input type="Submit" value="Save" />
</form>

</body>
</html>
