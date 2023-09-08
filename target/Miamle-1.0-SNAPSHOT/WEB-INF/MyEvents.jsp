<%-- 
    Document   : MyEvents
    Created on : 12 mai 2023, 07:15:33
    Author     : stag
--%>
<%@include file="taglibs.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Mes évenements</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <table style="padding-top: 50px">
            <tr>
                <th>Nom de l'évenement</th>
                <th>Date</th>
                <th>Nombre des participants</th>
                <th>Action</th>
            </tr>
            <tr>
                <c:forEach items="${myEvents}" var="myEvents">
                    <td>${DaoFactory.getEventDao().getNameByIdEvent(myEvents.id_event)}</td>
                    <td>/</td>
                    <td>/</td>
                    <td>/</td>
                </c:forEach>
            </tr>
        </table>
    </body>
</html>
