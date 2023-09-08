<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Accueil</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <main class="home">
            <h1>La liste des évenements</h1>
            <table>
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.allEvents}" var="eventOrm">
                        <c:choose>
                            <c:when test="${eventOrm.event.organizer == true}">
                                <tr>
                                    <td>${eventOrm.event.name}</td>
                                    <td>${eventOrm.event.date_event}</td>
                                    <td>${eventOrm.event.description}</td>
                                    <td>
                                        <a class='boutton' href="<c:url value='/event?id=${eventOrm.event.id}'/>">Consulter</a>
                                        <c:if test="${sessionScope.user.id == 1}">
                                            <a class='boutton' href="<c:url value='/deleteEvent?id=${eventOrm.event.id_event}'/>">Supprimer</a>
                                            <a class='boutton' href="<c:url value='/editEvent?id=${eventOrm.event.id_event}'/>">Modifier</a>
                                        </c:if>

                                    </td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>
                       
            <div class="pagination">
                <div class="first">
                    <c:if test="${requestScope.page > 2}"><a href="<c:url value="/home?page=1"/>"><<</a></c:if>
                </div>
                <div class="prev">
                    <c:if test="${requestScope.page > 1}"><a href="<c:url value="/home?page=${requestScope.page - 1}"/>"><</a></c:if>
                </div>
                <div class="next">
                    <c:if test="${requestScope.page < requestScope.maxPage}"><a href="<c:url value="/home?page=${requestScope.page + 1}"/>">></a></c:if>
                </div>
                <div class="last">
                    <c:if test="${requestScope.page < requestScope.maxPage - 1}"><a href="<c:url value="/home?page=${requestScope.maxPage}"/>">>></a></c:if>
                </div>
            </div>
        </main>
        <%@include file="./jspf/Footer.jsp" %>
    </body>
</html>



