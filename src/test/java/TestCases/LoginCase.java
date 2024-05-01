package TestCases;
import PageObjects.FinishedOrder;
import PageObjects.Login;
import TestComponents.BaseTest;
import org.testng.annotations.Test;
public class LoginCase extends BaseTest {
    @Test
    public void login(){
        new Login(driver,user).loginUserWithNewOrder();
        new FinishedOrder(driver).singOut();
    }


}
