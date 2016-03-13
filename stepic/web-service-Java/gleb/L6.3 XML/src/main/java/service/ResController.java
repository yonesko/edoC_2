package service;

import models.TestResource;

public class ResController implements ResControllerMBean {
    private final TestResource resource;

    public ResController(TestResource resource) {
        this.resource = resource;
    }

    public String getName() {
        return resource.getName();
    }

    public int getAge() {
        return resource.getAge();
    }
}
