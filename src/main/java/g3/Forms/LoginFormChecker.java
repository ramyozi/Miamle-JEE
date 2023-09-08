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
import g3.Model.User;
import g3.dao.DaoFactory;
import g3.helpers.PasswordAuthentification;
import javax.servlet.http.HttpServletRequest;

public class LoginFormChecker extends FormChecker<User> {

    private static final String PSEUDO_FIELD = "pseudo";
    private static final String PWD_FIELD = "pwd";

    public LoginFormChecker(HttpServletRequest request) {
        super(request);
    }

    /**
     * verifie que les informations du formulaire de login soient sous le bon format.
     *
     */
    @Override
    public boolean checkForm() {
        String pseudo = request.getParameter(PSEUDO_FIELD);
        String pwd = request.getParameter(PWD_FIELD);

        bean = new User(23, null, pseudo, pwd);

        if (pseudo == null || pseudo.trim().length() < 3) {
            errors.put(PSEUDO_FIELD, "Doit faire au moins 3 caractères");
        }

        if (pwd.length() < 6) {
            errors.put(PWD_FIELD, "Doit faire au moins 6 caractères");
        }

        if (errors.isEmpty()) {
            User user = DaoFactory.getUserDao().getByName(pseudo);
            // Vérifications de l'existence de l'utilisateur
            PasswordAuthentification pa = new PasswordAuthentification();
            try {
                // Attention, la méthode authenticate lance des exceptions silencieuses...
                if (user == null || !pa.authenticate(pwd.toCharArray(), user.getPassword())) {
                    errors.put(PSEUDO_FIELD, "Utilisateur ou mot de passe erroné");
                } else {
                    // L'utilisateur est le bon
                    bean = user;
                }
            } catch (IllegalArgumentException ex) {
                errors.put(PSEUDO_FIELD, "Utilisateur ou mot de passe erroné");

            }
        }

        return errors.isEmpty();

    }
}
