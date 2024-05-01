package PageObjects;
import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Random;

public class Order extends AbstractComponent {

    private  WebDriver driver;
    private String[] sortiments = {"ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"};
    WebElement prod;
    Random random = new Random();

    public Order(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//p[contains(@class, 'text')]")
    List<WebElement> products;
    WebElement addToCartButton;
    @FindBy(xpath ="//button[text()='Checkout']")
    WebElement checkOutButton;

    public Payment chooseProduct() throws InterruptedException {
        products = driver.findElements(By.cssSelector(".mb-3"));
        int index = random.nextInt(sortiments.length);
        String sortimentToFind = sortiments[index];
        prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(sortimentToFind)).findFirst().orElse(null);
        if(prod != null){
            addToCartButton = prod.findElement(By.xpath(".//button[@class='btn w-10 rounded']"));
        }
        else{
            throw new IllegalArgumentException("Product with name '" + sortimentToFind + "' was not found.");
        }
        addToCartButton.click();
        waitForElementToDisappear();
        clickOnCartButton();
        checkOutButton.click();
        return new Payment(driver);
    }
}
