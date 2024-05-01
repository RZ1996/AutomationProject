package TestComponents;
import PageObjects.Login;
import PageObjects.Registration;
import PageObjects.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public Registration registration;
    public User user;
    public WebDriver initialazeDriver() throws IOException {


        Properties prop = new Properties();
        FileInputStream stream = new FileInputStream("C:\\Users\\zamoc\\IdeaProjects\\Automation\\src\\main\\resources\\GlobalData.properties");
        prop.load(stream);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;

    }

    @BeforeMethod
    public Login launchApplication() throws IOException{
        driver = initialazeDriver();
        user = new User();
        registration = new Registration(driver, user);
        registration.getEmail();
        registration.registrationOfUser();
        return new Login(driver,user);
    }

    @AfterMethod
    public void closeApplication(){
        driver.quit();
    }


}
