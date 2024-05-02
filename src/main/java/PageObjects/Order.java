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
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3']/input")
    WebElement searchInput;
    @FindBy(xpath =" //div[@class='py-2 border-bottom ml-3'][2]/div[@class='row']/div/input[@name='minPrice']")
    WebElement minPriceInput;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][2]/div[@class='row']/div/input[@name='maxPrice']")
    WebElement maxPriceInput;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][3]/div[@class='form-group ng-star-inserted'][1]/input")
    WebElement fashionCheckBox;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][3]/div[@class='form-group ng-star-inserted'][2]/input")
    WebElement electronicsCheckBox;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][3]/div[@class='form-group ng-star-inserted'][3]/input")
    WebElement houseHoldCheckBox;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][4]/div[@class='form-group ng-star-inserted'][1]/input")
    WebElement tshirtsCheckBox;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][4]/div[@class='form-group ng-star-inserted'][2]/input")
    WebElement shirtsCheckBox;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][4]/div[@class='form-group ng-star-inserted'][3]/input")
    WebElement shoesCheckBox;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][4]/div[@class='form-group ng-star-inserted'][4]/input")
    WebElement mobilesCheckBox;
    @FindBy(xpath ="//div[@class='py-2 border-bottom ml-3'][4]/div[@class='form-group ng-star-inserted'][5]/input")
    WebElement laptopsCheckBox;
    @FindBy(xpath ="//div[@class='py-2 ml-3']/div[@class='form-group ng-star-inserted']/input")
    WebElement menCheckBox;
    @FindBy(xpath ="//div[@class='py-2 ml-3']/div[@class='form-group ng-star-inserted'][2]/input")
    WebElement womanCheckBox;

    public void clickOnFields() throws InterruptedException {
        for(int i = 0; i < 3; i ++){
            searchInput.sendKeys(sortiments[i]);
            waitForElementToDisappear();
            searchInput.clear();
            waitForElementToDisappear();
        }
        searchInput.clear();
        waitForElementToDisappear();
        minPriceInput.sendKeys("0");
        maxPriceInput.sendKeys("300000");
        fashionCheckBox.click();
        electronicsCheckBox.click();
        houseHoldCheckBox.click();
        tshirtsCheckBox.click();
        shirtsCheckBox.click();
        shoesCheckBox.click();
        mobilesCheckBox.click();
        laptopsCheckBox.click();
        menCheckBox.click();
        womanCheckBox.click();
    }
    public void unClickFields(){
        minPriceInput.clear();
        maxPriceInput.clear();
        fashionCheckBox.click();
        electronicsCheckBox.click();
        houseHoldCheckBox.click();
        tshirtsCheckBox.click();
        shirtsCheckBox.click();
        shoesCheckBox.click();
        mobilesCheckBox.click();
        laptopsCheckBox.click();
        menCheckBox.click();
        womanCheckBox.click();
    }

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
