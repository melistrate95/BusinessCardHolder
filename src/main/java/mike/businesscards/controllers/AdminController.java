package mike.businesscards.controllers;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
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

    private UserDaoImpl userDaoImpl;

    @Autowired
    public AdminController(UserDaoImpl userDaoImpl){
        this.userDaoImpl = userDaoImpl;
    }

    @RequestMapping(value = "/manage")
    public String getManagePage(ModelMap model) {
        (new UserSessionService()).addMailAttribute(model);
        ArrayList<User> userArrayList = (ArrayList<User>) this.userDaoImpl.listAll();
        model.addAttribute("array", userArrayList);
        return "manage";
    }

    @RequestMapping(value = "/manage/delete/id{id}", method = RequestMethod.GET)
    public String deleteUserPage(@PathVariable Integer id, ModelMap model) {
        this.userDaoImpl.removeUser(id);
        return "redirect:/manage";
    }

    @RequestMapping(value = "/manage/edit/id{id}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable Integer id, ModelMap model) {
        (new UserSessionService()).addMailAttribute(model);
        User user = this.userDaoImpl.getUserById(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @RequestMapping(value = "/manage/save", method = RequestMethod.POST)
    public String saveUserChange(ModelMap model, User user) {
        User fullUser = this.userDaoImpl.getUserByEmail(user.getMail());
        fullUser.setPassword(user.getPassword());
        fullUser.setName(user.getName());
        fullUser.setMail(user.getMail());
        fullUser.setRole(user.getRole());
        this.userDaoImpl.addUser(fullUser);
        return "redirect:/manage";
    }
}
