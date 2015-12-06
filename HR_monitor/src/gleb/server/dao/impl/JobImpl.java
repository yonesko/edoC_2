package gleb.server.dao.impl;

import gleb.server.dao.Sess;
import gleb.server.dao.Jobs;
import gleb.server.dao.entity.Job;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class JobImpl implements Jobs {

    public void deleteJob(Job job) {

    }

    public List getAllJobs() {
        Session session = null;
        List jobs = new ArrayList<Job>();
        try {
            session = Sess.getSess();
            jobs = session.createCriteria(Job.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
