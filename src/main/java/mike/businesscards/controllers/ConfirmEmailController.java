package mike.businesscards.controllers;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import mike.businesscards.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mike on 27/05/2015.
 */

@Controller
@RequestMapping(value = "/confirm/gencode{id}")
public class ConfirmEmailController {

    private UserDaoImpl userDaoImpl;

    @Autowired
    public ConfirmEmailController(UserDaoImpl userDaoImpl){
        this.userDaoImpl = userDaoImpl;
    }

    @RequestMapping()
    public String makeConfirm(@PathVariable Integer id, ModelMap model) {
        User thisUser = this.userDaoImpl.getUserById(id);
        thisUser.setIsConfirm(1);
        this.userDaoImpl.addUser(thisUser);
        return "redirect:/login";
    }
}
