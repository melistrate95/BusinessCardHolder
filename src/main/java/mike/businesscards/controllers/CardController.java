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
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/id{id}/cards/{idCard}/show", method = RequestMethod.GET)
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

    @RequestMapping(value = "/id{id}/saveCard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveCard(@PathVariable Integer id, @RequestBody String json) {
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("id", cardService.create(json));
        return response;
    }
    @RequestMapping(value = "/id{id}/cards/{idCard}/saveCard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCard(@PathVariable Integer id,
        @PathVariable Integer idCard, @RequestBody String json) {
        Map<String,Object> response = new HashMap<String, Object>();
        response.put("id", cardService.update(idCard, json));
        return response;
    }

    @RequestMapping(value = "/id{id}/saveCard/saveCardImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveCardImage(@PathVariable Integer id,
        @RequestParam(value = "idCard") Integer idCard,
        @RequestParam(value = "image", defaultValue = "") String image) {
        Map<String,Object> response = new HashMap<String, Object>();
        Card card = cardService.saveCardImage(idCard, image);
        response.put("urlImage", card.getUrl());
        response.put("url", "http://localhost:8080/id" + card.getUser().getId() + "/cards/" + card.getId() + "/show");
        return response;
    }
    @RequestMapping(value = "/id{id}/cards/{idCardPath}/saveCard/saveCardImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCardImage(@PathVariable Integer id, @PathVariable Integer idCardPath,
        @RequestParam(value = "idCard") Integer idCard,
        @RequestParam(value = "image", defaultValue = "") String image) {
        Map<String,Object> response = new HashMap<String, Object>();
        Card card = cardService.updateCardImage(idCard, image);
        response.put("urlImage", card.getUrl());
        response.put("url", "http://localhost:8080/id" + card.getUser().getId() + "/cards/" + card.getId() + "/show");
        return response;
    }

    @RequestMapping(value = "/cards/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> removeCard(@RequestParam(value = "idCard") Integer idCard) {
        Map<String,Object> response = new HashMap<String, Object>();
        cardService.removeCard(idCard);
        response.put("id", idCard);
        return response;
    }

    @RequestMapping(value = "/id{id}/cards/{idCard}/edit", method = RequestMethod.GET)
    public String goToPageEditCard(@PathVariable Integer id, @PathVariable Integer idCard, ModelMap model) {
        User onlineUser = userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        model.addAttribute("user", onlineUser);
        ArrayList<Contact> contacts = (ArrayList<Contact>) contactService.listUserContact(id);
        model.addAttribute("contacts", contacts);
        ArrayList<Jobs> jobs = (ArrayList<Jobs>) jobsService.listUserJobs(id);
        model.addAttribute("jobs", jobs);
        model.addAttribute("card", (Card) cardService.getCardById(idCard));
        return "add_card_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchCard(@RequestParam(value = "searchText") String searchText, ModelMap model) {
        User onlineUser = userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        model.addAttribute("user", onlineUser);
        model.addAttribute("cards", cardService.searchCards(searchText));
        return "search_page";
    }

    @RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
    public ModelAndView downloadPdf(@RequestParam(value = "image", defaultValue = "") String image) {
        return new ModelAndView("pdfView", "image", image);
    }


}
