/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.Forms;

import g3.Model.Event;
import g3.Model.User;
import g3.dao.DaoFactory;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stag
// */
public class CreateEventFormChecker extends FormChecker<Event> {

    private static final String NAME_FIELD = "name";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String DATE_FIELD = "date";

    public CreateEventFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public boolean checkForm() {

        String name = request.getParameter(NAME_FIELD);
        String description = request.getParameter(DESCRIPTION_FIELD);
        Integer id_creator = ((User) (request.getSession().getAttribute("user"))).getId_user();
        String dateString = request.getParameter(DATE_FIELD);

        // créer un objet Event
        bean = new Event(name, null, description, true);

        // valider le nom
        if (name == null || name.isEmpty()) {
            errors.put(NAME_FIELD, "Le nom de l'événement est obligatoire");
        } else if (name.length() > 50) {
            errors.put(NAME_FIELD, "Le nom de l'événement ne doit pas dépasser 50 caractères");
        }

        // valider la description
        if (description == null || description.isEmpty()){
            errors.put(DESCRIPTION_FIELD, "La description de l'événement est obligatoire");
        }else if (description.length() > 1000) {
            errors.put(DESCRIPTION_FIELD, "La description de l'événement ne doit pas dépasser 1000 caractères");
        }

        if (dateString == null || dateString.isEmpty()) {
            errors.put(DATE_FIELD, "La date de l'événement est obligatoire");
        } else {
            Date date = Date.valueOf(dateString);
            LocalDate today = LocalDate.now();
            LocalDate eventDate = date.toLocalDate();
            bean.setDate_event(date);
            if (eventDate.isBefore(today.plusDays(1))) {
                errors.put(DATE_FIELD, "La date de l'événement doit être ultérieure à aujourd'hui");
            }
        }

        if (errors.isEmpty()) {
            DaoFactory.getEventDao().insert(bean);
        }

        return errors.isEmpty();
    }

    /**
     * Verifie que la date saisie est valide.
     *
     * @param int day,
     * @param int month,
     * @param int year.
     * @return boolean isValid.
     */
    public boolean isValidDate(int day, int month, int year) {
        boolean isValid = true;
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            isValid = false;
        }
        return isValid;
    }
}
