package TestComponents;
import PageObjects.Login;
import PageObjects.Registration;
import PageObjects.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    public WebDriver driver;
    public Registration registration;
    public User user;
    public WebDriver initialazeDriver() throws IOException {


        Properties prop = new Properties();
        FileInputStream stream = new FileInputStream("C:\\Users\\zamoc\\IdeaProjects\\AutomationProject\\src\\main\\resources\\GlobalData.properties");
        prop.load(stream);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver= new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new EdgeDriver(options);
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
        registration.registrationOfUser();
        return new Login(driver,user);
    }

    @AfterMethod
    public void closeApplication(){
        driver.quit();
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") +"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }


}
