<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Inscription</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <main class="signin">
            <h1>Page d'inscription</h1>

            <form method="post" action="<c:url value='/sign-in' />">
                <div>
                    <label for="contact">Email ou téléphone</label>
                    <input type="text" name="contact" id="contact" value="${requestScope.userD.contact}" required>
                </div>
                <span class="error">${requestScope.errors.contact}</span>
                <div>
                    <label for="password">Mot de passe</label>
                    <input type="password" name="password" id="password" value="${requestScope.userD.password}" required>
                </div>
                <span class="error">${requestScope.errors.password}</span>
                <div>
                    <label for="confirmPassword">Confirmation</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" required>
                </div>
                <span class="error">${requestScope.errors.confirmPassword}</span>
                <div>
                    <label for="pseudo">Pseudo</label>
                    <input type="text" name="pseudo" id="pseudo" value="${requestScope.userD.pseudo}" required>
                </div>
                <span class="error">${requestScope.errors.pseudo}</span>
                <div>
                    <input class="boutton" type="submit" value="Valider">
                    <input class="boutton" type="reset" value="Annuler">
                </div>
            </form>
        </main>
        <%@include file="./jspf/Footer.jsp" %>
    </body>
</html>

