package main;

/**
 * Created by gleb on 12 July 2016.
 */
public class Main {
    public static void main(String[] args) {
        PersonBean person = new PersonBeanImpl("benedict", "hz?", "cars", 42, 2);
        PersonBean ownerProxy = person.getProxy(new OwnerInvocationHandler(person));
        PersonBean nonOwnerProxy = person.getProxy(new NonOwnerInvocationHandler(person));

        ownerProxy.setInterests(ownerProxy.getInterests() + " " + "some shit");

        System.out.println(ownerProxy);

        try {
            ownerProxy.setHotOrNotRating(56);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            nonOwnerProxy.setInterests("eat shit");
        } catch (Exception e) {
            e.printStackTrace();
        }

        nonOwnerProxy.setHotOrNotRating(4);

        System.out.println(nonOwnerProxy);
    }
}
