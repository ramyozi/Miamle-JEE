<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Profil</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <main class="profil">
            <h1>Vos informations personnelles</h1>
            <table>
                <tr>
                    <td> Votre id : </td>
                    <td> ${sessionScope.user.id} </td>
                </tr>
                <tr>
                    <td> Votre Pseudo : </td>
                    <td> ${sessionScope.user.pseudo} </td>
                </tr>
                <tr>
                    <td> Votre Contact : </td>
                    <td> ${sessionScope.user.contact} </td>
                </tr>
                
            </table>
        </main>
        <%@include file="./jspf/Footer.jsp" %>
    </body>
</html>
