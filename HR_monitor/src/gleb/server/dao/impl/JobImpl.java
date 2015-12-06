package gleb.server.dao.impl;

import gleb.server.dao.Sess;
import gleb.server.dao.Jobs;
import gleb.server.model.Job;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;

import java.util.ArrayList;
import java.util.List;

public class JobImpl implements Jobs {
    static ClassMetadata meta = Sess.getFactory().getClassMetadata(Job.class);
    
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

    public static Object getFieldValue(Job job, int i) {
        if (i == 0)
            return job.getId();
        return meta.getPropertyValue(job, meta.getPropertyNames()[i - 1]);
    }

    public static String getColName(int i) {
        if (i == 0)
            return "id";
        return meta.getPropertyNames()[i - 1];
    }

    public static int getColCount() {
        return meta.getPropertyNames().length + 1;
    }
}
