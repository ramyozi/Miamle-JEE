<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Edit Event</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <main class="edit-event">
            <h1>Editer un évenement</h1>
            <form action="<c:url value='/editEvent'/>" method="post">
                <input type="hidden" name="id" value="${event.id}">
                <div>
                    <label for="name">Nom de l'évenement</label>
                    <input type="text" name="name" value="${event.name}">
                </div>
                <div>
                    <label for="date">Date de l'évenement:</label>
                    <input type="date" name="date" value="${event.date_event}" min="<%=java.time.LocalDate.now().plusDays(1)%>">
                </div>
                <div>
                    <label for="description">Description:</label>
                    <textarea name="description">${event.description}</textarea>
                </div>
                <div>
                    <input class="boutton" type="submit" value="Update">
                </div>
            </form>
        </main>
        <%@include file="./jspf/Footer.jsp" %>
    </body>
</html>
