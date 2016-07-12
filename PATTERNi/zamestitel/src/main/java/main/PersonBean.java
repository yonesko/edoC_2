package main;

import java.lang.reflect.InvocationHandler;

/**
 * Created by gleb on 12 July 2016.
 */
public interface PersonBean {
    String getName();

    void setName(String name);

    String getGender();

    void setGender(String gender);

    String getInterests();

    void setInterests(String interests);

    int getHotOrNotRating();

    void setHotOrNotRating(int hotOrNotRating);

    PersonBean getProxy(InvocationHandler invocationHandler);
}