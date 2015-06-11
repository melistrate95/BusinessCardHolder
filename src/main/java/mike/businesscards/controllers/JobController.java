package mike.businesscards.controllers;

import mike.businesscards.dao.JobsDaoImpl;
import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.Jobs;
import mike.businesscards.model.User;
import mike.businesscards.service.JobsService;
import mike.businesscards.service.UserService;
import mike.businesscards.service.UserServiceImpl;
import mike.businesscards.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Mike on 01/06/2015.
 */

@Controller
public class JobController {

    private UserService userService;
    private JobsService jobsService;

    @Autowired
    public JobController(UserService userService, JobsService jobsService){
        this.jobsService = jobsService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add_job")
    public String initAddContactPage(ModelMap model) {
        Jobs jobModel = new Jobs();
        model.put("addContact", jobModel);
        (new UserSessionService()).addMailAttribute(model);
        return "add_job_page";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_job")
    public String addContact(ModelMap model, @Valid Jobs job, Errors errors, BindingResult result) {
        if (errors.hasErrors()) {
            return "add_job_page";
        }
        if (result.hasErrors()) {
            model.put("addJob", job);
            return "add_job_page";
        }
        User onlineUser = this.userService.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        this.jobsService.addJob(job, onlineUser.getId());
        return "redirect:/personal";
    }
}
