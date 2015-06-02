package mike.businesscards.service;

import mike.businesscards.dao.JobsDao;
import mike.businesscards.model.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */
@Service
public class JobsServiceImpl implements JobsService{

    @Autowired
    private JobsDao jobsDao;

    @Transactional
    public void addJob(Jobs contact, Integer userId) {
        jobsDao.addJob(contact, userId);
    }

    @Transactional
    public List<Jobs> listUserJobs(Integer userId) {
        return jobsDao.listUserJobs(userId);
    }

    @Transactional
    public List<Jobs> listAllJobs() {
        return jobsDao.listAllJobs();
    }

    @Transactional
    public void removeContact(Integer id) {
        jobsDao.removeContact(id);
    }
}
