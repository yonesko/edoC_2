package main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by gleb on 12 July 2016.
 */
public class PersonBeanImpl implements PersonBean {
    String name;
    String gender;
    String Interests;
    int rating;
    int ratingCount = 0;

    public PersonBeanImpl(String name, String gender, String interests, int rating, int ratingCount) {
        this.name = name;
        this.gender = gender;
        Interests = interests;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterests() {
        return Interests;
    }

    public void setInterests(String interests) {
        Interests = interests;
    }

    public int getHotOrNotRating() {
        return ratingCount == 0 ? 0 : rating/ratingCount;
    }

    public void setHotOrNotRating(int hotOrNotRating) {
        rating += hotOrNotRating;
        ratingCount++;
    }

    public PersonBean getProxy(InvocationHandler invocationHandler) {
        return (PersonBean) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                getClass().getInterfaces(),
                invocationHandler);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PersonBeanImpl{");
        sb.append("name='").append(name).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", Interests='").append(Interests).append('\'');
        sb.append(", rating=").append(getHotOrNotRating());
        sb.append('}');
        return sb.toString();
    }
}
