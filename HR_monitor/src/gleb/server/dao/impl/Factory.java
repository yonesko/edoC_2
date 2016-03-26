package gleb.server.dao.impl;

public class Factory {
    private static JobImpl jobImpl;

    public static JobImpl getJobImpl(){
        if (jobImpl == null)
            jobImpl = new JobImpl();

        return jobImpl;
    }
}
