package service;

import models.TestResource;
import sax.LoadXML;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class ResourceService {
    private static TestResource ourInstance;
    public static TestResource getInstance(String path) {
        if (ourInstance == null) {
            ourInstance = LoadXML.readXML(path);
            ResController resourceMBean = new ResController(ourInstance);

            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = null;
            try {
                objectName = new ObjectName("Admin:type=ResController");
                mbs.registerMBean(resourceMBean, objectName);
            } catch (MalformedObjectNameException e) {
                e.printStackTrace();
            } catch (NotCompliantMBeanException e) {
                e.printStackTrace();
            } catch (InstanceAlreadyExistsException e) {
                e.printStackTrace();
            } catch (MBeanRegistrationException e) {
                e.printStackTrace();
            }
        }
        return ourInstance;
    }
}
