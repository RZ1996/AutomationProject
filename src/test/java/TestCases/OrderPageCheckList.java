package TestCases;
import PageObjects.Login;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class OrderPageCheckList extends BaseTest {
    @Test
    public void verifyOrderPage(){
        new Login(driver,user).loginUserWithNewOrder();
    }
}
