package gleb.server.dao;

import gleb.server.dao.entity.Job;

import java.util.Collection;
import java.util.List;

public interface Jobs {
    void deleteJob(Job job);
    List getAllJobs();
}
