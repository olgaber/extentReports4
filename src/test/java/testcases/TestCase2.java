package testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCase2 {

    @Test
    public void doLogin(){

        System.out.println("Executing Login Test");

    }

    @Test
    public void doUserReg(){

        System.out.println("Executing User Registration Test");
        Assert.fail("User Registration Test Failed");
    }

    @Test
    public void doLogout(){

        System.out.println("Executing Logout Test");
        throw  new SkipException("Skipped Logout Test");
    }
}
