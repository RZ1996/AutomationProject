package TestCases;
import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

public class DeleteOrder extends BaseTest {

    @Test
    public void deleteOrder() throws  InterruptedException {
        value = true;
        new Registration(driver,user).registrationOfUser(value);
        Order order = new Login(driver,user).loginUserWithNewOrder();
        Payment payment = order.chooseProduct();
        FinishedOrder finishedOrder = payment.paymentFlow();
        OrderList orderList = finishedOrder.viewOrderList();
        orderList.deleteOrder();

    }


}
