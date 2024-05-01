package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    private WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
    }

     protected void waitForElementToDisappear() throws InterruptedException {
        Thread.sleep(3000);
    }
    protected void waitForElementToAppear(WebElement element) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(xpath ="//button[@class='btn btn-custom']")
    WebElement homeButton;
    @FindBy(xpath ="//button[@routerlink='/dashboard/myorders']")
    WebElement orderButton;
    @FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
    WebElement cartButton;
    @FindBy(xpath ="//i[@class='fa fa-sign-out']")
    WebElement signOutButton;

    public void clickOnHomeButton(){homeButton.click();}
    public void clickOnOrderButton(){orderButton.click();}
    public void clickOnCartButton(){cartButton.click();}
    public void clickOnSignOutButton(){signOutButton.click();}
    public void scrollDownPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
    }
}
