package TestCases;
import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteOrder extends BaseTest {

    @Test
    public void deleteOrder() throws IOException, InterruptedException {
        Login login = launchApplication();
        Order order = login.loginUserWithNewOrder();
        Payment payment = order.chooseProduct();
        FinishedOrder finishedOrder = payment.payment();
        OrderList orderList =  finishedOrder.viewOrder();
        orderList.deleteOrder();
    }
}
