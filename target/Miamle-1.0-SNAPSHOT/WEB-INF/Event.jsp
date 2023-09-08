<%@include file="taglibs.jsp" %>
<%@page import="g3.dao.DaoFactory"%>
<%@page import="g3.dao.DaoAttendance"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="rsc/css/Style.css" />
        <title>Evenement</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/Header.jsp" %>
        <main class="event">

            <section class="container" id="event">
                <section>
                    <h1>
                        L'évenement : ${requestScope.eventOrm.event.name}
                        <c:choose>
                            <c:when test="${requestScope.eventOrm.event.organizer == false}" >
                                ( Evenement Passé )
                            </c:when>
                        </c:choose>
                    </h1>


                    <h2> Date: ${requestScope.eventOrm.event.date_event}</h2>
                    <p>
                        ${requestScope.eventOrm.event.description}
                    </p>
                </section>

                <section id="table_event">
                    <h3>Participants : ${DaoFactory.getAttendanceDao().GetTotalUserByEvent(requestScope.eventOrm.event.id)}</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Nom</th>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.id == 1}">
                                        <th>contact</th>
                                        </c:when>
                                    </c:choose>
                                <th>Nombre de personnes</th>
                                <th>Entrées</th>
                                <th>Parts</th>
                                <th>Plat</th>
                                <th>Parts</th>
                                <th>Dessert</th>
                                <th>Parts</th>
                                <th>Boisson</th>
                                <th>Parts</th>
                                <th>Commentaire</th>
                                    <c:if test="${!empty sessionScope.user.id}">
                                    <th>Action</th>                               
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${attendees}" var="attendanceOrm">
                                <tr <c:if test="${sessionScope.user.pseudo == attendanceOrm.user.pseudo}">class='valide'</c:if>>
                                    <td>${attendanceOrm.user.pseudo}</td>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.id == 1}">
                                            <td>${attendanceOrm.user.contact}</td>
                                        </c:when>
                                    </c:choose>
                                    <td>${attendanceOrm.attendance.guests}</td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetDescriptionByBothIDs(attendanceOrm.attendance.id ,1 )}
                                    </td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetQuantityByBothIDs(attendanceOrm.attendance.id ,1 )}
                                    </td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetDescriptionByBothIDs(attendanceOrm.attendance.id ,2 )}
                                    </td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetQuantityByBothIDs(attendanceOrm.attendance.id ,2 )}
                                    </td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetDescriptionByBothIDs(attendanceOrm.attendance.id ,3 )}
                                    </td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetQuantityByBothIDs(attendanceOrm.attendance.id ,3 )}
                                    </td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetDescriptionByBothIDs(attendanceOrm.attendance.id ,4 )}
                                    </td>
                                    <td>
                                        ${DaoFactory.getContainDao().GetQuantityByBothIDs(attendanceOrm.attendance.id , 4 )}
                                    </td>
                                    <td>${attendanceOrm.attendance.comment}</td>
                                    <c:if test="${!empty sessionScope.user.id}">
                                        <td>-</td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            <tr>
                                <c:choose>
                                    <c:when test="${!empty sessionScope.user.id && sessionScope.user.id != 1}">
                                        <c:choose>
                                            <c:when test="${requestScope.eventOrm.event.organizer == true}" >
                                        <form method="post" action="<c:url value='/addAttendance'/>">
                                            <input type="hidden" name="id_event" value="${requestScope.eventOrm.event.id}" />
                                            <input type="hidden" name="id_attendance" value="${requestScope.attendanceOrm.attendance.id_attendance}" />
                                            <td>${sessionScope.user.pseudo}</td>
                                            <td>
                                                <input class="small" type="number"  name="guests"  value="<c:out value="1"/>" min="0" max="50">
                                                <span>${errors.guests}</span>
                                            </td> 
                                            <td><input type="text" name="des_entree"/></td>
                                            <td><input class="small" type="number"  name="entree" value="<c:out value="0"/>" min="0" max="50" ></td>
                                            <td><input type="text" name="des_plat"/></td>
                                            <td><input class="small" type="number"  name="plat" value="<c:out value="0"/>" min="0" max="50"></td>
                                            <td><input type="text" name="des_dessert"/></td>
                                            <td><input class="small" type="number"  name="dessert" value="<c:out value="0"/>" min="0" max="50"></td>
                                            <td><input type="text" name="des_boisson"/></td>
                                            <td><input class="small" type="number"  name="boisson" value="<c:out value="0"/>" min="0" max="50"></td>
                                            <td><input type="text" name="comment"/></td>
                                            <td><input class='boutton' type="submit" value="s'inscrire"/></td>
                                        </form>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
                        </tr>
                        <tr class="need">
                            <th>Il manquera</th>
                                <c:choose>
                                    <c:when test="${sessionScope.user.id == 1}">
                                    <th>-</th>
                                    </c:when>
                                </c:choose>
                            <th></th>
                            <th>Entrées</th>
                            <th>${DaoFactory.getContainDao().GetTotalMealByID(1) - DaoFactory.getAttendanceDao().GetTotalUserByEvent(requestScope.eventOrm.event.id)}</th>
                            <th>Plats</th>
                            <th>${DaoFactory.getContainDao().GetTotalMealByID(2) - DaoFactory.getAttendanceDao().GetTotalUserByEvent(requestScope.eventOrm.event.id)}</th>
                            <th>Desserts</th>
                            <th>${DaoFactory.getContainDao().GetTotalMealByID(3) - DaoFactory.getAttendanceDao().GetTotalUserByEvent(requestScope.eventOrm.event.id)}</th>
                            <th>Boissons</th>
                            <th>${DaoFactory.getContainDao().GetTotalMealByID(4) - DaoFactory.getAttendanceDao().GetTotalUserByEvent(requestScope.eventOrm.event.id)}</th>
                            <th></th>
                                <c:if test="${!empty sessionScope.user.id}">
                                <th></th>
                                </c:if>
                        </tr>  
                        </tbody>
                    </table>
                    <table>

                    </table>
                </section>
            </section>            


            <%@include file="/WEB-INF/jspf/Footer.jsp" %>
    </body>
</html>
