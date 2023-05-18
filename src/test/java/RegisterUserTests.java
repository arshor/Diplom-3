import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePageStellarBurgers;
import pageobject.LoginPageStellarBurgers;
import pageobject.RegisterPageStellarBurgers;

import static org.junit.Assert.assertTrue;

public class RegisterUserTests {

    private User user;
    private UserClient userClient;
    private WebDriver driver;
    public static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PAGE_URL);

        user = UserGenerator.getRandom();
        userClient = new UserClient();
    }

    @Test
    public void registerUserFlow() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.clickRegister();

        RegisterPageStellarBurgers objRegisterPageStellarBurgers = new RegisterPageStellarBurgers(driver);
        objRegisterPageStellarBurgers.enterName(user.getName());
        objRegisterPageStellarBurgers.enterEmail(user.getEmail());
        objRegisterPageStellarBurgers.enterPassword(user.getPassword());
        objRegisterPageStellarBurgers.clickRegister();
        boolean loginButton = objLoginPageStellarBurgers.checkLoadLoginPage();
        assertTrue(loginButton);
    }

    @After
    public void tearDown() {
        driver.quit();
        ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.from(user));
        if (loginResponse != null) {
            String accessToken = loginResponse.extract().path("accessToken");
            userClient.deleteUser(accessToken.split(" ")[1]);
        }
    }
}
