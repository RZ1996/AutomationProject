package TestCases;
import PageObjects.Login;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginCase extends BaseTest {
    private Random random;
    @Test
    public void login(){
        random = new Random();
        int index = random.nextInt(0, users.size());
        new Login(driver,user).loginWithRegisteredAccount(users.get(index));

    }


}
