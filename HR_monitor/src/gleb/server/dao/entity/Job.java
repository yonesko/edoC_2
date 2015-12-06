package gleb.server.dao.entity;

import gleb.server.LoGGer;
import gleb.server.dao.Sess;
import org.hibernate.metadata.ClassMetadata;

public class Job {
    private long id;
    private String title;
    private int minSalary;
    private int maxSalary;

    public Job() {
    }

    public Job(String title, int minSalary, int maxSalary) {
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Object getColValue(int i) {
        ClassMetadata meta = Sess.getFactory().getClassMetadata(Job.class);
        if (i == 0)
            return this.getId();
        return meta.getPropertyValue(this, meta.getPropertyNames()[i - 1]);
    }

    public static String getColName(int i) {
        if (i == 0)
            return "id";
        return Sess.getFactory().getClassMetadata(Job.class).getPropertyNames()[i - 1];
    }

    public static int getColCount() {
        return Sess.getFactory().getClassMetadata(Job.class).getPropertyNames().length + 1;
    }
}
