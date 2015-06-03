package mike.businesscards.dao;

import mike.businesscards.model.Jobs;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */
public interface JobsDao {

    public void addJob(Jobs contact, Integer userId);

    public List<Jobs> listUserJobs(Integer userId);

    public List<Jobs> listAllJobs();

    public void removeContact(Integer id);
}
