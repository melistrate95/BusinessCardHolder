package mike.businesscards.controllers;

import mike.businesscards.dao.CardDaoImpl;
import mike.businesscards.dao.ContactDaoImpl;
import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import mike.businesscards.model.enums.UserRoleEnum;
import mike.businesscards.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mike on 28/05/2015.
 */

@Controller
public class CardController {

    private UserDaoImpl userDaoImpl;
    private ContactDaoImpl contactDaoImpl;
    private CardDaoImpl cardDaoImpl;

    @Autowired
    public CardController(UserDaoImpl userDaoImpl, ContactDaoImpl contactDaoImpl, CardDaoImpl cardDaoImpl){
        this.userDaoImpl = userDaoImpl;
        this.contactDaoImpl = contactDaoImpl;
        this.cardDaoImpl = cardDaoImpl;
    }

    @RequestMapping(value = "/add_card", method = RequestMethod.GET)
    public String goToPageCreateCard(ModelMap model) {
        User onlineUser = this.userDaoImpl.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        model.addAttribute("user", onlineUser);
        (new UserSessionService()).addMailAttribute(model);
        return "add_card_page";
    }
}
