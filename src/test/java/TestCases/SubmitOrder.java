package TestCases;
import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.annotations.Test;
import java.util.Random;

public class SubmitOrder extends BaseTest {
    @Test
    public void submitOrderWithNewAccount() throws  InterruptedException {
        value = true;
        new Registration(driver,user).registrationOfUser(value);
        Order order = new Login(driver,user).loginUserWithNewOrder();
        Payment payment = order.chooseProduct();
        FinishedOrder finishedOrder = payment.paymentFlow();
        finishedOrder.singOut();
    }

}
