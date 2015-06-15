package mike.businesscards.controllers;

import mike.businesscards.model.User;
import mike.businesscards.service.UserService;
import mike.businesscards.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User onlineUser = userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        ArrayList<User> userArrayList = (ArrayList<User>) this.userService.listAll();
        model.addAttribute("array", userArrayList);
        model.addAttribute("online_user", onlineUser);
        return "manage";
    }

    @RequestMapping(value = "/manage/delete/id{id}", method = RequestMethod.GET)
    public String deleteUserPage(@PathVariable Integer id, ModelMap model) {
        User onlineUser = userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        if (onlineUser.getId() != id) {
            this.userService.removeUser(id);
        }
        return "redirect:/manage";
    }
}
