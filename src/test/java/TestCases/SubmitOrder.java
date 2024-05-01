package TestCases;
import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SubmitOrder extends BaseTest {

    @Test
    public void submitOrder() throws IOException, InterruptedException {
        Login login = launchApplication();
        Order order = login.loginUserWithNewOrder();
        Payment payment = order.chooseProduct();
        FinishedOrder finishedOrder = payment.payment();
        finishedOrder.home();
        finishedOrder.singOut();
    }

}
