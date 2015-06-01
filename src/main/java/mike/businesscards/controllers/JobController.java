package mike.businesscards.controllers;

import mike.businesscards.dao.JobsDaoImpl;
import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.Jobs;
import mike.businesscards.model.User;
import mike.businesscards.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mike on 01/06/2015.
 */

@Controller
public class JobController {

    private UserDaoImpl userDaoImpl;
    private JobsDaoImpl jobsDaoImpl;

    @Autowired
    public JobController(UserDaoImpl userDaoImpl, JobsDaoImpl jobsDaoImpl){
        this.jobsDaoImpl = jobsDaoImpl;
        this.userDaoImpl = userDaoImpl;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add_job")
    public String initAddContactPage(ModelMap model) {
        Jobs jobModel = new Jobs();
        model.put("addContact", jobModel);
        (new UserSessionService()).addMailAttribute(model);
        return "add_job_page";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add_job")
    public String addContact(ModelMap model, Jobs job, BindingResult result) {
        if (result.hasErrors()) {
            model.put("addJob", job);
            return "add_job_page";
        }
        User onlineUser = this.userDaoImpl.getUserByEmail((new UserSessionService()).addMailAttribute(model));
        this.jobsDaoImpl.addJob(job, onlineUser.getId());
        return "redirect:/personal";
    }
}
