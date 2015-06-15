package mike.businesscards.controllers;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import mike.businesscards.model.enums.UserRoleEnum;
import mike.businesscards.service.EmailSender;
import mike.businesscards.service.UserService;
import mike.businesscards.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Mike on 11/05/2015.
 */

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initRegistrationPage(ModelMap model) {
        User userModel = new User();
        model.put("userRegistration", userModel);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String makeRegistration(ModelMap model, @Valid User user, Errors errors, BindingResult result) {
        if (errors.hasErrors()) {
            return "registration";
        }
        if (result.hasErrors()) {
            model.put("userRegistration", user);
            return "registration";
        }
        if (!this.userService.findUserByEmail(user.getMail())) {
            appointRole(user);
            user.setIsConfirm(0);
            this.userService.addUser(user);
            EmailSender sslSender = new EmailSender("youngcompanysupp@gmail.com", "tenmillion");
            sslSender.send("Welcome to Young", "Your account has been created — now it will be easier than ever to share and connect with your friends." +
                    "\nhttp://localhost:8080/confirm/gencode" + user.getId(), "youngcompanysupp@gmail.com", user.getMail());
        }
        return "login";
    }

    private void appointRole(User user) {
        if (user.getName().equals("Admin")) {
            user.setRole(UserRoleEnum.ROLE_ADMIN.toString());
        }
        else {
            user.setRole(UserRoleEnum.ROLE_USER.toString());
        }
    }
}
