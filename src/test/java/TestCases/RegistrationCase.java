package TestCases;
import PageObjects.Registration;
import TestComponents.BaseTest;
import org.testng.annotations.Test;
public class RegistrationCase extends BaseTest {
    @Test
    public void registration(){
        value = true;
        Registration registration = new Registration(driver,user);
        registration.registrationOfUser(value);

    }
    @Test
    public void registrationNegative(){
        value = false;
        Registration registration = new Registration(driver,user);
        registration.registrationOfUser(value);

    }
}
