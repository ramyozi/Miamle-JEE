/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.Forms;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stag
 */
public abstract class FormChecker<T> implements Checkable{
    protected final HttpServletRequest request;
    protected final HashMap<String, String> errors;
    protected T bean;

    public FormChecker(HttpServletRequest request) {
        this.request = request;
        errors = new HashMap<>();
    }
    
    public HashMap<String, String> getErrors() {
        return errors;
    }

    private String getParameter(String field) {
        if (request.getParameter(field) == null || request.getParameter(field).trim().length() == 0) {
            return null;
        } else {
            return request.getParameter(field).trim();
        }
    }

    public T getBean() {
        return bean;
    }


    
}
