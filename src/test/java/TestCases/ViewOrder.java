package TestCases;
import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ViewOrder extends BaseTest {

    @Test
    public void viewOrder() throws IOException, InterruptedException {
        Login login = launchApplication();
        Order order = login.loginUserWithNewOrder();
        Payment payment = order.chooseProduct();
        FinishedOrder finishedOrder = payment.payment();
        OrderList orderList =  finishedOrder.viewOrder();
        orderList.viewOrder();


    }
}
