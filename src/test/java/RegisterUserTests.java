import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePageStellarBurgers;
import pageobject.LoginPageStellarBurgers;
import pageobject.RegisterPageStellarBurgers;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;

public class RegisterUserTests {

    private User user;
    private UserClient userClient;
    private WebDriver driver;
    public static final String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(HOME_PAGE_URL);

        user = UserGenerator.getRandom();
        userClient = new UserClient();
    }

    @Test
    @DisplayName("Тест успешной регистрации")
    public void registerUserFlowByGoodCredentialsShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.clickRegister();

        RegisterPageStellarBurgers objRegisterPageStellarBurgers = new RegisterPageStellarBurgers(driver);
        objRegisterPageStellarBurgers.enterName(user.getName());
        objRegisterPageStellarBurgers.enterEmail(user.getEmail());
        objRegisterPageStellarBurgers.enterPassword(user.getPassword());
        objRegisterPageStellarBurgers.clickRegister();
        boolean isLoginButton = objLoginPageStellarBurgers.checkLoadLoginPage();
        assertTrue(isLoginButton);
    }

    @Test
    @DisplayName("Тест регистрации с коротким паролем")
    public void registerUserFlowBySmallPasswordShowsWrong() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.clickRegister();

        RegisterPageStellarBurgers objRegisterPageStellarBurgers = new RegisterPageStellarBurgers(driver);
        objRegisterPageStellarBurgers.enterName(user.getName());
        objRegisterPageStellarBurgers.enterEmail(user.getEmail());
        objRegisterPageStellarBurgers.enterPassword(RandomStringUtils.randomAlphabetic(4));
        objRegisterPageStellarBurgers.clickRegister();
        boolean isInvalidPassword = objRegisterPageStellarBurgers.showsInvalidPasswordLabel();
        assertTrue(isInvalidPassword);
    }

    @After
    public void tearDown() {
        driver.quit();
        ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.from(user));
        if (loginResponse.extract().statusCode() == SC_OK) {
            String accessToken = loginResponse.extract().path("accessToken");
            userClient.deleteUser(accessToken.split(" ")[1]);
        }
    }
}
