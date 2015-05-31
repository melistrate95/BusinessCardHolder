package mike.businesscards.controllers;

import mike.businesscards.dao.CardDaoImpl;
import mike.businesscards.dao.ContactDaoImpl;
import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.Card;
import mike.businesscards.model.Contact;
import mike.businesscards.model.User;
import mike.businesscards.model.enums.UserRoleEnum;
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
import java.util.List;

/**
 * Created by Mike on 13/05/2015.
 */

@Controller
public class PersonalController {

    private UserDaoImpl userDaoImpl;
    private ContactDaoImpl contactDaoImpl;
    private CardDaoImpl cardDaoImpl;

    @Autowired
    public PersonalController(UserDaoImpl userDaoImpl, ContactDaoImpl contactDaoImpl, CardDaoImpl cardDaoImpl){
        this.userDaoImpl = userDaoImpl;
        this.contactDaoImpl = contactDaoImpl;
        this.cardDaoImpl = cardDaoImpl;
    }

    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String getPersonal(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User nowUser = this.userDaoImpl.getUserByEmail(email);
            if (nowUser.getIsConfirm() == 0) {
                auth.setAuthenticated(false);
            }
            model.addAttribute("id", nowUser.getId());
        }
        return "redirect:/id{id}";
    }

    @RequestMapping(value = "/id{id}")
    public String goToAccount(@PathVariable Integer id, ModelMap model) {
        if (this.userDaoImpl.findUserById(id)) {
            User thisUser = this.userDaoImpl.getUserById(id);
            model.addAttribute("user", thisUser);
            User onlineUser = this.userDaoImpl.getUserByEmail((new UserSessionService()).addMailAttribute(model));
            model.addAttribute("online_user", onlineUser);
            ArrayList<Contact> contacts = (ArrayList<Contact>) this.contactDaoImpl.listUserContact(thisUser.getId());
            model.addAttribute("contacts", contacts);
            ArrayList<Card> cards = (ArrayList<Card>) this.cardDaoImpl.listUserCard(thisUser.getId());
            model.addAttribute("cards", cards);
            return "personal";
        }
        (new UserSessionService()).addMailAttribute(model);
        return "page_not_found";
    }

    @RequestMapping(value = "/edit/id{id}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable Integer id, ModelMap model) {
        User user = this.userDaoImpl.getUserById(id);
        String onlineUserEmail = (new UserSessionService()).addMailAttribute(model);
        if (!onlineUserEmail.equals(user.getMail()) && !this.userDaoImpl.getUserByEmail(onlineUserEmail).getRole().equals(UserRoleEnum.ROLE_ADMIN.toString())) {
            model.addAttribute("id", id);
            return "redirect:/id{id}";
        }
        model.addAttribute("user", user);
        model.addAttribute("online_user", this.userDaoImpl.getUserByEmail(onlineUserEmail));
        return "user_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUserChange(ModelMap model, User user) {
        User fullUser = this.userDaoImpl.getUserByEmail(user.getMail());
        fullUser.setPassword(user.getPassword());
        fullUser.setName(user.getName());
        fullUser.setMail(user.getMail());
        if (fullUser.getRole().equals(UserRoleEnum.ROLE_ADMIN)) {
            fullUser.setRole(user.getRole());
        }
        this.userDaoImpl.addUser(fullUser);
        model.addAttribute("id", fullUser.getId());
        return "redirect:/personal";
    }

}
