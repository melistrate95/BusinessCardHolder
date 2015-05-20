package mike.businesscards.controllers;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mike on 13/05/2015.
 */

@Controller
public class PersonalController {

    private UserDaoImpl userDaoImpl;

    @Autowired
    public PersonalController(UserDaoImpl userDaoImpl){
        this.userDaoImpl = userDaoImpl;
    }

    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String getPersonal(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User nowUser = this.userDaoImpl.getUserByEmail(email);
            model.addAttribute("id", nowUser.getId());
        }
        return "redirect:/id{id}";
    }

    @RequestMapping(value = "/id{id}")
    public String goToAccount(@PathVariable Integer id, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User nowUser = this.userDaoImpl.getUserByEmail(email);
            model.addAttribute("email", nowUser.getMail());
        }
        User thisUser = this.userDaoImpl.getUserById(id);
        model.addAttribute("name", thisUser.getName());
        model.addAttribute("this_email", thisUser.getMail());
        return "personal";
    }

}
