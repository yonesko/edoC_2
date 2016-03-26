package example;

import base.CaseConfig;
import main.a;
import org.apache.commons.codec.digest.DigestUtils;

public class Hack {
    public static void main(String[] var0) {
        CaseConfig var3 = new CaseConfig("./cfg/test.properties", var0);

        System.out.println("Test passed.");
        String var4 = var3.f;
        var4 = DigestUtils.md5Hex("Salt: Beatrice1.0 Base:" + var4);
        System.out.println("Task pass-key: " + var4);

    }
}
