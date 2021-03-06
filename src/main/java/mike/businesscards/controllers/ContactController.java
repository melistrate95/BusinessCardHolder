package mike.businesscards.controllers;

import mike.businesscards.dao.ContactDaoImpl;
import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.Contact;
import mike.businesscards.model.User;
import mike.businesscards.service.ContactService;
import mike.businesscards.service.UserService;
import mike.businesscards.service.UserServiceImpl;
import mike.businesscards.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Mike on 29/05/2015.
 */

@Controller
public class ContactController {

    private UserService userService;
    private ContactService contactService;

    @Autowired
    public ContactController(UserService userService, ContactService contactService){
        this.contactService = contactService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add_contact")
    public String initAddContactPage(ModelMap model) {
        Contact contactModel = new Contact();
        model.put("addContact", contactModel);
        (new UserSessionService()).addMailAttribute(model);
        return "add_contact_page";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_contact")
    public String addContact(ModelMap model, @Valid Contact contact, Errors errors, BindingResult result) {
        if (errors.hasErrors()) {
            return "add_contact_page";
        }
        if (result.hasErrors()) {
            model.put("addContact", contact);
            return "add_contact_page";
        }
        User onlineUser = this.userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        this.contactService.addContact(contact, onlineUser.getId());
        return "redirect:/personal";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit_contact/{id}")
    public String initEditContactPage(@PathVariable Integer id,ModelMap model) {
        User onlineUser = this.userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        Contact contact = contactService.getContact(onlineUser.getId(), id);
        model.addAttribute("contact", contact);
        return "contact_edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit_contact")
    public String editContact(ModelMap model, Contact contact) {
        User onlineUser = this.userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        this.contactService.addContact(contact, onlineUser.getId());
        return "redirect:/personal";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete_contact/{id}")
    public String deleteContact(@PathVariable Integer id, ModelMap model) {
        contactService.removeContact(id);
        return "redirect:/personal";
    }
}
