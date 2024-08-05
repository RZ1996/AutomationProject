package TestCases;
import PageObjects.Login;
import PageObjects.Order;
import TestComponents.BaseTest;
import org.testng.annotations.Test;
public class LoginCase extends BaseTest {
    @Test
    public void login(){
        new Login(driver,user).loginWithRegisteredAccount();
        Order order = new Order(driver);
        order.clickOnHomeButton();
        order.clickOnSignOutButton();


    }


}
