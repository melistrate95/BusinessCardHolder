package mike.businesscards.controllers;

import mike.businesscards.dao.UserRepository;
import mike.businesscards.model.User;
import mike.businesscards.model.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mike on 11/05/2015.
 */

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initRegistrationPage(ModelMap model) {
        User userModel = new User();
        model.put("userRegistration", userModel);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String makeRegistration(ModelMap model, User user, BindingResult result) {
        if (result.hasErrors()) {
            model.put("userRegistration", user);
            return "registration";
        }
        if (!this.userRepository.findUserByEmail(user.getMail())) {
            appointRole(user);
            this.userRepository.addUser(user);
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
