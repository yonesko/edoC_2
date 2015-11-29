package gleb.server.dao.impl;

import gleb.server.Sess;
import gleb.server.dao.Jobs;
import gleb.server.dao.entity.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobImpl implements Jobs {

    @Override
    public void deleteJob(Job job) {

    }

    @Override
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
