package mike.businesscards.service;

import mike.businesscards.model.Jobs;

import java.util.List;

/**
 * Created by Mike on 02/06/2015.
 */
public interface JobsService {

    public void addJob(Jobs contact, Integer userId);

    public Jobs getJob(Integer userId, Integer jobId);

    public List<Jobs> listUserJobs(Integer userId);

    public List<Jobs> listAllJobs();

    public void removeJob(Integer id);
}
