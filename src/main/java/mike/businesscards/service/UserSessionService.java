package mike.businesscards.service;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;

/**
 * Created by Mike on 22/05/2015.
 */
public class UserSessionService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    public void addMailAttribute(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            model.addAttribute("email", email);
        }
    }
}
