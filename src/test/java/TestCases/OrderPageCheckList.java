package TestCases;
import PageObjects.Login;
import PageObjects.Order;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class OrderPageCheckList extends BaseTest {
    @Test
    public void verifyOrderPage() throws InterruptedException {
        Order order =  new Login(driver,user).loginUserWithNewOrder();
        order.clickOnFields();
        order.unClickFields();

    }
}
