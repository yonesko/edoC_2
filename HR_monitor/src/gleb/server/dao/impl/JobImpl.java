package gleb.server.dao.impl;

import gleb.server.dao.Sess;
import gleb.server.dao.Jobs;
import gleb.server.dao.entity.Job;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JobImpl implements Jobs {

    public void deleteJob(Job job) {

    }

    public Job insertJob(Job job) {
        Sess.beginTransaction();
        Session session = Sess.getSess();
        session.save(job);
        return job;
    }

    public List getAllJobs() {
        Session session = Sess.getSess();
        List jobs = new ArrayList<Job>();
        try {
            jobs = session.createCriteria(Job.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
