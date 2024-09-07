package TestCases;
import PageObjects.Login;
import PageObjects.Registration;
import TestComponents.BaseTest;
import org.testng.annotations.Test;
import java.util.Random;

public class LoginCase extends BaseTest {
    @Test
    public void login(){
        random = new Random();
        value = true;
        int index = random.nextInt(users.size());
        new Login(driver,user).loginWithRegisteredAccount(users.get(index),value);

    }

    @Test
    public void loginNegative(){
        random = new Random();
        value = false;
        int index = random.nextInt(users.size());
        new Login(driver,user).loginWithRegisteredAccount(users.get(index),value);

    }

    @Test
    public void loginWithNewAccount(){
        value = true;
        new Registration(driver,user).registrationOfUser(value);

    }


}
