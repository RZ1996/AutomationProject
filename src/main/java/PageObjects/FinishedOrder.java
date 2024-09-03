package PageObjects;
import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FinishedOrder extends AbstractComponent {
    private final WebDriver driver;
    public FinishedOrder(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public OrderList viewOrderList() throws InterruptedException {
        waitForElementToDisappear();
        clickOnOrderButton();
        return new OrderList(driver);
    }
    public void singOut(){clickOnSignOutButton();}

}
