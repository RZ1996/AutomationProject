package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Payment {
    private  WebDriver driver;
    public Payment(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//input[@class='input txt']")
    WebElement cvvInput;
    @FindBy(xpath ="//div[@class='details__user']/div/div/div")
    WebElement countrySelect;
    @FindBy(xpath ="//button[@type='button']")
    WebElement choosenCountry;
    @FindBy(xpath ="//a[@class='btnn action__submit ng-star-inserted']")
    WebElement placeOrder;


    public FinishedOrder paymentFlow(){
        cvvInput.sendKeys("100");
        Actions a = new Actions(driver);
        a.sendKeys(countrySelect, "Czech Republic").build().perform();
        choosenCountry.click();
        placeOrder.click();
        return new FinishedOrder(driver);

    }
}
