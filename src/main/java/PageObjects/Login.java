package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Login extends Registration {

    private User user;
    public Login(WebDriver driver, User user) {
        super(driver,user);
        this.driver = driver;
        this.user = user;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//input[@type='email']")
    WebElement emailInput;
    @FindBy(xpath ="//input[@type='password']")
    WebElement passwordInput;
    @FindBy(xpath ="//input[@name='login']")
    WebElement loginButton;


    public Order loginUserWithNewOrder(){
        emailInput.sendKeys(user.email);
        passwordInput.sendKeys(user.password);
        loginButton.click();
        return new Order(driver);
    }

    public void loginWithRegisteredAccount(){
        driver.get(url);
        emailInput.sendKeys(user.email);
        passwordInput.sendKeys(user.password);
        loginButton.click();
    }



}
