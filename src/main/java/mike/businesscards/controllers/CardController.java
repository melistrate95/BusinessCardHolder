package mike.businesscards.controllers;

import mike.businesscards.model.Card;
import mike.businesscards.model.Contact;
import mike.businesscards.model.Jobs;
import mike.businesscards.model.User;
import mike.businesscards.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CardController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private CardService cardService;

    @Autowired
    private JobsService jobsService;

    public CardController() {}

    @RequestMapping(value = "/id{id}/add_card", method = RequestMethod.GET)
    public String goToPageCreateCard(@PathVariable Integer id, ModelMap model) {
        User onlineUser = this.userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        model.addAttribute("user", onlineUser);
        ArrayList<Contact> contacts = (ArrayList<Contact>) contactService.listUserContact(id);
        model.addAttribute("contacts", contacts);
        ArrayList<Jobs> jobs = (ArrayList<Jobs>) jobsService.listUserJobs(id);
        model.addAttribute("jobs", jobs);
        return "add_card_page";
    }

    @RequestMapping(value = "/id{id}/cards/{idCard}", method = RequestMethod.GET)
    public String showCard(@PathVariable Integer id, @PathVariable Integer idCard, ModelMap model) {
        User onlineUser = this.userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        model.addAttribute("user", onlineUser);
        Card card = cardService.getCardById(idCard);
        model.addAttribute("card", card);
        return "show_card_page";
    }

    @RequestMapping(value = "/id{id}/cards/{idCard}/json", method = RequestMethod.GET)
    public ResponseEntity<String> getCardJson(@PathVariable Integer id, @PathVariable Integer idCard, ModelMap model) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<String>(cardService.getCardByIdJson(idCard), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveCard(@RequestBody String json) {
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("id", cardService.create(json));
        return response;
    }

    @RequestMapping(value = "/saveCard/saveCardImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveCardImage(
        @RequestParam(value = "idCard") Integer idCard,
        @RequestParam(value = "image", defaultValue = "") String image) {
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("url", cardService.saveCardImage(idCard, image));
        return response;
    }
}
