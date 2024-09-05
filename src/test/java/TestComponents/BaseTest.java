package TestComponents;
import PageObjects.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected WebDriver driver;
    protected User user;
    protected ArrayList<User> users = new ArrayList<>();
    protected WebDriver initialazeDriver() throws IOException {


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
    protected void launchApplication() throws IOException{
        driver = initialazeDriver();
        user = new User();
        users.add(user);

    }

    @AfterMethod
    protected void closeApplication(){
        driver.quit();
    }

    protected String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        String screenshotPath = null;

        try {
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
            FileHandler.copy(sourceFile, destinationFile);
            String[] relativePath = destinationFile.toString().split("reports");
            screenshotPath = ".\\" + relativePath[1];
        } catch (Exception e) {
            System.out.println("Failure to take screenshot " + e);
        }
        return screenshotPath;
    }
}
