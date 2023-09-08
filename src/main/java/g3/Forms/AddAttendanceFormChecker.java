/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.Forms;

import g3.Model.Attendance;
import g3.Model.Contain;
import g3.Model.Event;
import g3.Model.User;
import g3.dao.DaoFactory;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stag
 */
public class AddAttendanceFormChecker extends FormChecker<Attendance> {

    private static final String GUESTS_FIELD = "guests";
    private static final String STARTERS_FIELD = "entree";
    private static final String DES_STARTERS_FIELD = "des_entree";
    private static final String MAIN_COURSES_FIELD = "plat";
    private static final String DES_MAIN_COURSES_FIELD = "des_plat";
    private static final String DESSERTS_FIELD = "dessert";
    private static final String DES_DESSERTS_FIELD = "des_dessert";
    private static final String DRINKS_FIELD = "boisson";
    private static final String DES_DRINKS_FIELD = "des_boisson";
    private static final String COMMENT_FIELD = "comment";

    /**
     * Constructeur.
     *
     * @param request.
     */
    public AddAttendanceFormChecker(HttpServletRequest request) {
        super(request);
    }

    /**
     * verifie que les informations du formulaire d'attendance soient sous le bon format.
     *
     */
    @Override
    public boolean checkForm() {
        Integer guests = Integer.parseInt(request.getParameter(GUESTS_FIELD));
        int nb_entree = Integer.parseInt(request.getParameter(STARTERS_FIELD));
        String des_entree = request.getParameter(DES_STARTERS_FIELD);
        int nb_plat = Integer.parseInt(request.getParameter(MAIN_COURSES_FIELD));
        String des_plat = request.getParameter(DES_MAIN_COURSES_FIELD);
        int nb_dessert = Integer.parseInt(request.getParameter(DESSERTS_FIELD));
        String des_dessert = request.getParameter(DES_DESSERTS_FIELD);
        int nb_boisson = Integer.parseInt(request.getParameter(DRINKS_FIELD));
        String des_boisson = request.getParameter(DES_DRINKS_FIELD);
        String comment = request.getParameter(COMMENT_FIELD);

        Integer id_user = ((User) (request.getSession().getAttribute("user"))).getId_user();
        Integer id_event = ((Event) (request.getSession().getAttribute("event"))).getId_event();

        if (comment.length() > 50) {
            errors.put(COMMENT_FIELD, "ne doit pas dépasser 50 caractères");
        }
        if (guests <= 0) {
            errors.put(GUESTS_FIELD, "doit être un nombre entier positif");
        }
        if (nb_entree < 0) {
            errors.put(STARTERS_FIELD, "doit être un nombre entier positif");
        }
        if (nb_plat < 0) {
            errors.put(MAIN_COURSES_FIELD, "doit être un nombre entier positif");
        }
        if (nb_dessert < 0) {
            errors.put(DESSERTS_FIELD, "doit être un nombre entier positif");
        }
        if (nb_boisson < 0) {
            errors.put(DRINKS_FIELD, "doit être un nombre entier positif");
        }
        if (des_entree.length() > 50) {
            errors.put(DES_STARTERS_FIELD, "ne doit pas dépasser 50 caractères");
        }
        if (des_plat.length() > 50) {
            errors.put(DES_MAIN_COURSES_FIELD, "ne doit pas dépasser 50 caractères");
        }
        if (des_dessert.length() > 50) {
            errors.put(DES_DESSERTS_FIELD, "ne doit pas dépasser 50 caractères");
        }
        if (des_boisson.length() > 50) {
            errors.put(DES_DRINKS_FIELD, "ne doit pas dépasser 50 caractères");
        }

        bean = new Attendance(id_user, id_event, guests, comment);
        if (errors.isEmpty()) {
            DaoFactory.getAttendanceDao().insert(bean);
            Integer id_attendance = bean.getId_attendance();
            Contain entrees = new Contain(id_attendance,1, nb_entree, des_entree);
            Contain plats = new Contain(id_attendance,2, nb_plat, des_plat);
            Contain desserts = new Contain(id_attendance,3, nb_dessert, des_dessert);
            Contain boissons = new Contain(id_attendance,4, nb_boisson, des_boisson);

            DaoFactory.getContainDao().insert(entrees);
            DaoFactory.getContainDao().insert(plats);
            DaoFactory.getContainDao().insert(desserts);
            DaoFactory.getContainDao().insert(boissons);
            
        }
        return errors.isEmpty();
    }
}
