package mike.businesscards.controllers;

import mike.businesscards.dao.ContactDaoImpl;
import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.Contact;
import mike.businesscards.model.User;
import mike.businesscards.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mike on 29/05/2015.
 */

@Controller
public class ContactController {

    private UserDaoImpl userDaoImpl;
    private ContactDaoImpl contactDaoImpl;

    @Autowired
    public ContactController(UserDaoImpl userDaoImpl, ContactDaoImpl contactDaoImpl){
        this.contactDaoImpl = contactDaoImpl;
        this.userDaoImpl = userDaoImpl;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add_contact")
    public String initAddContactPage(ModelMap model) {
        Contact contactModel = new Contact();
        model.put("addContact", contactModel);
        (new UserSessionService()).addMailAttribute(model);
        return "add_contact_page";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_contact")
    public String addContact(ModelMap model, Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            model.put("addContact", contact);
            return "add_contact_page";
        }
        User onlineUser = this.userDaoImpl.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        this.contactDaoImpl.addContact(contact, onlineUser.getId());
        return "redirect:/personal";
    }
}
