<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Connexion</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <main class="login">
            <h1>Page de connexion</h1>

            <c:if test="${not empty errorMessage}">
                <p class="error-message">${errorMessage}</p>
            </c:if>

            <form action="<c:url value='/login'/>" method="post" >
                <div>
                    <label for="pseudo">Pseudo</label>
                    <input type="text" id="pseudo" name="pseudo" value="<c:out value="${userData.pseudo}"/>" required><br>                    
                </div>
                <span class="error">${errors.pseudo}</span>
                <div>
                    <label for="pwd">Password:</label>
                    <input type="password" id="pwd" name="pwd" required><br>
                </div>
                <span class="error">${errors.pwd}</span>
                <div>
                    <input class="boutton" type="submit" value="Envoyer" >
                    <input class="boutton" type="reset" value="Annuler" >
                </div>
            </form>
        </main>
        <%@include file="./jspf/Footer.jsp" %>
    </body>
</html>
