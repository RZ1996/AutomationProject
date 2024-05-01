package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Registration  {
    protected WebDriver driver;
    protected final String url = "https://rahulshettyacademy.com/client";
    private final String tempMailUrl = "https://tempail.com/";
    private User user;
    private String userEmail;

    public Registration(WebDriver driver, User user){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.user = user;
    }

    @FindBy(xpath ="//p[contains(@class, 'text')]")
    WebElement registrationButton;
    @FindBy(xpath ="//input[@id='firstName']")
    WebElement firstNameInput;
    @FindBy(xpath ="//input[@id='lastName']")
    WebElement lastNameInput;
    @FindBy(xpath ="//input[@id='userEmail']")
    WebElement userEmailInput;
    @FindBy(xpath ="//input[@id='userMobile']")
    WebElement userMobileInput;
    @FindBy(xpath ="//select[@formcontrolname='occupation']")
    WebElement occupationSelect;
    @FindBy(xpath ="//input[@value='Male']")
    WebElement maleOption;
    @FindBy(xpath ="//input[@value='Female']")
    WebElement femaleOption;
    @FindBy(xpath ="//input[@id='userPassword']")
    WebElement passwordInput;
    @FindBy(xpath ="//input[@id='confirmPassword']")
    WebElement confirmPassword;
    @FindBy(xpath ="//input[@type='checkbox']")
    WebElement checkBox;
    @FindBy(xpath ="//input[@name='login']")
    WebElement registerButton;
    @FindBy(xpath ="//button[text()='Login']")
    WebElement loginButton;

    public void getEmail(){
        driver.get(tempMailUrl);
        WebElement consentElement = driver.findElement(By.xpath("//p[text()='Consent']"));
        consentElement.click();
        userEmail = driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value");
        driver.findElement(By.xpath("//button[text()='OK']")).click();
        user.email = userEmail;
    }

    public Login registrationOfUser(){
        driver.get(url);
        registrationButton.click();
        firstNameInput.sendKeys(user.firstName);
        lastNameInput.sendKeys(user.lastName);
        userEmailInput.sendKeys(user.email);
        userMobileInput.sendKeys(user.phoneNumber);
        occupationSelect.click();
        Select select = new Select(occupationSelect);
        select.selectByVisibleText(user.occupationValue);
        if(user.gender.equals("Male"))
            maleOption.click();
        else
            femaleOption.click();
        passwordInput.sendKeys(user.password);
        confirmPassword.sendKeys(user.password);
        checkBox.click();
        registerButton.click();
        loginButton.click();
        return new Login(driver,user);

    }

}
