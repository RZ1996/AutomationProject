package PageObjects;
import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderList extends AbstractComponent {
    private final WebDriver driver;
    public OrderList(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@class='btn btn-primary col-md-2 offset-md-4']")
    WebElement backToShopButton;
    @FindBy(xpath = "//button[@class='btn btn-primary col-md-2']")
    WebElement backToCartButton;
    @FindBy(xpath = "//tr[@class='ng-star-inserted']/td/button[text()='View']")
    WebElement viewButton;
    @FindBy(xpath = "//tr[@class='ng-star-inserted']/td/button[text()='Delete']")
    WebElement deleteButton;
    @FindBy(xpath = "//div[@routerlink='/dashboard/myorders']")
    WebElement viewOrdersButton;

    public void viewOrder() throws InterruptedException {
        viewButton.click();
        waitForElementToDisappear();
        scrollDownPage();
        waitForElementToDisappear();
        waitForElementToAppear(viewOrdersButton);
        viewOrdersButton.click();
        waitForElementToDisappear();
        backToCartButton.click();
        waitForElementToDisappear();
        clickOnSignOutButton();
    }
    public void deleteOrder(){deleteButton.click();backToShopButton.click();}

}
