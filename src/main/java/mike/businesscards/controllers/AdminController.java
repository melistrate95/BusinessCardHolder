package mike.businesscards.controllers;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import mike.businesscards.service.UserService;
import mike.businesscards.service.UserServiceImpl;
import mike.businesscards.service.UserSessionService;
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

import java.util.ArrayList;

/**
 * Created by Mike on 13/05/2015.
 */

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/manage")
    public String getManagePage(ModelMap model) {
        (new UserSessionService()).addMailAttribute(model);
        ArrayList<User> userArrayList = (ArrayList<User>) this.userService.listAll();
        model.addAttribute("array", userArrayList);
        return "manage";
    }

    @RequestMapping(value = "/manage/delete/id{id}", method = RequestMethod.GET)
    public String deleteUserPage(@PathVariable Integer id, ModelMap model) {
        this.userService.removeUser(id);
        return "redirect:/manage";
    }
}
