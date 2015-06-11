package mike.businesscards.service;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class UserSessionService {

    public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = "";
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            email = userDetails.getUsername();
        }
        return email;
    }

    public String addMailAttribute(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            model.addAttribute("email", email);
            return email;
        }
        return null;
    }
}
