<%-- 
    Document   : Footer
    Created on : 27 avr. 2023, 12:59:37
    Author     : stag
--%>
<footer>
    <c:choose>
        <c:when test="${!empty sessionScope.user}"> Vous etes connect� avec l'id: <span>${sessionScope.user.id}</span>  pseudo: 
            <span>${sessionScope.user.pseudo}</span> et votre contact : <span>${sessionScope.user.contact}</span></c:when>
        <c:otherwise>
            vous n'�tes pas connect�.
        </c:otherwise>
    </c:choose>
    <c:if test="${sessionScope.user.id == 1}"> et vous �tes <span>administrateur</span></c:if>
</footer>
