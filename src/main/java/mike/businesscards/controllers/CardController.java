package mike.businesscards.controllers;

import mike.businesscards.dao.CardDaoImpl;
import mike.businesscards.dao.ContactDaoImpl;
import mike.businesscards.dao.JobsDaoImpl;
import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.Card;
import mike.businesscards.model.Contact;
import mike.businesscards.model.Jobs;
import mike.businesscards.model.User;
import mike.businesscards.service.UserSessionService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Mike on 28/05/2015.
 */

@Controller
public class CardController {

    private UserDaoImpl userDaoImpl;
    private ContactDaoImpl contactDaoImpl;
    private CardDaoImpl cardDaoImpl;
    private JobsDaoImpl jobsDaoImpl;

    @Autowired
    public CardController(UserDaoImpl userDaoImpl, ContactDaoImpl contactDaoImpl,
        CardDaoImpl cardDaoImpl, JobsDaoImpl jobsDaoImpl){
        this.userDaoImpl = userDaoImpl;
        this.contactDaoImpl = contactDaoImpl;
        this.cardDaoImpl = cardDaoImpl;
        this.jobsDaoImpl = jobsDaoImpl;
    }

    @RequestMapping(value = "/id{id}/add_card", method = RequestMethod.GET)
    public String goToPageCreateCard(@PathVariable Integer id, ModelMap model) {
        User onlineUser = this.userDaoImpl.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        model.addAttribute("user", onlineUser);
        ArrayList<Contact> contacts = (ArrayList<Contact>) this.contactDaoImpl.listUserContact(id);
        model.addAttribute("contacts", contacts);
        ArrayList<Jobs> jobs = (ArrayList<Jobs>) this.jobsDaoImpl.listUserJobs(id);
        model.addAttribute("jobs", jobs);
        return "add_card_page";
    }

    @RequestMapping(value = "/id{id}/cards/{nameCard}", method = RequestMethod.GET)
    public String showCard(@PathVariable Integer id, @PathVariable String nameCard, ModelMap model) {
        User onlineUser = this.userDaoImpl.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        model.addAttribute("user", onlineUser);
        Card card = this.cardDaoImpl.getCardByName(nameCard);
        model.addAttribute("card", card);
        return "show_card_page";
    }

    @RequestMapping(value = "/id{id}/cards/{nameCard}/json", method = RequestMethod.GET)
    public ResponseEntity<String> getCardJson(@PathVariable Integer id, @PathVariable String nameCard, ModelMap model) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Card card = this.cardDaoImpl.getCardByName(nameCard);
        return new ResponseEntity<String>(card.getJson(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCard", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCard(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            User user = this.userDaoImpl.getUserByEmail(userDetails.getUsername());
            try {
                JSONObject object = (JSONObject) (new JSONParser()).parse(json);
                String nameCard = (String) object.get("name");
                if ( !nameCard.equals("")) {
                    Card card = new Card();
                    card.setUser(user);
                    card.setName(nameCard);
                    card.setUrl("/id" + user.getId() + "/cards/"+ nameCard);
                    card.setJson(json);
                    this.cardDaoImpl.addCard(card, user.getId());
                    return new ResponseEntity<String>(json, headers, HttpStatus.CREATED);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<String>(json, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
