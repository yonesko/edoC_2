package gleb.server.dao;

import gleb.server.model.Job;

import java.util.List;

public interface Jobs {
    void deleteJob(Job job);
    void insertJob(Job job);
    List getAllJobs();
}
