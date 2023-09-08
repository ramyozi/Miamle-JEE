<header>
    <nav>
        <ul>
            <li><a href="<c:url value='/home'/>">Home</a></li>
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                <li><a href="<c:url value='/sign-in'/>">Inscription</a></li>
                <li><a href="<c:url value='/login'/>">Connexion</a></li>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.user.id == 1}">
                    <li><a href="<c:url value='/createEvent'/>">Créer un évenement</a></li>
                    </c:if>
                    <c:if test="${( sessionScope.user.id != null) && sessionScope.user.id != 1}">
                    <li><a href="<c:url value='/myEvents'/>">Mes évenements</a></li>
                    </c:if>
                <li><a href="<c:url value='/profile'/>" class="dropbtn">Profil</a></li>
                <li><a href="<c:url value='/logout'/>">Se deconnecter</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>