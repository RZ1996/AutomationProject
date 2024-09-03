package TestCases;
import PageObjects.Registration;
import TestComponents.BaseTest;
import org.testng.annotations.Test;
public class RegistrationCase extends BaseTest {
    @Test
    public void registration(){

        Registration registration = new Registration(driver,user);
        registration.registrationOfUser();

    }
}
