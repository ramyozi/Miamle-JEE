/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.Forms;

/**
 *
 * @author stag
 */
import com.google.i18n.phonenumbers.NumberParseException;
import g3.Model.User;
import g3.dao.DaoFactory;
import g3.helpers.PasswordAuthentification;
import javax.servlet.http.HttpServletRequest;
import javax.mail.internet.InternetAddress;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class SigninFormChecker extends FormChecker<User> {

    private static final String CONTACT_FIELD = "contact";
    private static final String PWD_FIELD = "password";
    private static final String CONFIRM_PWD_FIELD = "confirmPassword";
    private static final String PSEUDO_FIELD = "pseudo";

    public SigninFormChecker(HttpServletRequest request) {
        super(request);
    }

    
    /**
     * verifie que les informations du formulaire de connection soient sous le bon format.
     *
     */
    @Override
    public boolean checkForm() {

        String contact = request.getParameter(CONTACT_FIELD);
        String password = request.getParameter(PWD_FIELD);
        String confirmPassword = request.getParameter(CONFIRM_PWD_FIELD);
        String pseudo = request.getParameter(PSEUDO_FIELD);
        bean = new User(pseudo, contact, password);

        if (contact == null || contact.trim().isEmpty()) {
            errors.put(CONTACT_FIELD, "Le contact est obligatoire.");
        } else {
            // Vérifiez si le contact est un e-mail
            if (contact.contains("@")) {
                // Vérifiez si l'adresse e-mail est valide
                if (!isValidEmail(contact)) {
                    errors.put(CONTACT_FIELD, "L'adresse e-mail n'est pas valide. Veuillez entrer une adresse e-mail valide.");
                }
            } else {
                // Vérifiez si le contact est un num de téléphone français
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                try {
                    PhoneNumber phoneNumber = phoneUtil.parse(contact, "FR"); // "FR" for France
                    if (!phoneUtil.isValidNumber(phoneNumber)) {
                        errors.put(CONTACT_FIELD, "Le contact n'est pas un numéro de téléphone valide en France.");
                    }
                } catch (NumberParseException e) {
                    errors.put(CONTACT_FIELD, "Le contact n'est pas un numéro de téléphone valide.");
                }
            }
        }

        if (password == null || password.length() < 6) {
            password = ""; // nécessaire pour simplifier la tentative de hashage
            errors.put(PWD_FIELD, "Doit contenir au moins 6 caractères");
        } else {
            if (confirmPassword == null || !password.equals(confirmPassword)) {
                errors.put(CONFIRM_PWD_FIELD, "Doit être identique au mot de passe");
            }
        }

        if (pseudo == null || pseudo.trim().isEmpty()) {
            errors.put(PSEUDO_FIELD, "Le pseudo est obligatoire.");
        } else if (pseudo.length() < 3) {
            errors.put(PSEUDO_FIELD, "Le pseudo doit contenir au moins 3 caractères.");
        } else {
            // Check if the pseudo already exists in the database
            User existingUser = DaoFactory.getUserDao().getByName(pseudo);
            if (existingUser != null) {
                errors.put(PSEUDO_FIELD, "Ce pseudo est déjà utilisé.");
            }
        }

        if (errors.isEmpty()) {
            PasswordAuthentification pa = new PasswordAuthentification();
            bean.setPassword(pa.hash(password.toCharArray()));
            DaoFactory.getUserDao().insert(bean);
            if (bean.getId() == null) {
                errors.put(PSEUDO_FIELD, "Ce pseudo est déja utilisé !!!");
            }
        }

        return errors.isEmpty();

    }

    /**
     * verifie que les adresses e-mail soient sous le bon format.
     * 
     *@param String email.
     *@return boolean true si valide, sinon false.
     */
    public boolean isValidEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

}
