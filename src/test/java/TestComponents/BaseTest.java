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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected WebDriver driver;
    protected User user;
    protected Random random;
    protected Boolean value;
    protected ArrayList<User> users = new ArrayList<>();
    private Connection connection;
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
        connectToDatabase();
        insertUserToDatabase(user);

    }

    @AfterMethod
    protected void closeApplication(){
        driver.quit();
        closeDatabaseConnection();
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

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/qadbt"; // Replace with your database URL
        String username = "root"; // Replace with your database username
        String password = "271996"; // Replace with your database password
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void insertUserToDatabase(User user) {
        String sql = "INSERT INTO User (firstName, lastName, email, password, phoneNumber, gender, occupationValue) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getOccupationValue());
            preparedStatement.executeUpdate();
            System.out.println("User inserted into the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeDatabaseConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
