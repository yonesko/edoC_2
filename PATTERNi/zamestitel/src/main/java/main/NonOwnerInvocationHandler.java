package main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandler implements InvocationHandler {
    PersonBean person;

    public NonOwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {

        try {

            if (method.getName().startsWith("set") && !method.getName().equals("setHotOrNotRating"))
                throw new IllegalAccessException("non owner cant only setHotOrNotRating");

            return method.invoke(person, args);

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
