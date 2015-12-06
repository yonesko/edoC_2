package gleb.server.dao.entity;

import gleb.server.LoGGer;

public class Job extends AbstractEntity {
    private long id;
    private String title;
    private int minSalary;
    private int maxSalary;

    static {
        try {
            thisClass = (Class<? extends AbstractEntity>) Class.forName("gleb.server.dao.entity.Job");
        } catch (ClassNotFoundException e) {
            LoGGer.printException(e);
        }
        fields = thisClass.getDeclaredFields();
    }

    public Job() {
    }

    public Job(String title, int minSalary, int maxSalary) {
        System.out.println("Job(");
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
}
