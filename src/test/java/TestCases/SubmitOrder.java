package TestCases;
import PageObjects.FinishedOrder;
import PageObjects.Login;
import PageObjects.Order;
import PageObjects.Payment;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class SubmitOrder extends BaseTest {
    @Test
    public void submitOrderWithNewAccount() throws  InterruptedException {
        Order order = new Login(driver,user).loginUserWithNewOrder();
        Payment payment = order.chooseProduct();
        FinishedOrder finishedOrder = payment.paymentFlow();
        finishedOrder.singOut();
    }

}
