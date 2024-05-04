package TestCases;
import PageObjects.FinishedOrder;
import PageObjects.Login;
import PageObjects.Order;
import PageObjects.Registration;
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
