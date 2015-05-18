package mike.businesscards.controllers;

import mike.businesscards.dao.UserRepository;
import mike.businesscards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/manage")
    public String getManagePage(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        model.addAttribute("email", email);
        ArrayList<User> userArrayList = (ArrayList<User>) this.userRepository.listAll();
        model.addAttribute("array", userArrayList);
        return "manage";
    }

    @RequestMapping(value = "/manage/delete/id{id}", method = RequestMethod.GET)
    public String deleteUserPage(@PathVariable Integer id, ModelMap model) {
        this.userRepository.removeUser(id);
        return "redirect:/manage";
    }
}
