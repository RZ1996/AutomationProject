package TestCases;
import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class OrderPageCheckList extends BaseTest {
    @Test
    public void verifyOrderPage() throws InterruptedException {
        value = true;
        new Registration(driver, user).registrationOfUser(value);
        Order order = new Login(driver, user).loginUserWithNewOrder();
        order.clickOnFields();
        order.unClickFields();
    }
}
