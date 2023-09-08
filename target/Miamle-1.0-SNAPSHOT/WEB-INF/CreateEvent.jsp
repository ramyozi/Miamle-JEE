<%@page import="java.time.Year"%>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Create Event</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <main class="create-event">
            <h1>Créer un évenement</h1>
            <form action="<c:url value='/createEvent'/>" method="post">
                <div>
                    <label for="name">Nom de l'évenement</label>
                    <input type="text" id="name" name="name" value="<c:out value="${eventData.name}"/>">
                </div>
                <span class="error">${errors.name}</span><br>
                <div>
                    <label for="description">Description</label>
                    <textarea id="description" name="description"><c:out value="${eventData.description}"/></textarea>
                </div>
                <span class="error">${errors.description}</span><br>
                <div>
                    <label for="date">Date:</label>
                    <input type="date" name="date" value="${event.date_event}" min="<%=java.time.LocalDate.now().plusDays(1)%>">
                </div>
                <span class="error">${errors.date}</span><br>
                <div>
                    <input class="boutton" type="submit" value="Valider">
                    <input class="boutton" type="reset" value="Reset">
                </div>
            </form>
        </main>
        <%@include file="./jspf/Footer.jsp" %>
    </body>
</html>
